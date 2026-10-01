#pragma once

#include <cstdint>
#include <string>
#include <vector>
#include "platform_detect.hpp"

namespace nd {

struct ArchivePluginEntry {
    std::string filename;
    std::string full_path;
    std::string rel_path;
    uint64_t size_bytes = 0;
    PlatformInfo platform;
    bool is_plugin = false;
    bool is_server_core = false;
    std::string core_reason;
};

struct ArchiveExtractionSummary {
    bool ok = false;
    std::string error;
    std::string temp_dir;
    std::vector<ArchivePluginEntry> plugins;
    std::vector<ArchivePluginEntry> server_cores;
    size_t skipped_libraries = 0;
    size_t total_jars = 0;
};

/** Проверяет, поддерживается ли архив движком (.zip, .tar.gz, .tgz, .tar, .7z, .rar) */
bool is_supported_archive_file(const std::string& path);

/** Распаковывает архив в temp-папку и находит Bukkit/Spigot/Paper/Bungee/Velocity плагины */
ArchiveExtractionSummary extract_and_scan_archive(const std::string& archive_path, const std::string& custom_temp_dir = "");

}  // namespace nd
