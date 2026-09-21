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
  // Потолок - не даём одному отчёту раздуться до гигабайта, если движок
  // словил что-то совсем экзотическое (тысячи методов с fallback).
  const MAX_CONTEXTS = 200;
  const fallback_contexts = fallbackContextsRaw.slice(0, MAX_CONTEXTS).map(c => ({
    file: safeStr(c && c.file, "unknown file"),
    method_hint: safeStr(c && c.method_hint, ""),
    java_before: safeArrOfStr(c && c.java_before),
    bytecode: safeArrOfStr(c && c.bytecode),
    java_after: safeArrOfStr(c && c.java_after),
  }));

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
  };
}

// ==================== форматирование отчёта ====================

function escapeHtml(s) {
  return String(s).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
}

function buildSummaryHtml(r) {
  const lines = [
    `<b>NanoDecompiler error report</b>`,
    ``,
    `App version: <code>${escapeHtml(r.app_version)}</code>`,
    `Commit: <code>${escapeHtml(r.app_commit)}</code>`,
    `Engine: <code>${escapeHtml(r.engine_version)}</code>`,
    `OS: <code>${escapeHtml(r.os)}</code>`,
    ``,
    `Plugin: <b>${escapeHtml(r.target_plugin_name)}</b> (${escapeHtml(r.jar_file_name)})`,
    `Platform: ${escapeHtml(r.target_platform)}`,
    `Methods: ${r.decompiled_methods}/${r.total_methods} decompiled (${r.decompiled_pct.toFixed(1)}%), ${r.fallback_methods} fallback`,
    `Fallback locations attached: ${r.fallback_contexts.length}`,
  ];
  if (r.user_comment) lines.push(``, `Comment: ${escapeHtml(r.user_comment)}`);
  const text = lines.join("\n");
  // Telegram-лимит на текст сообщения - 4096 символов, у summary такого
  // объёма никогда не будет, но на всякий случай подрежем.
  return text.length > 4000 ? text.slice(0, 4000) + "\n…" : text;
}

/**
 * Полный файл-вложение - ПОСЛЕДОВАТЕЛЬНО, каждый fallback целиком (before +
 * bytecode + after) один за другим, а НЕ "все before, потом все bytecode,
 * потом все after" - именно так, как попросили.
 */
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
    parts.push(`-- ${c.java_before.length} line(s) before --`);
    parts.push(...c.java_before);
    parts.push(``);
    parts.push(`-- bytecode (${c.bytecode.length} line(s)) --`);
    parts.push(...c.bytecode);
    parts.push(``);
    parts.push(`-- ${c.java_after.length} line(s) after --`);
    parts.push(...c.java_after);
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

async function tgSendDocument(filename, content) {
  if (!content.trim()) return; // нечего прикладывать (0 fallback-контекстов) - не шлём пустой файл
  const form = new FormData();
  form.append("chat_id", chatId);
  form.append(
    "document",
    new Blob([content], { type: "text/plain; charset=utf-8" }),
    filename,
  );
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
  const MAX_BODY = 20 * 1024 * 1024; // 20 МБ с запасом - обычный отчёт на порядки меньше
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
      await tgSendDocument(`${safeName}_fallback_report.txt`, buildAttachmentText(report));
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
