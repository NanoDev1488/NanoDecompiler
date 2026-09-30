#!/usr/bin/env node
// НОВОЕ v1.8.4: мини-бэкенд телеметрии NanoDecompiler.
//
// Принимает POST /report от клиента (JSON, см. схему ниже), формирует
// человекочитаемый отчёт и пересылает его в Telegram-бота - коротким
// текстовым сообщением (summary) + файлом-вложением (полный контекст
// каждого отката на байткод: 10 строк до, сам байткод, 10 строк после -
// именно в таком порядке ДЛЯ КАЖДОГО отката, а не все "до" отдельно от
// всех "после").
//
// БЕЗ npm-зависимостей вообще - только встроенные модули Node.js (fetch и
// FormData глобальны с Node 18+). Специально: это разворачивается ОДИН РАЗ
// на сервере пользователя, минимум мороки - `node server.js` и всё.
//
// ---- Запуск ----
//   1. Скопировать config.example.json -> config.json, заполнить botToken/chatId
//      (или задать переменные окружения TG_BOT_TOKEN / TG_CHAT_ID / PORT -
//      они имеют приоритет над config.json).
//   2. node server.js
//   3. Указать http://<ip-сервера>:<port>/report как адрес телеметрии в
//      настройках NanoDecompiler.
//
// ---- Как получить chatId для личных сообщений ----
//   Написать боту любое сообщение, затем открыть в браузере
//   https://api.telegram.org/bot<TOKEN>/getUpdates - там будет chat.id.

"use strict";

const http = require("http");
const fs = require("fs");
const path = require("path");
const zlib = require("zlib");

// ==================== pure node zip generator ====================
const crcTable = new Uint32Array(256);
for (let n = 0; n < 256; n++) {
  let c = n;
  for (let k = 0; k < 8; k++) {
    c = (c & 1) ? (0xedb88320 ^ (c >>> 1)) : (c >>> 1);
  }
  crcTable[n] = c;
}
function crc32(buf) {
  let crc = 0xffffffff;
  for (let i = 0; i < buf.length; i++) {
    crc = crcTable[(crc ^ buf[i]) & 0xff] ^ (crc >>> 8);
  }
  return (crc ^ 0xffffffff) >>> 0;
}

function createZip(files) {
  const localHeaders = [];
  const centralHeaders = [];
  let offset = 0;

  for (const file of files) {
    const nameBuf = Buffer.from(file.name, "utf8");
    const dataBuf = Buffer.isBuffer(file.data) ? file.data : Buffer.from(file.data || "", "utf8");
    const crc = crc32(dataBuf);
    const compressed = zlib.deflateRawSync(dataBuf);
    const uncompressedSize = dataBuf.length;
    const compressedSize = compressed.length;

    const localHeader = Buffer.alloc(30);
    localHeader.writeUInt32LE(0x04034b50, 0);
    localHeader.writeUInt16LE(20, 4);
    localHeader.writeUInt16LE(0, 6);
    localHeader.writeUInt16LE(8, 8);
    localHeader.writeUInt16LE(0, 10);
    localHeader.writeUInt16LE(0, 12);
    localHeader.writeUInt32LE(crc, 14);
    localHeader.writeUInt32LE(compressedSize, 18);
    localHeader.writeUInt32LE(uncompressedSize, 22);
    localHeader.writeUInt16LE(nameBuf.length, 26);
    localHeader.writeUInt16LE(0, 28);

    localHeaders.push(localHeader, nameBuf, compressed);

    const centralHeader = Buffer.alloc(46);
    centralHeader.writeUInt32LE(0x02014b50, 0);
    centralHeader.writeUInt16LE(20, 4);
    centralHeader.writeUInt16LE(20, 6);
    centralHeader.writeUInt16LE(0, 8);
    centralHeader.writeUInt16LE(8, 10);
    centralHeader.writeUInt16LE(0, 12);
    centralHeader.writeUInt16LE(0, 14);
    centralHeader.writeUInt32LE(crc, 16);
    centralHeader.writeUInt32LE(compressedSize, 20);
    centralHeader.writeUInt32LE(uncompressedSize, 24);
    centralHeader.writeUInt16LE(nameBuf.length, 28);
    centralHeader.writeUInt16LE(0, 30);
    centralHeader.writeUInt16LE(0, 32);
    centralHeader.writeUInt16LE(0, 34);
    centralHeader.writeUInt16LE(0, 36);
    centralHeader.writeUInt32LE(0, 38);
    centralHeader.writeUInt32LE(offset, 42);

    centralHeaders.push(centralHeader, nameBuf);
    offset += 30 + nameBuf.length + compressedSize;
  }

  const centralDirOffset = offset;
  const centralDirSize = centralHeaders.reduce((acc, b) => acc + b.length, 0);

  const eocd = Buffer.alloc(22);
  eocd.writeUInt32LE(0x06054b50, 0);
  eocd.writeUInt16LE(0, 4);
  eocd.writeUInt16LE(0, 6);
  eocd.writeUInt16LE(files.length, 8);
  eocd.writeUInt16LE(files.length, 10);
  eocd.writeUInt32LE(centralDirSize, 12);
  eocd.writeUInt32LE(centralDirOffset, 16);
  eocd.writeUInt16LE(0, 20);

  return Buffer.concat([...localHeaders, ...centralHeaders, eocd]);
}

// ==================== конфигурация ====================

function loadConfig() {
  const configPath = path.join(__dirname, "config.json");
  let fileConfig = {};
  if (fs.existsSync(configPath)) {
    try {
      fileConfig = JSON.parse(fs.readFileSync(configPath, "utf8"));
    } catch (e) {
      console.error("config.json существует, но не парсится как JSON:", e.message);
      process.exit(1);
    }
  }
  const botToken = process.env.TG_BOT_TOKEN || fileConfig.botToken;
  const chatId = process.env.TG_CHAT_ID || fileConfig.chatId;
  const port = Number(process.env.PORT || fileConfig.port || 8787);
  // НОВОЕ: необязательный общий секрет - клиент присылает его в заголовке
  // X-Report-Secret, сервер отбрасывает запросы без совпадения. Пусто -
  // проверка выключена (принимаем любой POST /report). Без этого сервер
  // открыт для отправки отчётов ЛЮБЫМ, кто знает адрес - не критично для
  // "прислать код себе в личку", но легко закрыть одной строкой в config.
  const reportSecret = process.env.REPORT_SECRET || fileConfig.reportSecret || "";
  if (!botToken || !chatId) {
    console.error(
      "Не хватает botToken/chatId - заполни config.json (см. config.example.json) " +
        "или задай TG_BOT_TOKEN/TG_CHAT_ID в переменных окружения.",
    );
    process.exit(1);
  }
  return { botToken, chatId: String(chatId), port, reportSecret };
}

const { botToken, chatId, port, reportSecret } = loadConfig();
const TG_API = `https://api.telegram.org/bot${botToken}`;

// ==================== нормализация входных данных ====================
// НОВОЕ: движок может прислать что угодно "странное" (не тот тип, NaN,
// отрицательные числа, слишком длинные массивы) - тут не отклоняем весь
// отчёт из-за одного кривого поля, а просто приводим к разумному виду.

function safeStr(v, fallback = "unknown") {
  if (typeof v === "string" && v.length) return v;
  if (typeof v === "number" && Number.isFinite(v)) return String(v);
  return fallback;
}
function safeNum(v, fallback = 0) {
  const n = Number(v);
  return Number.isFinite(n) ? n : fallback;
}
function safeArrOfStr(v, maxItems = 2000, maxLineLen = 2000) {
  if (!Array.isArray(v)) return [];
  return v.slice(0, maxItems).map(x => {
    const s = typeof x === "string" ? x : safeStr(x, "");
    return s.length > maxLineLen ? s.slice(0, maxLineLen) + " …[обрезано]" : s;
  });
}

/**
 * @typedef {{file: string, method_hint: string, java_before: string[], bytecode: string[], java_after: string[]}} FallbackContext
 */

function normalizeReport(body) {
  const fallbackContextsRaw = Array.isArray(body.fallback_contexts) ? body.fallback_contexts : [];
  const MAX_CONTEXTS = 200;
  const fallback_contexts = fallbackContextsRaw.slice(0, MAX_CONTEXTS).map(c => ({
    file: safeStr(c && c.file, "unknown file"),
    method_hint: safeStr(c && c.method_hint, ""),
    java_before: safeArrOfStr(c && c.java_before),
    bytecode: safeArrOfStr(c && c.bytecode),
    java_after: safeArrOfStr(c && c.java_after),
  }));

  const heavy_files = Array.isArray(body.heavy_files)
    ? body.heavy_files.slice(0, 20).map(f => ({
        file: safeStr(f && f.file, "unknown.java"),
        content: typeof f.content === "string" ? f.content : "",
        fallback_pct: safeNum(f && f.fallback_pct, 0),
      }))
    : [];

  return {
    app_version: safeStr(body.app_version),
    app_commit: safeStr(body.app_commit, "unknown (not built from git checkout)"),
    engine_version: safeStr(body.engine_version),
    os: safeStr(body.os),
    target_plugin_name: safeStr(body.target_plugin_name, "(unknown plugin)"),
    target_platform: safeStr(body.target_platform, "unknown"),
    jar_file_name: safeStr(body.jar_file_name, "(unknown file)"),
    classes_total: Math.max(0, safeNum(body.classes_total)),
    total_methods: Math.max(0, safeNum(body.total_methods)),
    decompiled_methods: Math.max(0, safeNum(body.decompiled_methods)),
    fallback_methods: Math.max(0, safeNum(body.fallback_methods)),
    decompiled_pct: Math.max(0, Math.min(100, safeNum(body.decompiled_pct))),
    user_comment: safeStr(body.user_comment, ""),
    fallback_contexts,
    heavy_files,
  };
}

// ==================== форматирование отчёта ====================

function escapeHtml(s) {
  return String(s).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
}

function buildSummaryHtml(r) {
  const fallbackPct = r.total_methods > 0 ? (r.fallback_methods / r.total_methods) * 100 : (100 - r.decompiled_pct);
  const lines = [
    `<b>🔍 NanoDecompiler Report</b>`,
    ``,
    `📦 <b>Плагин:</b> <code>${escapeHtml(r.target_plugin_name)}</code> (${escapeHtml(r.jar_file_name)})`,
    `🖥 <b>Платформа:</b> <code>${escapeHtml(r.target_platform)}</code>`,
    `⚙️ <b>Версия:</b> GUI <code>${escapeHtml(r.app_version)}</code> · Engine <code>${escapeHtml(r.engine_version)}</code>`,
    `💻 <b>ОС:</b> <code>${escapeHtml(r.os)}</code>`,
    ``,
    `📊 <b>Статистика:</b>`,
    `• Классов: <b>${r.classes_total}</b>`,
    `• Методов: <b>${r.decompiled_methods}/${r.total_methods}</b> декомпилировано (<b>${r.decompiled_pct.toFixed(1)}%</b>)`,
    `• Байткод-откатов: <b>${r.fallback_methods}</b> (${fallbackPct.toFixed(1)}% | ${r.fallback_contexts.length} мест зафиксировано)`,
  ];

  if (r.user_comment) {
    lines.push(``, `💬 <b>Комментарий:</b>\n<blockquote expandable>${escapeHtml(r.user_comment)}</blockquote>`);
  }

  if (r.fallback_contexts.length > 0) {
    lines.push(``, `⚠️ <b>Места байткода:</b>`);
    const previewCount = Math.min(3, r.fallback_contexts.length);
    for (let i = 0; i < previewCount; i++) {
      const c = r.fallback_contexts[i];
      const snippet = [
        ...c.java_before.slice(-5),
        `// --- БАЙТКОД (${c.bytecode.length} строк) ---`,
        ...c.bytecode.slice(0, 8),
        ...(c.bytecode.length > 8 ? ["// ..."] : []),
        ...c.java_after.slice(0, 5),
      ].join("\n");
      lines.push(
        `<b>[${i + 1}] ${escapeHtml(c.file)}</b>${c.method_hint ? ` · <code>${escapeHtml(c.method_hint)}</code>` : ""}\n<blockquote expandable>${escapeHtml(snippet)}</blockquote>`
      );
    }
    if (r.fallback_contexts.length > previewCount) {
      lines.push(`<i>... и ещё ${r.fallback_contexts.length - previewCount} мест во вложении</i>`);
    }
  }

  const text = lines.join("\n");
  return text.length > 4000 ? text.slice(0, 4000) + "\n…" : text;
}

function buildAttachmentText(r) {
  const parts = [];
  parts.push(`NanoDecompiler error report`);
  parts.push(`====================================`);
  parts.push(`App version:    ${r.app_version}`);
  parts.push(`Commit:         ${r.app_commit}`);
  parts.push(`Engine version: ${r.engine_version}`);
  parts.push(`OS:             ${r.os}`);
  parts.push(``);
  parts.push(`Plugin:   ${r.target_plugin_name}`);
  parts.push(`Jar file: ${r.jar_file_name}`);
  parts.push(`Platform: ${r.target_platform}`);
  parts.push(``);
  parts.push(`Methods decompiled: ${r.decompiled_methods}/${r.total_methods} (${r.decompiled_pct.toFixed(1)}%)`);
  parts.push(`Fallback methods:   ${r.fallback_methods}`);
  if (r.user_comment) {
    parts.push(``);
    parts.push(`User comment:`);
    parts.push(r.user_comment);
  }
  parts.push(``);
  parts.push(`==================== FALLBACK LOCATIONS (${r.fallback_contexts.length}) ====================`);

  r.fallback_contexts.forEach((c, idx) => {
    parts.push(``);
    parts.push(`---- [${idx + 1}/${r.fallback_contexts.length}] ${c.file} ----`);
    if (c.method_hint) parts.push(`Method: ${c.method_hint}`);
    parts.push(``);
    parts.push(`-- ${Math.min(5, c.java_before.length)} line(s) before --`);
    parts.push(...c.java_before.slice(-5));
    parts.push(``);
    parts.push(`-- bytecode (${c.bytecode.length} line(s)) --`);
    parts.push(...c.bytecode);
    parts.push(``);
    parts.push(`-- ${Math.min(5, c.java_after.length)} line(s) after --`);
    parts.push(...c.java_after.slice(0, 5));
  });

  return parts.join("\n");
}

// ==================== отправка в Telegram ====================

async function tgSendMessage(text) {
  const res = await fetch(`${TG_API}/sendMessage`, {
    method: "POST",
    headers: { "content-type": "application/json" },
    body: JSON.stringify({ chat_id: chatId, text, parse_mode: "HTML" }),
  });
  if (!res.ok) throw new Error(`sendMessage: ${res.status} ${await res.text()}`);
}

async function tgSendDocument(filename, content, mimeType = "text/plain; charset=utf-8") {
  if (!content) return;
  const form = new FormData();
  form.append("chat_id", chatId);
  const blob = Buffer.isBuffer(content)
    ? new Blob([content], { type: mimeType })
    : new Blob([content], { type: mimeType });
  form.append("document", blob, filename);
  const res = await fetch(`${TG_API}/sendDocument`, { method: "POST", body: form });
  if (!res.ok) throw new Error(`sendDocument: ${res.status} ${await res.text()}`);
}

// ==================== HTTP-сервер ====================

const server = http.createServer((req, res) => {
  if (req.method !== "POST" || req.url !== "/report") {
    res.writeHead(404).end("not found");
    return;
  }
  if (reportSecret && req.headers["x-report-secret"] !== reportSecret) {
    res.writeHead(401).end("unauthorized");
    return;
  }

  let raw = "";
  let tooBig = false;
  const MAX_BODY = 25 * 1024 * 1024;
  req.on("data", chunk => {
    if (tooBig) return;
    raw += chunk;
    if (raw.length > MAX_BODY) {
      tooBig = true;
      res.writeHead(413).end("payload too large");
      req.destroy();
    }
  });
  req.on("end", async () => {
    if (tooBig) return;
    let body;
    try {
      body = JSON.parse(raw);
    } catch {
      res.writeHead(400).end("invalid json");
      return;
    }
    const report = normalizeReport(body);
    try {
      await tgSendMessage(buildSummaryHtml(report));
      const safeName = report.jar_file_name.replace(/[^\w.\-]+/g, "_").slice(0, 60) || "report";
      const fallbackPct = report.total_methods > 0 ? (report.fallback_methods / report.total_methods) * 100 : (100 - report.decompiled_pct);

      if (fallbackPct > 5.0 || (report.heavy_files && report.heavy_files.length > 0)) {
        // Упаковываем в ZIP архив, если байткод превышает 5% или есть файлы с байткодом
        const zipFiles = [
          { name: "report_summary.txt", data: buildAttachmentText(report) },
          { name: "stats.json", data: JSON.stringify(body, null, 2) },
        ];
        if (report.heavy_files && report.heavy_files.length > 0) {
          for (const f of report.heavy_files) {
            zipFiles.push({ name: `sources/${f.file.replace(/^[/\\]+/, "")}`, data: f.content });
          }
        }
        const zipBuf = createZip(zipFiles);
        await tgSendDocument(`${safeName}_bytecode_archive.zip`, zipBuf, "application/zip");
      } else if (report.fallback_contexts.length > 0) {
        await tgSendDocument(`${safeName}_fallback_report.txt`, buildAttachmentText(report), "text/plain; charset=utf-8");
      }

      res.writeHead(200, { "content-type": "application/json" }).end(JSON.stringify({ ok: true }));
    } catch (e) {
      console.error("Ошибка отправки в Telegram:", e.message);
      res.writeHead(502, { "content-type": "application/json" }).end(JSON.stringify({ ok: false, error: e.message }));
    }
  });
});

server.listen(port, () => {
  console.log(`NanoDecompiler telemetry relay слушает на порту ${port} (POST /report)`);
});
