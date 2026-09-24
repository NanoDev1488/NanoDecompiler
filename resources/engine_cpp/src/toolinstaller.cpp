// toolinstaller.cpp - см. toolinstaller.hpp.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
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
    if (!fs::is_regular_file(fs::u8path(path), ec)) return false;
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
            std::string full = (fs::u8path(dir) / name).u8string();
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
        if (!java_home.empty()) out.push_back((fs::u8path(java_home) / "bin").u8string());
        for (auto& pf : program_files) {
            if (pf.empty()) continue;
            for (auto vendor : {"Java", "Eclipse Adoptium", "Eclipse Foundation", "Microsoft", "Zulu"}) {
                fs::path base = fs::u8path(pf) / vendor;
                std::error_code ec;
                if (!fs::is_directory(base, ec)) continue;
                for (auto& entry : fs::directory_iterator(base, ec)) out.push_back((entry.path() / "bin").u8string());
            }
        }
    } else if (kind == "maven") {
        for (auto var : {"MAVEN_HOME", "M2_HOME"}) {
            std::string home = getenv_s(var);
            if (!home.empty()) out.push_back((fs::u8path(home) / "bin").u8string());
        }
        for (auto& pf : program_files) {
            if (pf.empty()) continue;
            fs::path base = fs::u8path(pf) / "Apache" / "maven";
            std::error_code ec;
            if (fs::is_directory(base, ec)) out.push_back((base / "bin").u8string());
            fs::path apache_base = fs::u8path(pf) / "Apache";
            if (fs::is_directory(apache_base, ec)) {
                for (auto& entry : fs::directory_iterator(apache_base, ec)) {
                    std::string name = entry.path().filename().u8string();
                    std::string lower = name;
                    std::transform(lower.begin(), lower.end(), lower.begin(), ::tolower);
                    if (lower.rfind("maven", 0) == 0) out.push_back((entry.path() / "bin").u8string());
                }
            }
        }
        if (!user_profile.empty()) out.push_back((fs::u8path(user_profile) / "scoop" / "apps" / "maven" / "current" / "bin").u8string());
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
    base = local != nullptr ? fs::u8path(local) : fs::u8path(std::getenv("USERPROFILE") ? std::getenv("USERPROFILE") : ".");
    base /= "NanoDecompiler";
#else
    const char* home = std::getenv("HOME");
    base = fs::u8path(home != nullptr ? home : ".") / ".nanodecompiler";
#endif
    fs::path tools = base / "tools";
    std::error_code ec;
    fs::create_directories(tools, ec);
    return tools.u8string();
}

namespace {

std::optional<std::string> find_one(const std::string& tools_dir, const std::string& dir_prefix_lower, const std::vector<std::string>& rel_bin) {
    std::error_code ec;
    if (!fs::is_directory(tools_dir, ec)) return std::nullopt;
    std::vector<std::string> candidates;
    for (auto& entry : fs::directory_iterator(tools_dir, ec)) {
        std::string name = entry.path().filename().u8string();
        std::string lower = name;
        std::transform(lower.begin(), lower.end(), lower.begin(), ::tolower);
        if (lower.rfind(dir_prefix_lower, 0) != 0) continue;
        fs::path full = entry.path();
        for (auto& part : rel_bin) full /= part;
        if (fs::is_regular_file(full, ec)) candidates.push_back(full.u8string());
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

#if !defined(_WIN32) && !defined(__APPLE__)
// НОВОЕ 1.9.6 (реальный запрос - "на Linux попробовать установку Java/Maven
// через apt/sudo apt"): системный apt-путь пробуется ПЕРВЫМ на Linux, ДО
// curl_download-пути ниже - он не зависит от доступности зеркал
// Adoptium/Apache (repo Ubuntu/Debian почти всегда доступен и быстрее),
// и именно это, по всей видимости, стояло за повторяющейся жалобой
// "Maven не ставится" (mirror-fallback самого portable-скачивания уже
// чинился в v1.8.0 - добавлял ещё зеркала, но не убирал зависимость от
// внешней сети Apache целиком; apt - независимый путь).
//
// ЧЕСТНАЯ ОГОВОРКА: НЕ протестировано вживую с реальной установкой -
// песочница этой сессии тоже без исходящей сети для apt (подтверждено:
// apt-get install -y default-jdk-headless здесь падает с 403 Forbidden
// от прокси), НО пакетные имена (`default-jdk-headless`, `maven`) и
// команда `apt-get install -y <pkg>` реально проверены через
// `apt-get install --dry-run` в этой же песочнице - оба резолвятся в
// корректное дерево зависимостей на настоящем Ubuntu 24.04 (см. HANDOFF).
// graceful degradation, как и весь остальной toolinstaller: любая
// неудача (нет apt-get, нет pkexec, пользователь отменил графический
// запрос прав, нет сети) тихо роняет исполнение на существующий
// portable curl-путь ниже, а не считается фатальной ошибкой.
bool command_available_posix(const std::string& cmd) {
    return std::system(("command -v " + cmd + " >/dev/null 2>&1").c_str()) == 0;
}

// pkexec - штатный способ спросить права у пользователя GUI-приложению
// на современных Linux desktop (GNOME/KDE - идёт в комплекте с
// PolicyKit), показывает системный диалог, НЕ требует tty. НЕ используем
// голый `sudo` - без tty он либо тихо провалится (`sudo -n`), либо (без
// -n) зависнет, ожидая пароль на несуществующем терминале, что для
// GUI-приложения неотличимо от зависания.
bool try_apt_install(const std::vector<std::string>& packages, const std::string& label, const ProgressCallback& progress_cb) {
    if (!command_available_posix("apt-get")) return false;  // не Debian/Ubuntu-семейство - тихо на portable-путь
    if (!command_available_posix("pkexec")) return false;   // нет штатного способа спросить права без терминала
    if (progress_cb) progress_cb(label + " (apt)", 0, std::nullopt);
    std::string pkg_list;
    for (auto& p : packages) pkg_list += " " + shell_quote(p);
    // DEBIAN_FRONTEND=noninteractive - на случай debconf-диалогов у
    // каких-то транзитивных зависимостей (GUI-приложение не может
    // ответить на текстовый prompt внутри невидимого подпроцесса).
    std::string cmd = "pkexec env DEBIAN_FRONTEND=noninteractive apt-get install -y" + pkg_list + " >/dev/null 2>&1";
    int raw_rc = std::system(cmd.c_str());
    int rc = WIFEXITED(raw_rc) ? WEXITSTATUS(raw_rc) : -1;
    if (rc != 0) return false;
    if (progress_cb) progress_cb(label + " (apt)", 100, std::optional<uint64_t>(100));
    return true;
}
#endif

// --- zip-извлечение (переиспользует zip_reader.hpp) ---
std::optional<std::string> extract_zip(const std::string& zip_path, const std::string& dest_dir) {
    ZipReader zr(zip_path);
    std::set<std::string> roots;
    for (auto& name : zr.namelist()) {
        if (name.empty()) continue;
        auto slash = name.find('/');
        std::string root = slash == std::string::npos ? name : name.substr(0, slash);
        if (!root.empty()) roots.insert(root);

        fs::path dest = fs::u8path(dest_dir) / name;
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
        if (fs::is_directory(fs::u8path(dest_dir) / root, ec)) return root;
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

        fs::path dest = fs::u8path(dest_dir) / name;
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
        if (fs::is_directory(fs::u8path(dest_dir) / root, ec)) return root;
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
#if !defined(_WIN32) && !defined(__APPLE__)
    if (try_apt_install({"default-jdk-headless"}, "JDK", progress_cb)) {
        // apt кладёт java в системный /usr/bin, НЕ в get_tools_dir() -
        // ищем именно через resolve_tool_path (смотрит PATH текущего
        // процесса), а не find_local_java() (тот смотрит ТОЛЬКО папку
        // portable-установок самого приложения).
        auto java_path = resolve_tool_path({"java"}, "java");
        if (java_path.has_value()) return *java_path;
        // apt отчитался об успехе, но java не нашлась в PATH (нетипичный
        // случай, другой профиль путей дистрибутива) - не бросаем ошибку,
        // просто идём на portable-путь ниже, как и было раньше.
    }
#endif
    std::string tools_dir = get_tools_dir();
    std::string url = "https://api.adoptium.net/v3/binary/latest/" + std::to_string(kAdoptiumFeatureVersion) + "/ga/" + adoptium_os() + "/" +
                       adoptium_arch() + "/jdk/hotspot/normal/eclipse";
    bool is_zip = adoptium_os() == "windows";
    std::string tmp_archive = (fs::temp_directory_path() / ("nd_jdk_" + random_hex(16) + (is_zip ? ".zip" : ".tar.gz"))).u8string();
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
                                (fs::u8path(tools_dir) / root.value_or("")).u8string());
    }
    ensure_executable(*java_path);
    return *java_path;
}

std::string install_maven(ProgressCallback progress_cb) {
#if !defined(_WIN32) && !defined(__APPLE__)
    // См. комментарий в install_jdk() выше - тот же apt-путь, тот же
    // resolve_tool_path (НЕ find_local_maven - apt ставит в /usr/bin).
    // Это конкретно нацелено на повторяющуюся жалобу "Maven не
    // ставится" - apt полностью обходит сеть Apache mirrors, на
    // которую жалоба указывала изначально.
    if (try_apt_install({"maven"}, "Maven", progress_cb)) {
        auto maven_path = resolve_tool_path({"mvn"}, "maven");
        if (maven_path.has_value()) return *maven_path;
    }
#endif
    std::string tools_dir = get_tools_dir();
    std::string version = kMavenFallbackVersion;
    // БАГ-ФИКС v1.8.0 (реальный репорт - "Maven не качается вообще, не
    // видно его в папке"): логика скачивания сама по себе была верна (URL
    // подтверждён вручную - archive.apache.org хранит версии вечно), но
    // единственный источник - точка отказа, если archive.apache.org
    // недоступен/медленный/заблокирован в конкретной сети пользователя.
    // Пробуем несколько независимых зеркал по очереди - repo.maven.apache.org
    // (Maven Central, тот же файл) и dlcdn.apache.org (текущий, НЕ архивный
    // сервер - там есть только последние версии, но 3.9.9 достаточно новая,
    // может ещё оставаться) как запасные варианты.
    std::vector<std::string> urls = {
        "https://archive.apache.org/dist/maven/maven-3/" + version + "/binaries/apache-maven-" + version + "-bin.zip",
        "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/" + version + "/apache-maven-" + version + "-bin.zip",
        "https://dlcdn.apache.org/maven/maven-3/" + version + "/binaries/apache-maven-" + version + "-bin.zip",
    };
    std::string tmp_archive = (fs::temp_directory_path() / ("nd_maven_" + random_hex(16) + ".zip")).u8string();
    std::vector<std::string> mirror_errors;
    bool downloaded = false;
    for (auto& url : urls) {
        try {
            curl_download(url, tmp_archive, "Maven", progress_cb);
            downloaded = true;
            break;
        } catch (const std::exception& e) {
            mirror_errors.push_back(url + ": " + e.what());
        }
    }
    if (!downloaded) {
        std::string joined;
        for (auto& e : mirror_errors) joined += "\n  - " + e;
        throw ToolInstallError("не удалось скачать Maven ни с одного зеркала:" + joined);
    }

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
                                (fs::u8path(tools_dir) / root.value_or("")).u8string());
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
