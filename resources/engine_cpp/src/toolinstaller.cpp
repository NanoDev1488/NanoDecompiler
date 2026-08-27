// toolinstaller.cpp - см. toolinstaller.hpp.
#include "toolinstaller.hpp"

#include <zlib.h>

#include <algorithm>
#include <array>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <filesystem>
#include <fstream>
#include <random>
#include <set>

#include "zip_reader.hpp"

#ifdef _WIN32
#include <windows.h>
#else
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#endif

namespace fs = std::filesystem;

namespace nd {

namespace {

#ifdef _WIN32
constexpr bool kIsWindows = true;
#else
constexpr bool kIsWindows = false;
#endif

std::vector<std::string> split_path_env() {
    const char* p = std::getenv("PATH");
    if (p == nullptr) return {};
    std::string s(p);
    char sep = kIsWindows ? ';' : ':';
    std::vector<std::string> out;
    size_t start = 0;
    for (size_t i = 0; i <= s.size(); ++i) {
        if (i == s.size() || s[i] == sep) {
            if (i > start) out.push_back(s.substr(start, i - start));
            start = i + 1;
        }
    }
    return out;
}

bool is_executable_file(const std::string& path) {
    std::error_code ec;
    if (!fs::is_regular_file(path, ec)) return false;
#ifdef _WIN32
    return true;  // на Windows расширение (.exe/.cmd) уже говорит о типе
#else
    return ::access(path.c_str(), X_OK) == 0;
#endif
}

std::optional<std::string> which(const std::vector<std::string>& exe_names, const std::vector<std::string>& extra_dirs = {}) {
    std::vector<std::string> dirs = split_path_env();
    dirs.insert(dirs.end(), extra_dirs.begin(), extra_dirs.end());
    for (auto& dir : dirs) {
        for (auto& name : exe_names) {
            std::string full = (fs::path(dir) / name).string();
            if (is_executable_file(full)) return full;
        }
    }
    return std::nullopt;
}

#ifdef _WIN32
std::vector<std::string> registry_path_dirs() {
    std::vector<std::string> out;
    struct HiveKey {
        HKEY hive;
        const char* subkey;
    };
    HiveKey keys[] = {{HKEY_CURRENT_USER, "Environment"}, {HKEY_LOCAL_MACHINE, R"(SYSTEM\CurrentControlSet\Control\Session Manager\Environment)"}};
    for (auto& hk : keys) {
        HKEY key;
        if (RegOpenKeyExA(hk.hive, hk.subkey, 0, KEY_READ, &key) != ERROR_SUCCESS) continue;
        DWORD type = 0, size = 0;
        if (RegQueryValueExA(key, "Path", nullptr, &type, nullptr, &size) == ERROR_SUCCESS && size > 0) {
            std::string buf(size, '\0');
            if (RegQueryValueExA(key, "Path", nullptr, &type, reinterpret_cast<LPBYTE>(buf.data()), &size) == ERROR_SUCCESS) {
                if (!buf.empty() && buf.back() == '\0') buf.pop_back();
                size_t start = 0;
                for (size_t i = 0; i <= buf.size(); ++i) {
                    if (i == buf.size() || buf[i] == ';') {
                        if (i > start) out.push_back(buf.substr(start, i - start));
                        start = i + 1;
                    }
                }
            }
        }
        RegCloseKey(key);
    }
    return out;
}

std::vector<std::string> well_known_dirs(const std::string& kind) {
    std::vector<std::string> out;
    auto getenv_s = [](const char* n) -> std::string {
        const char* v = std::getenv(n);
        return v ? v : "";
    };
    std::vector<std::string> program_files = {getenv_s("ProgramFiles"), getenv_s("ProgramFiles(x86)"), getenv_s("ProgramW6432")};
    std::string user_profile = getenv_s("USERPROFILE");

    if (kind == "java") {
        std::string java_home = getenv_s("JAVA_HOME");
        if (!java_home.empty()) out.push_back((fs::path(java_home) / "bin").string());
        for (auto& pf : program_files) {
            if (pf.empty()) continue;
            for (auto vendor : {"Java", "Eclipse Adoptium", "Eclipse Foundation", "Microsoft", "Zulu"}) {
                fs::path base = fs::path(pf) / vendor;
                std::error_code ec;
                if (!fs::is_directory(base, ec)) continue;
                for (auto& entry : fs::directory_iterator(base, ec)) out.push_back((entry.path() / "bin").string());
            }
        }
    } else if (kind == "maven") {
        for (auto var : {"MAVEN_HOME", "M2_HOME"}) {
            std::string home = getenv_s(var);
            if (!home.empty()) out.push_back((fs::path(home) / "bin").string());
        }
        for (auto& pf : program_files) {
            if (pf.empty()) continue;
            fs::path base = fs::path(pf) / "Apache" / "maven";
            std::error_code ec;
            if (fs::is_directory(base, ec)) out.push_back((base / "bin").string());
            fs::path apache_base = fs::path(pf) / "Apache";
            if (fs::is_directory(apache_base, ec)) {
                for (auto& entry : fs::directory_iterator(apache_base, ec)) {
                    std::string name = entry.path().filename().string();
                    std::string lower = name;
                    std::transform(lower.begin(), lower.end(), lower.begin(), ::tolower);
                    if (lower.rfind("maven", 0) == 0) out.push_back((entry.path() / "bin").string());
                }
            }
        }
        if (!user_profile.empty()) out.push_back((fs::path(user_profile) / "scoop" / "apps" / "maven" / "current" / "bin").string());
    }
    std::vector<std::string> filtered;
    for (auto& d : out) {
        std::error_code ec;
        if (fs::is_directory(d, ec)) filtered.push_back(d);
    }
    return filtered;
}
#endif

}  // namespace

std::optional<std::string> resolve_tool_path(const std::vector<std::string>& exe_names, const std::string& kind) {
    auto found = which(exe_names);
    if (found.has_value()) return found;
#ifdef _WIN32
    std::vector<std::string> extra = registry_path_dirs();
    auto wk = well_known_dirs(kind);
    extra.insert(extra.end(), wk.begin(), wk.end());
    if (extra.empty()) return std::nullopt;
    return which(exe_names, extra);
#else
    (void)kind;
    return std::nullopt;
#endif
}

std::string get_tools_dir() {
    fs::path base;
#ifdef _WIN32
    const char* local = std::getenv("LOCALAPPDATA");
    base = local != nullptr ? fs::path(local) : fs::path(std::getenv("USERPROFILE") ? std::getenv("USERPROFILE") : ".");
    base /= "NanoDecompiler";
#else
    const char* home = std::getenv("HOME");
    base = fs::path(home != nullptr ? home : ".") / ".nanodecompiler";
#endif
    fs::path tools = base / "tools";
    std::error_code ec;
    fs::create_directories(tools, ec);
    return tools.string();
}

namespace {

std::optional<std::string> find_one(const std::string& tools_dir, const std::string& dir_prefix_lower, const std::vector<std::string>& rel_bin) {
    std::error_code ec;
    if (!fs::is_directory(tools_dir, ec)) return std::nullopt;
    std::vector<std::string> candidates;
    for (auto& entry : fs::directory_iterator(tools_dir, ec)) {
        std::string name = entry.path().filename().string();
        std::string lower = name;
        std::transform(lower.begin(), lower.end(), lower.begin(), ::tolower);
        if (lower.rfind(dir_prefix_lower, 0) != 0) continue;
        fs::path full = entry.path();
        for (auto& part : rel_bin) full /= part;
        if (fs::is_regular_file(full, ec)) candidates.push_back(full.string());
    }
    if (candidates.empty()) return std::nullopt;
    std::sort(candidates.begin(), candidates.end());
    return candidates.back();
}

}  // namespace

std::optional<std::string> find_local_java() {
    std::string exe = kIsWindows ? "java.exe" : "java";
    return find_one(get_tools_dir(), "jdk", {"bin", exe});
}

std::optional<std::string> find_local_maven() {
    std::string exe = kIsWindows ? "mvn.cmd" : "mvn";
    return find_one(get_tools_dir(), "apache-maven", {"bin", exe});
}

namespace {

std::string random_hex(int n) {
    static std::random_device rd;
    static std::mt19937_64 gen(rd());
    std::uniform_int_distribution<int> dist(0, 15);
    static const char* hexd = "0123456789abcdef";
    std::string out;
    for (int i = 0; i < n; ++i) out += hexd[dist(gen)];
    return out;
}

// Оборачивает аргумент в кавычки для shell (одиночные на POSIX, двойные
// на Windows cmd.exe) - используется ТОЛЬКО для URL/путей, которые сами
// формируем (версии/URL из наших констант или доверенного XML-ответа
// Maven Central), не для произвольного пользовательского ввода.
std::string shell_quote(const std::string& s) {
#ifdef _WIN32
    // БАГ-ФИКС: раньше просто оборачивалось в кавычки без экранирования
    // внутренних " - если бы s содержал ", можно было вырваться из
    // аргумента cmd.exe. Удваиваем " (стандартное экранирование для
    // cmd.exe /C и большинства Win32-парсеров командной строки).
    std::string out = "\"";
    for (char c : s) {
        if (c == '"')
            out += "\"\"";
        else
            out += c;
    }
    out += "\"";
    return out;
#else
    std::string out = "'";
    for (char c : s) {
        if (c == '\'')
            out += "'\\''";
        else
            out += c;
    }
    out += "'";
    return out;
#endif
}

bool curl_available() {
#ifdef _WIN32
    return std::system("where curl >nul 2>nul") == 0;
#else
    return std::system("command -v curl >/dev/null 2>&1") == 0;
#endif
}

// Скачивает url в dest_path целиком через системный curl (см. hpp - HTTPS
// без встроенной TLS-библиотеки). progress_cb получает два вызова: (0%,
// перед стартом) и (100%, после успешного завершения) - см. оговорку в hpp
// про отсутствие потокового прогресса при скачивании подпроцессом.
void curl_download(const std::string& url, const std::string& dest_path, const std::string& label, const ProgressCallback& progress_cb) {
    if (!curl_available()) {
        throw ToolInstallError("системная утилита 'curl' не найдена в PATH - без неё автозакачка JDK/Maven "
                                "недоступна (curl нужен для HTTPS-скачивания без встраивания TLS-библиотеки, "
                                "см. toolinstaller.hpp). Установите curl или скачайте " +
                                label + " вручную.");
    }
    if (progress_cb) progress_cb(label, 0, std::nullopt);
    std::string cmd = "curl -fsSL --max-time 300 -A \"NanoDecompiler-toolinstaller\" -o " + shell_quote(dest_path) + " " + shell_quote(url);
    int raw_rc = std::system(cmd.c_str());
#ifdef _WIN32
    int rc = raw_rc;  // на Windows std::system() уже возвращает код процесса напрямую
#else
    int rc = WIFEXITED(raw_rc) ? WEXITSTATUS(raw_rc) : -1;  // POSIX: system() отдаёт "сырой" wait()-статус, не сам код возврата
#endif
    if (rc != 0) {
        std::error_code ec;
        fs::remove(dest_path, ec);
        throw ToolInstallError("не удалось скачать " + url + " (curl завершился с кодом " + std::to_string(rc) + ")");
    }
    std::error_code ec;
    auto sz = fs::file_size(dest_path, ec);
    if (progress_cb) progress_cb(label, ec ? 0 : sz, ec ? std::nullopt : std::optional<uint64_t>(sz));
}

// --- zip-извлечение (переиспользует zip_reader.hpp) ---
std::optional<std::string> extract_zip(const std::string& zip_path, const std::string& dest_dir) {
    ZipReader zr(zip_path);
    std::set<std::string> roots;
    for (auto& name : zr.namelist()) {
        if (name.empty()) continue;
        auto slash = name.find('/');
        std::string root = slash == std::string::npos ? name : name.substr(0, slash);
        if (!root.empty()) roots.insert(root);

        fs::path dest = fs::path(dest_dir) / name;
        if (!name.empty() && name.back() == '/') {
            std::error_code ec;
            fs::create_directories(dest, ec);
            continue;
        }
        std::error_code ec;
        fs::create_directories(dest.parent_path(), ec);
        auto data = zr.read(name);
        std::ofstream f(dest, std::ios::binary);
        if (!data.empty()) f.write(reinterpret_cast<const char*>(data.data()), static_cast<std::streamsize>(data.size()));
    }
    if (roots.size() == 1) {
        std::string root = *roots.begin();
        std::error_code ec;
        if (fs::is_directory(fs::path(dest_dir) / root, ec)) return root;
    }
    return std::nullopt;
}

// --- tar.gz-извлечение (нужен только для JDK на Linux/macOS - Maven и
// Windows-JDK всегда zip). Ручной разбор POSIX ustar-заголовков поверх
// zlib gzFile-API (потоковая gzip-распаковка без промежуточного файла
// нераспакованного tar) - без внешней библиотеки, как и оригинал
// (Python stdlib tarfile). Понимает regular-файлы, каталоги и symlink'и
// (JDK-архивы Adoptium их содержат) - остальные typeflag'и (hardlink и
// т.п.) пропускаются молча, как малозначимые для распакованного JDK. ---
struct TarHeader {
    char name[100];
    char mode[8];
    char uid[8];
    char gid[8];
    char size[12];
    char mtime[12];
    char chksum[8];
    char typeflag;
    char linkname[100];
    char magic[6];
    char version[2];
    char uname[32];
    char gname[32];
    char devmajor[8];
    char devminor[8];
    char prefix[155];
    char padding[12];
};
static_assert(sizeof(TarHeader) == 512, "tar-заголовок должен быть ровно 512 байт");

uint64_t parse_octal(const char* field, size_t len) {
    uint64_t v = 0;
    for (size_t i = 0; i < len && field[i] != '\0' && field[i] != ' '; ++i) {
        if (field[i] < '0' || field[i] > '7') break;
        v = v * 8 + static_cast<uint64_t>(field[i] - '0');
    }
    return v;
}

bool is_all_zero(const char* buf, size_t n) {
    for (size_t i = 0; i < n; ++i)
        if (buf[i] != 0) return false;
    return true;
}

std::optional<std::string> extract_targz(const std::string& archive_path, const std::string& dest_dir) {
    gzFile gz = gzopen(archive_path.c_str(), "rb");
    if (gz == nullptr) throw ToolInstallError("не удалось открыть архив как gzip: " + archive_path);

    std::set<std::string> roots;
    TarHeader hdr;
    while (true) {
        int n = gzread(gz, &hdr, sizeof(hdr));
        if (n < static_cast<int>(sizeof(hdr))) break;
        if (is_all_zero(reinterpret_cast<char*>(&hdr), sizeof(hdr))) break;  // два нулевых блока - конец архива

        std::string name(hdr.prefix, strnlen(hdr.prefix, sizeof(hdr.prefix)));
        std::string base_name(hdr.name, strnlen(hdr.name, sizeof(hdr.name)));
        if (!name.empty()) name += "/";
        name += base_name;
        uint64_t size = parse_octal(hdr.size, sizeof(hdr.size));

        if (!name.empty()) {
            auto slash = name.find('/');
            std::string root = slash == std::string::npos ? name : name.substr(0, slash);
            if (!root.empty()) roots.insert(root);
        }

        fs::path dest = fs::path(dest_dir) / name;
        std::error_code ec;
        if (hdr.typeflag == '5') {  // каталог
            fs::create_directories(dest, ec);
        } else if (hdr.typeflag == '0' || hdr.typeflag == '\0') {  // обычный файл
            fs::create_directories(dest.parent_path(), ec);
            std::ofstream f(dest, std::ios::binary);
            std::vector<char> buf(64 * 1024);
            uint64_t remaining = size;
            while (remaining > 0) {
                int want = static_cast<int>(std::min<uint64_t>(remaining, buf.size()));
                int got = gzread(gz, buf.data(), want);
                if (got <= 0) break;
                f.write(buf.data(), got);
                remaining -= static_cast<uint64_t>(got);
            }
        } else if (hdr.typeflag == '2') {  // symlink
            std::string link_target(hdr.linkname, strnlen(hdr.linkname, sizeof(hdr.linkname)));
            fs::create_directories(dest.parent_path(), ec);
            fs::remove(dest, ec);
#ifndef _WIN32
            fs::create_symlink(link_target, dest, ec);  // ошибка симлинка не фатальна для распаковки JDK в целом
#endif
        }
        // остальные typeflag'и (hardlink и т.п.) - содержимого не имеют
        // отдельно от заголовка, пропускаем молча.

        // tar выравнивает содержимое файла до кратного 512 байт - выше мы
        // прочитали ровно `size` байт полезной нагрузки для файлов; съедаем
        // оставшийся паддинг (для не-файлов size обычно 0, паддинга нет).
        uint64_t padded = (size + 511) / 512 * 512;
        uint64_t pad = padded - size;
        if (pad > 0) {
            std::vector<char> skip(static_cast<size_t>(pad));
            gzread(gz, skip.data(), static_cast<int>(pad));
        }
    }
    gzclose(gz);

    if (roots.size() == 1) {
        std::string root = *roots.begin();
        std::error_code ec;
        if (fs::is_directory(fs::path(dest_dir) / root, ec)) return root;
    }
    return std::nullopt;
}

void ensure_executable(const std::string& path) {
#ifndef _WIN32
    struct stat st{};
    if (::stat(path.c_str(), &st) == 0) ::chmod(path.c_str(), st.st_mode | S_IXUSR | S_IXGRP | S_IXOTH);
#else
    (void)path;
#endif
}

std::string adoptium_os() {
#ifdef _WIN32
    return "windows";
#elif defined(__APPLE__)
    return "mac";
#else
    return "linux";
#endif
}

std::string adoptium_arch() {
#if defined(__x86_64__) || defined(_M_X64)
    return "x64";
#elif defined(__aarch64__) || defined(_M_ARM64)
    return "aarch64";
#elif defined(__arm__)
    return "arm";
#elif defined(__i386__) || defined(_M_IX86)
    return "x86-32";
#else
    throw ToolInstallError("неизвестная архитектура - автозакачка JDK не поддерживается, скачайте вручную: https://adoptium.net/");
#endif
}

constexpr int kAdoptiumFeatureVersion = 17;  // LTS - см. toolinstaller.py
constexpr const char* kMavenFallbackVersion = "3.9.9";

}  // namespace

std::string install_jdk(ProgressCallback progress_cb) {
    std::string tools_dir = get_tools_dir();
    std::string url = "https://api.adoptium.net/v3/binary/latest/" + std::to_string(kAdoptiumFeatureVersion) + "/ga/" + adoptium_os() + "/" +
                       adoptium_arch() + "/jdk/hotspot/normal/eclipse";
    bool is_zip = adoptium_os() == "windows";
    std::string tmp_archive = (fs::temp_directory_path() / ("nd_jdk_" + random_hex(16) + (is_zip ? ".zip" : ".tar.gz"))).string();
    curl_download(url, tmp_archive, "JDK", progress_cb);

    std::optional<std::string> root;
    try {
        root = is_zip ? extract_zip(tmp_archive, tools_dir) : extract_targz(tmp_archive, tools_dir);
    } catch (const std::exception& e) {
        std::error_code ec;
        fs::remove(tmp_archive, ec);
        throw ToolInstallError(std::string("не удалось распаковать архив JDK: ") + e.what());
    }
    std::error_code ec;
    fs::remove(tmp_archive, ec);

    auto java_path = find_local_java();
    if (!java_path.has_value()) {
        throw ToolInstallError("JDK скачан и распакован, но java не найдена внутри - возможно, Adoptium изменил структуру архива. "
                                "Папка распаковки: " +
                                (fs::path(tools_dir) / root.value_or("")).string());
    }
    ensure_executable(*java_path);
    return *java_path;
}

std::string install_maven(ProgressCallback progress_cb) {
    std::string tools_dir = get_tools_dir();
    // Версия последнего релиза Maven Central-метаданных сознательно НЕ
    // запрашивается отдельным HTTPS GET (нужен был бы XML-парсер только
    // ради одной строки) - сразу пробуем известный стабильный
    // MAVEN_FALLBACK_VERSION с archive.apache.org (хранит версии вечно) -
    // ЭТО ОТЛИЧИЕ от оригинала (тот сначала пробовал узнать САМУЮ свежую
    // версию через maven-metadata.xml, здесь - сразу конкретная известная
    // версия). Практический эффект: пользователь получит Maven
    // {kMavenFallbackVersion}, а не обязательно самый первый релиз на
    // момент запуска - для сборки декомпилированных Bukkit-плагинов через
    // `mvn clean package` разница версий Maven 3.9.x непринципиальна.
    std::string version = kMavenFallbackVersion;
    std::string url = "https://archive.apache.org/dist/maven/maven-3/" + version + "/binaries/apache-maven-" + version + "-bin.zip";
    std::string tmp_archive = (fs::temp_directory_path() / ("nd_maven_" + random_hex(16) + ".zip")).string();
    curl_download(url, tmp_archive, "Maven", progress_cb);

    std::optional<std::string> root;
    try {
        root = extract_zip(tmp_archive, tools_dir);
    } catch (const std::exception& e) {
        std::error_code ec;
        fs::remove(tmp_archive, ec);
        throw ToolInstallError(std::string("не удалось распаковать архив Maven: ") + e.what());
    }
    std::error_code ec;
    fs::remove(tmp_archive, ec);

    auto mvn_path = find_local_maven();
    if (!mvn_path.has_value()) {
        throw ToolInstallError("Maven скачан и распакован, но mvn не найден внутри - возможно, изменилась структура архива. "
                                "Папка распаковки: " +
                                (fs::path(tools_dir) / root.value_or("")).string());
    }
    ensure_executable(*mvn_path);
    return *mvn_path;
}

InstallMissingResult install_missing(bool need_java, bool need_maven, ProgressCallback progress_cb) {
    InstallMissingResult result;
    if (need_java) {
        try {
            result.java = install_jdk(progress_cb);
        } catch (const std::exception& e) {
            result.errors.push_back(std::string("JDK: ") + e.what());
        }
    }
    if (need_maven) {
        try {
            result.maven = install_maven(progress_cb);
        } catch (const std::exception& e) {
            result.errors.push_back(std::string("Maven: ") + e.what());
        }
    }
    return result;
}

}  // namespace nd
