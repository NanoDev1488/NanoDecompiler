// dump_pom.cpp - строит pom.xml для jar'а (с фиксированным синтетическим
// списком external_dotted_names для воспроизводимости) - для diff против
// dump_pom_ref.py (HANDOFF_33).
#include <cstdio>
#include <fstream>
#include <sstream>

#include "pom_builder.hpp"
#include "zip_reader.hpp"

using namespace nd;

int main(int argc, char** argv) {
    if (argc < 2) return 2;
    std::string jar_path = argv[1];
    try {
        ZipReader z(jar_path);
        std::string plugin_yml_text;
        try {
            auto raw = z.read("plugin.yml");
            plugin_yml_text = std::string(raw.begin(), raw.end());
        } catch (...) {
        }
        std::vector<std::string> external = {
            "org.bukkit.Bukkit", "com.google.common.collect.Lists",
            "org.apache.commons.lang3.StringUtils", "com.zaxxer.hikari.HikariDataSource",
            "java.util.List", "org.jetbrains.annotations.NotNull",
        };
        auto result = build_pom(jar_path, plugin_yml_text, external, z.namelist(), z);
        printf("SOURCE=%s\n", result.source.c_str());
        printf("%s", result.pom_xml.c_str());
    } catch (const std::exception& ex) {
        printf("ERROR: %s\n", ex.what());
    }
    return 0;
}
