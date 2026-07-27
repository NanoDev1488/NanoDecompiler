# -*- coding: utf-8 -*-
"""
Проверка легитимности плагина - дополнение к malware_scan.py (см.
HANDOFF_3_TODO.md, пункт 3, и последующее уточнение пользователя: не
тумблеры "включить проверку", а ВСЕГДА проверять сразу по всем
источникам). Источники:

1. `fields_from_plugin_yml()` - БЕСПЛАТНО, без сети. website/author(s) из
   уже распарсенного `plugin.yml`.
2. `check_github()` - `api.github.com/search/repositories`, публичный,
   без ключа (rate-limit 10/мин без токена - терпимо для одного вызова
   на декомпиляцию).
3. `check_modrinth()` - `api.modrinth.com/v2/search`, публичный,
   документированный, без ключа.
4. `check_spigot()` - `api.spiget.org/v2` (НЕОФИЦИАЛЬНЫЙ сторонний сервис
   для spigotmc.org - официального публичного API у SpigotMC нет, см.
   HANDOFF_3).
5. `check_ruspigot()` - у русскоязычного форума (spigotmc.ru, форум на
   XenForo) НЕТ ни официального, ни стороннего документированного JSON
   API (в отличие от spiget для англоязычного SpigotMC) - делаем
   best-effort: GET по странице поиска ресурсов и проверяем, встречается
   ли имя плагина в HTML списка результатов. Это ХРУПКО (ломается при
   любой смене вёрстки форума) - помечено степенью доверия ниже, чем у
   остальных трёх источников с нормальным JSON API.

Все четыре сетевых источника запускаются ПАРАЛЛЕЛЬНО (потоки - I/O-bound,
GIL не мешает) с коротким таймаутом каждый, чтобы суммарная задержка была
близка к самому медленному ответу, а не к сумме всех четырёх.

ВАЖНО: ничего из этого не блокирует декомпиляцию и не является заменой
malware_scan.py - только ДОПОЛНИТЕЛЬНЫЙ контекст, решение остаётся за
человеком. Любая сетевая/парсинг ошибка - тихо возвращает "не проверено",
никогда не бросает наружу (не должно иметь возможность сорвать основной
пайплайн декомпиляции).
"""
import json
import re
import threading
import urllib.request
import urllib.parse


def fields_from_plugin_yml(plugin_yml_text):
    """Достаёт website/author(s) прямо из сырого текста plugin.yml простой
    регуляркой (полноценный YAML-парсер - лишняя зависимость ради трёх
    полей). Возвращает dict, отсутствующие поля - None/[]."""
    result = {"website": None, "authors": []}
    if not plugin_yml_text:
        return result
    m = re.search(r"^website:\s*['\"]?([^'\"\n]+)['\"]?\s*$", plugin_yml_text, re.M)
    if m:
        result["website"] = m.group(1).strip()
    m = re.search(r"^author:\s*['\"]?([^'\"\n]+)['\"]?\s*$", plugin_yml_text, re.M)
    if m:
        result["authors"] = [m.group(1).strip()]
    else:
        m = re.search(r"^authors:\s*\[([^\]]*)\]", plugin_yml_text, re.M)
        if m:
            result["authors"] = [a.strip().strip("'\"") for a in m.group(1).split(",") if a.strip()]
    return result


def _http_get(url, timeout=4.0, accept="application/json"):
    req = urllib.request.Request(url, headers={
        "User-Agent": "Mozilla/5.0 (NanoDecompiler-LegitimacyCheck/1.1)",
        "Accept": accept,
    })
    try:
        with urllib.request.urlopen(req, timeout=timeout) as resp:
            return resp.read().decode("utf-8", errors="replace")
    except Exception:
        return None


def _http_get_json(url, timeout=4.0):
    raw = _http_get(url, timeout=timeout, accept="application/json")
    if raw is None:
        return None
    try:
        return json.loads(raw)
    except Exception:
        return None


_EMPTY = {"checked": False, "found": False, "candidates": []}


def check_github(plugin_name, timeout=4.0):
    """Ищет открытый репозиторий с похожим названием. Возвращает
    {"checked": bool, "found": bool, "candidates": [{"full_name","url","stars"}]}
    - checked=False значит сеть/API недоступны или ответ не распознан (не
    значит "не найдено" - это отдельный случай found=False,checked=True)."""
    out = dict(_EMPTY, candidates=[])
    if not plugin_name or not plugin_name.strip():
        return out
    q = urllib.parse.quote(f"{plugin_name} in:name")
    data = _http_get_json(f"https://api.github.com/search/repositories?q={q}&per_page=5", timeout)
    if not isinstance(data, dict) or "items" not in data:
        return out
    out["checked"] = True
    for item in data.get("items", [])[:5]:
        try:
            out["candidates"].append({
                "full_name": item["full_name"],
                "url": item["html_url"],
                "stars": item.get("stargazers_count", 0),
            })
        except Exception:
            continue
    out["found"] = len(out["candidates"]) > 0
    return out


def check_modrinth(plugin_name, timeout=4.0):
    """Ищет проект на Modrinth с похожим названием. Тот же формат ответа,
    что check_github()."""
    out = dict(_EMPTY, candidates=[])
    if not plugin_name or not plugin_name.strip():
        return out
    q = urllib.parse.quote(plugin_name)
    data = _http_get_json(f"https://api.modrinth.com/v2/search?query={q}&limit=5", timeout)
    if not isinstance(data, dict) or "hits" not in data:
        return out
    out["checked"] = True
    for item in data.get("hits", [])[:5]:
        try:
            out["candidates"].append({
                "full_name": item["title"],
                "url": f"https://modrinth.com/plugin/{item['slug']}",
                "stars": item.get("downloads", 0),
            })
        except Exception:
            continue
    out["found"] = len(out["candidates"]) > 0
    return out


def check_spigot(plugin_name, timeout=4.0):
    """Ищет ресурс на SpigotMC.org через НЕОФИЦИАЛЬНЫЙ api.spiget.org
    (см. HANDOFF_3 - у SpigotMC самого официального публичного API нет).
    Тот же формат ответа, что check_github()/check_modrinth()."""
    out = dict(_EMPTY, candidates=[])
    if not plugin_name or not plugin_name.strip():
        return out
    q = urllib.parse.quote(plugin_name)
    data = _http_get_json(f"https://api.spiget.org/v2/search/resources/{q}?field=name&size=5", timeout)
    if not isinstance(data, list):
        return out
    out["checked"] = True
    for item in data[:5]:
        try:
            rid = item["id"]
            out["candidates"].append({
                "full_name": item.get("name", f"resource #{rid}"),
                "url": f"https://www.spigotmc.org/resources/{rid}/",
                "stars": item.get("downloads", 0),
            })
        except Exception:
            continue
    out["found"] = len(out["candidates"]) > 0
    return out


_RUSPIGOT_RESULT_RE = re.compile(
    r'<a[^>]+class="[^"]*resource-tile-title[^"]*"[^>]+href="([^"]+)"[^>]*>\s*([^<]+?)\s*</a>',
    re.I,
)


def check_ruspigot(plugin_name, timeout=4.0):
    """Best-effort проверка на spigotmc.ru (RuSpigot) - см. предупреждение
    в шапке файла: нет ни официального, ни стороннего JSON API, поэтому
    парсим HTML страницы поиска напрямую. checked=False здесь может
    означать и "нет сети", и "вёрстка форума изменилась и regex больше не
    матчится" - в обоих случаях честно молчим, а не гадаем."""
    out = dict(_EMPTY, candidates=[])
    if not plugin_name or not plugin_name.strip():
        return out
    q = urllib.parse.quote(plugin_name)
    html = _http_get(f"https://spigotmc.ru/resources/?q={q}", timeout=timeout, accept="text/html")
    if html is None:
        return out
    out["checked"] = True
    for href, title in _RUSPIGOT_RESULT_RE.findall(html)[:5]:
        title = title.strip()
        if not title:
            continue
        url = href if href.startswith("http") else "https://spigotmc.ru" + href
        out["candidates"].append({"full_name": title, "url": url, "stars": 0})
    out["found"] = len(out["candidates"]) > 0
    return out


_SOURCES = (
    ("github", check_github),
    ("modrinth", check_modrinth),
    ("spigot", check_spigot),
    ("ruspigot", check_ruspigot),
)


def run_legitimacy_check(plugin_name, plugin_yml_text):
    """Точка входа, вызывается из main.py. ВСЕГДА проверяет все четыре
    сетевых источника (GitHub/Modrinth/SpigotMC/RuSpigot) параллельно -
    без тумблеров, по прямому запросу пользователя. Возвращает dict,
    готовый для JSON/консоли:
    {"plugin_yml_fields": {...}, "github": {...}, "modrinth": {...},
     "spigot": {...}, "ruspigot": {...}}
    Каждый элемент - {"checked", "found", "candidates"} (см. check_*())."""
    result = {"plugin_yml_fields": fields_from_plugin_yml(plugin_yml_text)}
    for key, _ in _SOURCES:
        result[key] = dict(_EMPTY, candidates=[])

    def _run(key, fn):
        try:
            result[key] = fn(plugin_name)
        except Exception:
            result[key] = dict(_EMPTY, candidates=[])

    threads = [threading.Thread(target=_run, args=(key, fn)) for key, fn in _SOURCES]
    for t in threads:
        t.start()
    for t in threads:
        t.join(timeout=8.0)  # общий верхний предел на случай зависшего сокета
    return result


_LABELS = {"github": "GitHub", "modrinth": "Modrinth", "spigot": "SpigotMC", "ruspigot": "RuSpigot"}


def format_for_console(result):
    """Человекочитаемый текст для консоли, аналогично
    malware_scan.format_findings_for_console(). Возвращает None, если
    вообще нечего показать."""
    if not result:
        return None
    lines = []
    fields = result.get("plugin_yml_fields") or {}
    if fields.get("website") or fields.get("authors"):
        lines.append("[*] Поля в plugin.yml:")
        if fields.get("website"):
            lines.append(f"    website: {fields['website']}")
        if fields.get("authors"):
            lines.append(f"    author(s): {', '.join(fields['authors'])}")
    for key, label in _LABELS.items():
        r = result.get(key)
        if r is None:
            continue
        if not r["checked"]:
            lines.append(f"[*] {label}: проверка не удалась (нет сети или сервис недоступен)")
        elif r["found"]:
            lines.append(f"[*] {label}: найдены похожие проекты -")
            for c in r["candidates"]:
                lines.append(f"    {c['full_name']} ({c['url']})")
        else:
            lines.append(f"[*] {label}: похожих проектов не найдено")
    return "\n".join(lines) if lines else None
