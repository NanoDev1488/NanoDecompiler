// platform_detect.hpp - определение платформы .jar по манифест-файлу
// внутри него, ДО декомпиляции (см. HANDOFF о поддержке всех платформ:
// Bukkit/Spigot/Paper/BungeeCord/Velocity + отдельное распознавание
// Fabric/Forge/NeoForge МОДОВ, которые НЕ являются серверными плагинами
// и временно не декомпилируются).
//
// Раньше движок узнавал ТОЛЬКО plugin.yml (Bukkit/Spigot/старый Paper) -
// остальные форматы манифестов вообще не смотрелись: Velocity/BungeeCord/
// современный Paper-only обрабатывались бы как generic jar без имени
// плагина (плохой pom.xml/legitimacy check), а Fabric/Forge-моды
// декомпилировались бы вслепую, как если бы это был обфусцированный
// плагин - без предупреждения, что результат для мода не поддерживается.
#pragma once
#include <cstdint>
#include <functional>
#include <optional>
#include <string>
#include <vector>

namespace nd {

enum class PlatformKind {
    Bukkit,    // plugin.yml (Bukkit/Spigot/CraftBukkit) - main: класс
    Paper,     // paper-plugin.yml (современный Paper-only формат)
    Velocity,  // velocity-plugin.json
    Bungee,    // bungee.yml (BungeeCord)
    ModFabric, // fabric.mod.json
    ModForge,  // META-INF/mods.toml (Forge/NeoForge) или mcmod.info (легаси Forge 1.7-1.12)
    Unknown,   // манифест не найден - generic jar, декомпилируется как есть
};

struct PlatformInfo {
    PlatformKind kind = PlatformKind::Unknown;
    // Путь манифеста внутри jar, если найден (для отладки/логов).
    std::string manifest_path;
    // Имя плагина/мода, извлечённое из манифеста, если удалось - разный
    // формат на разных платформах (YAML-подобный plugin.yml/bungee.yml,
    // JSON velocity-plugin.json/fabric.mod.json, TOML mods.toml).
    std::optional<std::string> name;

    bool is_mod() const { return kind == PlatformKind::ModFabric || kind == PlatformKind::ModForge; }
    bool is_server_plugin() const {
        return kind == PlatformKind::Bukkit || kind == PlatformKind::Paper || kind == PlatformKind::Velocity ||
               kind == PlatformKind::Bungee;
    }
    std::string kind_label() const;
};

// all_names - список путей всех записей в jar (см. ZipReader). read_entry -
// функция чтения содержимого записи по пути (внедряется вызывающим кодом,
// чтобы не тащить сюда сам ZipReader как обязательную зависимость).
PlatformInfo detect_platform(const std::vector<std::string>& all_names,
                              const std::function<std::optional<std::string>(const std::string&)>& read_entry);

}  // namespace nd
