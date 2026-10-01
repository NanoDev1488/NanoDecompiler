#include "archive_extractor.hpp"
#include "zip_reader.hpp"
#include "platform_detect.hpp"

#include <chrono>
#include <cstdlib>
#include <cstring>
#include <filesystem>
#include <fstream>
#include <iostream>
#include <sstream>
#include <algorithm>
#include <zlib.h>

namespace fs = std::filesystem;

namespace nd {

namespace {

std::string sanitize_subpath(const std::string& raw) {
    if (raw.empty()) return "";
    std::string s = raw;
    for (char& c : s) {
        if (c == '\\') c = '/';
    }
    std::string out;
    std::istringstream iss(s);
    std::string seg;
    while (std::getline(iss, seg, '/')) {
        while (!seg.empty() && (seg.back() == ' ' || seg.back() == '.' || seg.back() == '\t')) {
            seg.pop_back();
        }
        if (seg.empty() || seg == "." || seg == "..") continue;
        for (char& c : seg) {
            if (c == '<' || c == '>' || c == ':' || c == '"' || c == '|' || c == '?' || c == '*' || static_cast<unsigned char>(c) < 32) {
                c = '_';
            }
        }
        if (!out.empty()) out += "/";
        out += seg;
    }
    return out;
}

std::string create_temp_archive_directory(const std::string& archive_path) {
    std::string stem = fs::u8path(archive_path).stem().u8string();
    for (char& c : stem) {
        if (!std::isalnum(static_cast<unsigned char>(c)) && c != '_' && c != '-') {
            c = '_';
        }
    }
    auto ms = std::chrono::duration_cast<std::chrono::milliseconds>(
                  std::chrono::system_clock::now().time_since_epoch())
                  .count();
    fs::path temp_base = fs::temp_directory_path() / "NanoDecompiler" / "archives";
    fs::create_directories(temp_base);
    fs::path dir = temp_base / (std::to_string(ms) + "_" + stem);
    fs::create_directories(dir);
    return dir.u8string();
}

bool extract_zip_archive(const std::string& archive_path, const std::string& out_dir, std::string& err) {
    try {
        ZipReader zr(archive_path);
        auto names = zr.namelist();
        for (const auto& name : names) {
            std::string clean = sanitize_subpath(name);
            if (clean.empty()) continue;
            fs::path target = fs::u8path(out_dir) / fs::u8path(clean);
            if (name.back() == '/' || name.back() == '\\') {
                fs::create_directories(target);
                continue;
            }
            if (target.has_parent_path()) {
                fs::create_directories(target.parent_path());
            }
            auto data = zr.read(name);
            std::ofstream f(target, std::ios::binary);
            if (f) {
                f.write(reinterpret_cast<const char*>(data.data()), data.size());
            }
        }
        return true;
    } catch (const std::exception& e) {
        err = e.what();
        return false;
    }
}

bool extract_tar_stream(gzFile gz, const std::string& out_dir, std::string& err) {
    char header[512];
    while (true) {
        int read_bytes = gzread(gz, header, 512);
        if (read_bytes == 0) break;
        if (read_bytes < 512) {
            err = "Неполный tar-блок заголовка";
            return false;
        }

        // Проверяем на нулевой блок (признак окончания tar)
        bool all_zero = true;
        for (int i = 0; i < 512; ++i) {
            if (header[i] != 0) {
                all_zero = false;
                break;
            }
        }
        if (all_zero) break;

        char name_buf[101] = {0};
        std::memcpy(name_buf, header, 100);
        std::string raw_name(name_buf);

        // Префикс ustar
        if (std::memcmp(header + 257, "ustar", 5) == 0) {
            char prefix_buf[156] = {0};
            std::memcpy(prefix_buf, header + 345, 155);
            if (prefix_buf[0] != 0) {
                raw_name = std::string(prefix_buf) + "/" + raw_name;
            }
        }

        char size_buf[13] = {0};
        std::memcpy(size_buf, header + 124, 12);
        uint64_t file_size = std::strtoull(size_buf, nullptr, 8);
        char typeflag = header[156];

        std::string clean = sanitize_subpath(raw_name);
        if (clean.empty()) {
            // Пропускаем содержимое
            uint64_t pad = ((file_size + 511) / 512) * 512;
            std::vector<char> skip_buf(4096);
            while (pad > 0) {
                int to_read = static_cast<int>(std::min<uint64_t>(pad, skip_buf.size()));
                int n = gzread(gz, skip_buf.data(), to_read);
                if (n <= 0) break;
                pad -= n;
            }
            continue;
        }

        fs::path target = fs::u8path(out_dir) / fs::u8path(clean);

        if (typeflag == '5' || raw_name.back() == '/') {
            fs::create_directories(target);
        } else {
            if (target.has_parent_path()) {
                fs::create_directories(target.parent_path());
            }
            std::ofstream out(target, std::ios::binary);
            uint64_t remaining = file_size;
            std::vector<char> chunk(8192);
            while (remaining > 0) {
                int to_read = static_cast<int>(std::min<uint64_t>(remaining, chunk.size()));
                int n = gzread(gz, chunk.data(), to_read);
                if (n <= 0) break;
                if (out) out.write(chunk.data(), n);
                remaining -= n;
            }
            // Пропускаем padding до 512 байт
            uint64_t pad = ((file_size + 511) / 512) * 512 - file_size;
            while (pad > 0) {
                char dummy[512];
                int to_read = static_cast<int>(std::min<uint64_t>(pad, 512));
                int n = gzread(gz, dummy, to_read);
                if (n <= 0) break;
                pad -= n;
            }
        }
    }
    return true;
}

bool extract_tar_archive(const std::string& archive_path, const std::string& out_dir, std::string& err) {
    gzFile gz = gzopen(archive_path.c_str(), "rb");
    if (!gz) {
        err = "Не удалось открыть tar.gz архив: " + archive_path;
        return false;
    }
    bool ok = extract_tar_stream(gz, out_dir, err);
    gzclose(gz);
    return ok;
}

bool extract_external_archive(const std::string& archive_path, const std::string& out_dir, std::string& err) {
    // Ищем 7za в текущей папке или рядом с exe
    std::vector<std::string> tools = {
        "7za",
        "7za.exe",
        "7z",
        "7z.exe",
        "unrar",
        "unrar.exe"
    };

    std::string tool_cmd;
    for (const auto& t : tools) {
        std::error_code ec;
        if (fs::exists(t, ec)) {
            tool_cmd = t;
            break;
        }
    }

    if (tool_cmd.empty()) {
        tool_cmd = "7za"; // fallback на PATH
    }

    std::string cmd = tool_cmd + " x -y -o\"" + out_dir + "\" \"" + archive_path + "\" > nul 2>&1";
#ifndef _WIN32
    cmd = tool_cmd + " x -y -o\"" + out_dir + "\" \"" + archive_path + "\" > /dev/null 2>&1";
#endif
    int rc = std::system(cmd.c_str());
    if (rc != 0) {
        err = "Команда распаковщика (" + tool_cmd + ") завершилась с ошибкой " + std::to_string(rc);
        return false;
    }
    return true;
}

}  // namespace

bool is_supported_archive_file(const std::string& path) {
    std::string lower = path;
    for (char& c : lower) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));

    if (lower.size() >= 4 && lower.substr(lower.size() - 4) == ".zip") return true;
    if (lower.size() >= 7 && lower.substr(lower.size() - 7) == ".tar.gz") return true;
    if (lower.size() >= 4 && lower.substr(lower.size() - 4) == ".tgz") return true;
    if (lower.size() >= 4 && lower.substr(lower.size() - 4) == ".tar") return true;
    if (lower.size() >= 3 && lower.substr(lower.size() - 3) == ".7z") return true;
    if (lower.size() >= 5 && lower.substr(lower.size() - 5) == ".7zip") return true;
    if (lower.size() >= 4 && lower.substr(lower.size() - 4) == ".rar") return true;

    // Проверяем сигнатуры байт
    std::ifstream f(fs::u8path(path), std::ios::binary);
    if (!f) return false;
    char sig[8] = {0};
    f.read(sig, sizeof(sig));
    if (f.gcount() >= 4) {
        // ZIP
        if (sig[0] == 'P' && sig[1] == 'K' && sig[2] == 0x03 && sig[3] == 0x04) return true;
        // GZIP
        if (static_cast<unsigned char>(sig[0]) == 0x1f && static_cast<unsigned char>(sig[1]) == 0x8b) return true;
        // 7Z ('7' 'z' \xbc \xaf)
        if (sig[0] == '7' && sig[1] == 'z' && static_cast<unsigned char>(sig[2]) == 0xbc && static_cast<unsigned char>(sig[3]) == 0xaf) return true;
        // RAR ('R' 'a' 'r' '!')
        if (sig[0] == 'R' && sig[1] == 'a' && sig[2] == 'r' && sig[3] == '!') return true;
    }

    return false;
}

ArchiveExtractionSummary extract_and_scan_archive(const std::string& archive_path, const std::string& custom_temp_dir) {
    ArchiveExtractionSummary res;
    res.temp_dir = !custom_temp_dir.empty() ? custom_temp_dir : create_temp_archive_directory(archive_path);

    std::string lower = archive_path;
    for (char& c : lower) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));

    bool extracted = false;
    std::string err;

    if (lower.size() >= 7 && lower.substr(lower.size() - 7) == ".tar.gz") {
        extracted = extract_tar_archive(archive_path, res.temp_dir, err);
    } else if (lower.size() >= 4 && (lower.substr(lower.size() - 4) == ".tgz" || lower.substr(lower.size() - 4) == ".tar")) {
        extracted = extract_tar_archive(archive_path, res.temp_dir, err);
    } else if (lower.size() >= 4 && lower.substr(lower.size() - 4) == ".rar") {
        extracted = extract_external_archive(archive_path, res.temp_dir, err);
    } else if (lower.size() >= 3 && (lower.substr(lower.size() - 3) == ".7z" || (lower.size() >= 5 && lower.substr(lower.size() - 5) == ".7zip"))) {
        extracted = extract_external_archive(archive_path, res.temp_dir, err);
    } else {
        // По умолчанию пробуем встроенный ZIP-распаковщик
        extracted = extract_zip_archive(archive_path, res.temp_dir, err);
        if (!extracted) {
            // Fallback на внешние утилиты
            extracted = extract_external_archive(archive_path, res.temp_dir, err);
        }
    }

    if (!extracted) {
        res.ok = false;
        res.error = err.empty() ? "Не удалось распаковать архив" : err;
        return res;
    }

    res.ok = true;

    // Рекурсивный поиск .jar файлов
    std::vector<std::string> jar_paths;
    std::error_code ec;
    for (const auto& entry : fs::recursive_directory_iterator(fs::u8path(res.temp_dir), ec)) {
        if (ec) break;
        if (entry.is_regular_file()) {
            std::string ext = entry.path().extension().u8string();
            std::string ext_lower = ext;
            for (char& c : ext_lower) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
            if (ext_lower == ".jar") {
                jar_paths.push_back(entry.path().u8string());
            }
        }
    }

    res.total_jars = jar_paths.size();

    // Анализируем каждый .jar
    for (const auto& jp : jar_paths) {
        ArchivePluginEntry entry;
        entry.full_path = jp;
        entry.filename = fs::u8path(jp).filename().u8string();
        entry.rel_path = fs::relative(fs::u8path(jp), fs::u8path(res.temp_dir), ec).u8string();
        entry.size_bytes = fs::file_size(fs::u8path(jp), ec);

        try {
            ZipReader zr(jp);
            auto names = zr.namelist();

            // Проверка на серверное ядро
            bool is_paperclip = false;
            bool is_bundler = false;
            bool is_mc_server = false;
            bool is_craftbukkit_core = false;

            for (const auto& n : names) {
                if (n.rfind("io/papermc/paperclip", 0) == 0 || n.rfind("META-INF/versions/", 0) == 0) {
                    is_paperclip = true;
                } else if (n.rfind("net/minecraft/bundler", 0) == 0) {
                    is_bundler = true;
                } else if (n == "net/minecraft/server/MinecraftServer.class" || n == "net/minecraft/server/Main.class") {
                    is_mc_server = true;
                } else if (n == "org/bukkit/craftbukkit/Main.class") {
                    is_craftbukkit_core = true;
                }
            }

            bool has_plugin_yml = std::find(names.begin(), names.end(), "plugin.yml") != names.end();
            std::string fn_lower = entry.filename;
            for (char& c : fn_lower) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
            bool is_server_name = (fn_lower.rfind("paper-", 0) == 0 || fn_lower.rfind("spigot-", 0) == 0 ||
                                  fn_lower.rfind("purpur-", 0) == 0 || fn_lower.rfind("folia-", 0) == 0 ||
                                  fn_lower == "server.jar");

            if (is_paperclip || is_bundler || is_mc_server || (is_craftbukkit_core && !has_plugin_yml) || (is_server_name && !has_plugin_yml)) {
                entry.is_server_core = true;
                entry.is_plugin = false;
                if (is_paperclip) entry.core_reason = "Paperclip загрузчик (Paper/Purpur/Folia)";
                else if (is_bundler) entry.core_reason = "Vanilla / Bundler загрузчик ядра";
                else if (is_mc_server) entry.core_reason = "Серверное ядро net.minecraft.server";
                else if (is_craftbukkit_core) entry.core_reason = "Серверное ядро CraftBukkit/Spigot";
                else entry.core_reason = "Серверное ядро Minecraft";
                res.server_cores.push_back(std::move(entry));
                continue;
            }

            // Проверка на плагин через detect_platform
            auto pinfo = detect_platform(names, [&zr](const std::string& path) -> std::optional<std::string> {
                try {
                    auto data = zr.read(path);
                    return std::string(data.begin(), data.end());
                } catch (...) {
                    return std::nullopt;
                }
            });

            entry.platform = pinfo;
            if (pinfo.kind == PlatformKind::Bukkit || pinfo.kind == PlatformKind::Paper ||
                pinfo.kind == PlatformKind::Bungee || pinfo.kind == PlatformKind::Velocity ||
                pinfo.kind == PlatformKind::Sponge) {
                entry.is_plugin = true;
                entry.is_server_core = false;
                res.plugins.push_back(std::move(entry));
            } else {
                entry.is_plugin = false;
                entry.is_server_core = false;
                res.skipped_libraries++;
            }
        } catch (...) {
            res.skipped_libraries++;
        }
    }

    return res;
}

}  // namespace nd
