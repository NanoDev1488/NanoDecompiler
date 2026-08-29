// stats_json.cpp - см. stats_json.hpp.
#include "stats_json.hpp"

#include <cmath>
#include <cstdio>
#include <sstream>

namespace nd {

namespace {

std::string esc(const std::string& s) {
    std::string out;
    out.reserve(s.size() + 2);
    for (unsigned char c : s) {
        switch (c) {
            case '"':
                out += "\\\"";
                break;
            case '\\':
                out += "\\\\";
                break;
            case '\n':
                out += "\\n";
                break;
            case '\r':
                out += "\\r";
                break;
            case '\t':
                out += "\\t";
                break;
            default:
                if (c < 0x20) {
                    char buf[8];
                    std::snprintf(buf, sizeof(buf), "\\u%04x", c);
                    out += buf;
                } else {
                    out += static_cast<char>(c);
                }
        }
    }
    return out;
}

std::string js(const std::string& s) { return "\"" + esc(s) + "\""; }
std::string jn(const std::optional<std::string>& s) { return s.has_value() ? js(*s) : "null"; }

std::string strarr(const std::vector<std::string>& v) {
    std::string out = "[";
    for (size_t i = 0; i < v.size(); ++i) {
        if (i) out += ",";
        out += js(v[i]);
    }
    out += "]";
    return out;
}

std::string round2(double v) {
    char buf[64];
    std::snprintf(buf, sizeof(buf), "%.2f", std::round(v * 100.0) / 100.0);
    return buf;
}

std::string malware_findings_json(const std::vector<MalwareFinding>& findings) {
    std::string out = "[";
    for (size_t i = 0; i < findings.size(); ++i) {
        if (i) out += ",";
        auto& f = findings[i];
        out += "{\"severity\":" + js(f.severity) + ",\"description\":" + js(f.description) + ",\"where\":" + js(f.where) + "}";
    }
    out += "]";
    return out;
}

std::string legitimacy_source_json(const LegitimacySourceResult& r) {
    std::string out = "{\"checked\":" + std::string(r.checked ? "true" : "false");
    out += ",\"found\":" + std::string(r.found ? "true" : "false");
    out += ",\"candidates\":[";
    for (size_t i = 0; i < r.candidates.size(); ++i) {
        if (i) out += ",";
        auto& c = r.candidates[i];
        out += "{\"full_name\":" + js(c.full_name) + ",\"url\":" + js(c.url) + ",\"stars\":" + std::to_string(c.stars) +
               ",\"sha256_hex\":" + jn(c.sha256_hex) + "}";
    }
    out += "]}";
    return out;
}

std::string hash_comparison_json(const std::optional<HashComparisonResult>& hc) {
    if (!hc.has_value()) return "null";
    return "{\"matching\":" + strarr(hc->matching) + ",\"mismatching\":" + strarr(hc->mismatching) + "}";
}

std::string legitimacy_json(const std::optional<LegitimacyCheckResult>& leg) {
    if (!leg.has_value()) return "null";
    std::string out = "{";
    out += "\"plugin_yml_fields\":{\"website\":" + jn(leg->plugin_yml_fields.website);
    out += ",\"authors\":" + strarr(leg->plugin_yml_fields.authors) + "}";
    out += ",\"github\":" + legitimacy_source_json(leg->github);
    out += ",\"modrinth\":" + legitimacy_source_json(leg->modrinth);
    out += ",\"spigot\":" + legitimacy_source_json(leg->spigot);
    out += ",\"ruspigot\":" + legitimacy_source_json(leg->ruspigot);
    out += ",\"hash_comparison\":" + hash_comparison_json(leg->hash_comparison);
    out += "}";
    return out;
}

std::string stats_body_json(const ProjectStats& stats) {
    std::ostringstream o;
    o << "{";
    o << "\"classes_total\":" << stats.classes_total << ",";
    o << "\"classes_parsed\":" << stats.classes_parsed << ",";
    o << "\"parse_errors\":[";
    for (size_t i = 0; i < stats.parse_errors.size(); ++i) {
        if (i) o << ",";
        o << "{\"name\":" << js(stats.parse_errors[i].first) << ",\"error\":" << js(stats.parse_errors[i].second) << "}";
    }
    o << "],";
    o << "\"library_classes_skipped\":" << stats.library_classes_skipped << ",";
    o << "\"library_names_hit\":[";
    {
        size_t i = 0;
        for (auto& n : stats.library_names_hit) {
            if (i++) o << ",";
            o << js(n);
        }
    }
    o << "],";
    o << "\"total_methods\":" << stats.total_methods << ",";
    o << "\"decompiled_methods\":" << stats.decompiled_methods << ",";
    o << "\"fallback_methods\":" << stats.fallback_methods << ",";
    o << "\"fallback_reasons\":{";
    for (size_t i = 0; i < stats.fallback_reasons.size(); ++i) {
        if (i) o << ",";
        auto& [reason, count] = stats.fallback_reasons[i];
        o << js(reason.has_value() ? *reason : std::string("unknown")) << ":" << count;
    }
    o << "},";
    o << "\"decompiled_pct\":" << round2(stats.pct(stats.decompiled_methods, stats.total_methods)) << ",";
    o << "\"bracket_issues\":" << strarr(stats.bracket_issues) << ",";
    o << "\"import_conflicts\":{";
    for (size_t i = 0; i < stats.import_conflicts.size(); ++i) {
        if (i) o << ",";
        auto& [simple, dotted_list] = stats.import_conflicts[i];
        o << js(simple) << ":" << strarr(dotted_list);
    }
    o << "},";
    o << "\"synthetic_switchmap_classes_hidden\":" << stats.synthetic_switchmap_classes_hidden << ",";
    o << "\"junk_catches_removed\":" << stats.junk_catches_removed;
    o << "}";
    return o.str();
}

}  // namespace

std::string jar_process_result_to_json(const JarProcessResult& jr, const std::string& out_dir, double elapsed_sec) {
    // Отдельная ветка ДО обычного "ok" - мод распознан и декомпиляция
    // намеренно не выполнялась (см. process_jar.cpp, раздел 1.5), это не
    // ошибка движка и не "ok" (jr.stats тут пустая/неактуальная - методы
    // не разбирались вообще). status:"mod_rejected" - отдельная строка,
    // не "ok"/"error", чтобы GUI мог показать точно нужное сообщение
    // ("временно моды не декомпилируются"), а не общий текст ошибки.
    if (jr.mod_rejected) {
        std::ostringstream mo;
        mo << "{\"status\":\"mod_rejected\",\"out_dir\":" << js(out_dir) << ",\"elapsed_sec\":" << round2(elapsed_sec)
           << ",\"platform\":" << js(jr.platform.kind_label())
           << ",\"reason\":" << js(jr.mod_rejected_reason.value_or("")) << "}";
        return mo.str();
    }
    std::ostringstream o;
    o << "{\"status\":\"ok\",\"out_dir\":" << js(out_dir) << ",\"elapsed_sec\":" << round2(elapsed_sec) << ",\"stats\":{";
    // stats_body_json уже возвращает полный {...} - вставляем его поля без
    // внешних скобок и дописываем 4 поля, которые в python лежали НА stats,
    // а у нас - отдельно в JarProcessResult (см. hpp).
    std::string body = stats_body_json(jr.stats);
    o << body.substr(1, body.size() - 2);  // без внешних { }
    o << ",\"malware_findings\":" << malware_findings_json(jr.malware_findings);
    o << ",\"decrypted_strings_owner\":" << jn(jr.decrypted_strings_owner);
    o << ",\"decrypted_strings_count\":" << jr.decrypted_strings_count;
    o << ",\"legitimacy\":" << legitimacy_json(jr.legitimacy);
    o << ",\"platform\":" << js(jr.platform.kind_label());
    o << "}}";
    return o.str();
}

std::string json_error_response(const std::string& error_message) { return "{\"status\":\"error\",\"error\":" + js(error_message) + "}"; }

}  // namespace nd
