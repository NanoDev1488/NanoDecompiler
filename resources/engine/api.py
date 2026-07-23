# -*- coding: utf-8 -*-
"""
Режим API (HANDOFF_3, п.2) - для интеграции с чужим приложением (JS),
которое обращается к декомпилерам через HTTP. Оба варианта из хэндоффа
реализованы (там предлагалось спросить пользователя или сделать оба):

  1. Разовый вызов, только JSON в stdout, без баннера/цвета/прогресс-бара:
         python3 main.py plugin.jar out_dir --json-output

  2. Локальный HTTP-сервер (stdlib http.server, без внешних зависимостей):
         python3 main.py --api-server [--port 8791] [--host 127.0.0.1]
     Эндпоинты:
         POST /decompile   - тело JSON {"jar_path": "...", "out_dir": "..."}
                              ИЛИ сырые байты .jar (Content-Type:
                              application/java-archive или
                              application/octet-stream) - тогда jar
                              сохраняется во временный файл, out_dir
                              вычисляется автоматически (или берётся из
                              query-параметра ?out_dir=...).
                              Ответ: JSON {"status": "ok", "out_dir": ...,
                              "stats": {...}} или {"status": "error", ...}.
         GET  /health      - {"status": "ok", "version": "..."}

КЛЮЧЕВОЕ ТРЕБОВАНИЕ (см. HANDOFF_3): ни один путь в этом файле НИКОГДА не
импортирует gui.py / не вызывает run_gui() - даже на Windows. main.py
проверяет --api/--json-output/--api-server ДО ветки "Windows -> GUI"
(см. main.py::main()).
"""
import json
import os
import sys
import tempfile
import time
import uuid
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer
from urllib.parse import urlparse, parse_qs

# На Windows с не-UTF8 консолью (напр. английская локаль - именно так
# настроены раннеры GitHub Actions windows-latest) sys.stdout по умолчанию
# кодируется в cp1252/cp437 и т.п., которые физически не умеют кириллицу -
# print() падает с UnicodeEncodeError на первой же русской букве в JSON
# (статистика, тексты ошибок, пути). Принудительно переключаем на UTF-8
# ДО того, как что-либо напечатано - это чинит и --api/--json-output (этот
# файл), и обычный консольный вывод main.py (та же правка стоит и там).
for _stream in (sys.stdout, sys.stderr):
    try:
        _stream.reconfigure(encoding="utf-8", errors="replace")
    except AttributeError:
        pass  # Python <3.7 или поток без reconfigure() - маловероятно, но не падать из-за этого

import main as _main  # process_jar_with_stats, NANO_DECOMPILER_VERSION


def stats_to_dict(stats):
    """ProjectStats -> plain dict, безопасный для json.dumps (никаких
    множеств/кортежей-ключей - library_names_hit это set, fallback_reasons
    может содержать None-ключ)."""
    return {
        "classes_total": stats.classes_total,
        "classes_parsed": stats.classes_parsed,
        "parse_errors": [{"name": n, "error": e} for n, e in stats.parse_errors],
        "library_classes_skipped": stats.library_classes_skipped,
        "library_names_hit": sorted(stats.library_names_hit),
        "total_methods": stats.total_methods,
        "decompiled_methods": stats.decompiled_methods,
        "fallback_methods": stats.fallback_methods,
        "fallback_reasons": {(k if k is not None else "unknown"): v
                              for k, v in stats.fallback_reasons.items()},
        "decompiled_pct": round(stats.pct(stats.decompiled_methods, stats.total_methods), 2),
        "bracket_issues": list(stats.bracket_issues),
        "import_conflicts": {k: v for k, v in stats.import_conflicts.items()},
        "synthetic_switchmap_classes_hidden": stats.synthetic_switchmap_classes_hidden,
    }


def decompile_silent(jar_path, out_dir):
    """Запускает process_jar_with_stats(), полностью проглатывая консольный
    вывод (баннер/прогресс-бар/цвет - в API-режиме они не нужны и только
    засоряли бы JSON, если бы утекли в stdout мимо перехвата). Возвращает
    dict, готовый под json.dumps."""
    import io
    from contextlib import redirect_stdout

    t0 = time.time()
    buf = io.StringIO()
    try:
        with redirect_stdout(buf):
            resolved_out_dir, stats = _main.process_jar_with_stats(jar_path, out_dir)
    except Exception as e:
        return {
            "status": "error",
            "error": f"{type(e).__name__}: {e}",
            "console_log": buf.getvalue(),
        }
    return {
        "status": "ok",
        "out_dir": resolved_out_dir,
        "elapsed_sec": round(time.time() - t0, 2),
        "stats": stats_to_dict(stats),
    }


def run_json_output(jar_path, out_dir):
    """--json-output: разовый вызов, печатает ТОЛЬКО JSON в stdout (даже при
    ошибке - status: error, а не traceback на экран), код возврата 0/1."""
    result = decompile_silent(jar_path, out_dir)
    print(json.dumps(result, ensure_ascii=False))
    sys.exit(0 if result.get("status") == "ok" else 1)


# ---------------------------------------------------------------------
# HTTP-сервер
# ---------------------------------------------------------------------
class _Handler(BaseHTTPRequestHandler):
    server_version = "NanoDecompilerAPI/1.1"

    def log_message(self, fmt, *args):
        # Свой лог вместо дефолтного (тот пишет в stderr в формате Apache
        # common log - оставляем короче, и не засоряем stdout - в API-режиме
        # stdout зарезервирован под ответы одноразовых вызовов, сервер же
        # пишет только в stderr).
        sys.stderr.write(f"[api] {self.address_string()} - {fmt % args}\n")

    def _send_json(self, code, payload):
        body = json.dumps(payload, ensure_ascii=False).encode("utf-8")
        self.send_response(code)
        self.send_header("Content-Type", "application/json; charset=utf-8")
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def do_GET(self):
        parsed = urlparse(self.path)
        if parsed.path == "/health":
            self._send_json(200, {"status": "ok", "version": _main.NANO_DECOMPILER_VERSION})
            return
        self._send_json(404, {"status": "error", "error": "неизвестный маршрут"})

    def do_POST(self):
        parsed = urlparse(self.path)
        if parsed.path != "/decompile":
            self._send_json(404, {"status": "error", "error": "неизвестный маршрут"})
            return

        length = int(self.headers.get("Content-Length", "0") or "0")
        raw_body = self.rfile.read(length) if length else b""
        content_type = (self.headers.get("Content-Type") or "").split(";")[0].strip()
        qs = parse_qs(parsed.query)

        tmp_jar_path = None
        try:
            if content_type == "application/json":
                try:
                    payload = json.loads(raw_body.decode("utf-8") or "{}")
                except json.JSONDecodeError as e:
                    self._send_json(400, {"status": "error", "error": f"невалидный JSON: {e}"})
                    return
                jar_path = payload.get("jar_path")
                out_dir = payload.get("out_dir")
                if not jar_path:
                    self._send_json(400, {"status": "error",
                                           "error": "поле 'jar_path' обязательно"})
                    return
                if not os.path.isfile(jar_path):
                    self._send_json(400, {"status": "error",
                                           "error": f"файл не найден: {jar_path}"})
                    return
            elif (content_type in ("application/java-archive", "application/octet-stream",
                                    "application/zip")
                  or (not content_type and raw_body[:2] == b"PK")):
                if not raw_body:
                    self._send_json(400, {"status": "error", "error": "пустое тело запроса"})
                    return
                tmp_dir = tempfile.gettempdir()
                tmp_jar_path = os.path.join(tmp_dir, f"nanodecompiler_upload_{uuid.uuid4().hex}.jar")
                with open(tmp_jar_path, "wb") as f:
                    f.write(raw_body)
                jar_path = tmp_jar_path
                out_dir = (qs.get("out_dir") or [None])[0]
            else:
                self._send_json(400, {
                    "status": "error",
                    "error": ("непонятное тело запроса - используйте Content-Type: "
                              "application/json с {\"jar_path\": ...} или пришлите "
                              "сырые байты .jar (application/java-archive)"),
                })
                return

            if not out_dir:
                base = os.path.splitext(os.path.basename(jar_path))[0]
                out_dir = os.path.join(tempfile.gettempdir(), base + "_decompiled")

            result = decompile_silent(jar_path, out_dir)
            self._send_json(200 if result.get("status") == "ok" else 500, result)
        finally:
            if tmp_jar_path and os.path.isfile(tmp_jar_path):
                try:
                    os.remove(tmp_jar_path)
                except OSError:
                    pass


def run_api_server(host="127.0.0.1", port=8791):
    server = ThreadingHTTPServer((host, port), _Handler)
    print(f"[api] {_main.NANO_DECOMPILER_VERSION} - HTTP API слушает на "
          f"http://{host}:{port}  (POST /decompile, GET /health)")
    print("[api] GUI НЕ будет открыт в этом режиме, даже на Windows.")
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("\n[api] Остановлено.")
    finally:
        server.server_close()
