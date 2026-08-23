// jar_summary.cpp - см. jar_summary.hpp.
#include "jar_summary.hpp"

#include <cstdio>
#include <filesystem>
#include <set>

#include "zip_reader.hpp"

namespace fs = std::filesystem;

namespace nd {

namespace {

std::string java_version_from_major(int major) {
    char buf[32];
    if (major <= 48) {
        std::snprintf(buf, sizeof(buf), "Java 1.%d", major - 44);
    } else {
        std::snprintf(buf, sizeof(buf), "Java %d", major - 44);
    }
    return buf;
}

std::string format_size(uint64_t bytes) {
    char buf[64];
    if (bytes >= 1024 * 1024) {
        std::snprintf(buf, sizeof(buf), "%.1f МБ", bytes / 1024.0 / 1024.0);
    } else {
        std::snprintf(buf, sizeof(buf), "%.1f КБ", bytes / 1024.0);
    }
    return buf;
}

std::string json_escape(const std::string& s) {
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

}  // namespace

JarSummary jar_summary(const std::string& jar_path) {
    JarSummary info;
    info.name = fs::path(jar_path).filename().string();

    try {
        info.size = format_size(static_cast<uint64_t>(fs::file_size(jar_path)));
    } catch (...) {
        // остаётся "?" - см. hpp
    }

    try {
        ZipReader zr(jar_path);
        const auto& names = zr.namelist();
        std::vector<std::string> class_names;
        std::set<std::string> pkgs;
        for (auto& n : names) {
            if (n.size() >= 6 && n.substr(n.size() - 6) == ".class" && n.find("module-info") == std::string::npos) {
                class_names.push_back(n);
                auto pos = n.find_last_of('/');
                if (pos != std::string::npos) pkgs.insert(n.substr(0, pos));
            }
        }
        info.classes = static_cast<int>(class_names.size());
        info.packages = static_cast<int>(pkgs.size());

        if (!class_names.empty()) {
            try {
                auto head = zr.read(class_names[0]);
                if (head.size() >= 8) {
                    int major = (static_cast<int>(head[6]) << 8) | static_cast<int>(head[7]);
                    info.java = java_version_from_major(major);
                }
            } catch (...) {
                // остаётся "?"
            }
        }

        bool has_plugin_yml = false;
        for (auto& n : names)
            if (n == "plugin.yml") {
                has_plugin_yml = true;
                break;
            }
        if (has_plugin_yml) {
            try {
                auto data = zr.read("plugin.yml");
                std::string text(data.begin(), data.end());
                size_t pos = 0;
                while (pos < text.size()) {
                    size_t eol = text.find('\n', pos);
                    std::string line = text.substr(pos, eol == std::string::npos ? std::string::npos : eol - pos);
                    pos = (eol == std::string::npos) ? text.size() : eol + 1;
                    size_t b = line.find_first_not_of(" \t\r");
                    std::string trimmed = (b == std::string::npos) ? "" : line.substr(b);
                    if (trimmed.rfind("name:", 0) == 0) {
                        std::string val = trimmed.substr(5);
                        size_t vb = val.find_first_not_of(" \t\r");
                        size_t ve = val.find_last_not_of(" \t\r");
                        val = (vb == std::string::npos) ? "" : val.substr(vb, ve - vb + 1);
                        while (!val.empty() && (val.front() == '\'' || val.front() == '"')) val.erase(val.begin());
                        while (!val.empty() && (val.back() == '\'' || val.back() == '"')) val.pop_back();
                        info.plugin_name = val;
                        break;
                    }
                }
            } catch (...) {
                // остаётся nullopt
            }
        }
    } catch (...) {
        // не zip / битый файл - остаются дефолты (classes=0, packages=0, java="?")
    }

    return info;
}

std::string jar_summary_to_json(const JarSummary& s) {
    std::string out = "{";
    out += "\"name\":\"" + json_escape(s.name) + "\",";
    out += "\"size\":\"" + json_escape(s.size) + "\",";
    out += "\"classes\":" + std::to_string(s.classes) + ",";
    out += "\"packages\":" + std::to_string(s.packages) + ",";
    out += "\"java\":\"" + json_escape(s.java) + "\",";
    out += "\"plugin_name\":" + (s.plugin_name.has_value() ? ("\"" + json_escape(*s.plugin_name) + "\"") : "null");
    out += "}";
    return out;
}

}  // namespace nd
