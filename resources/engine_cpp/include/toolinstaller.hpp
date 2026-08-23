// toolinstaller.hpp - порт resources/engine/toolinstaller.py (HANDOFF_46).
// Portable-установка JDK/Maven "по требованию" (НИЧЕГО не качается молча -
// только по явному запросу, см. оригинал).
//
// ЧЕСТНАЯ ОГОВОРКА ПРО HTTPS: все реальные источники (api.adoptium.net,
// dlcdn.apache.org, repo1.maven.org) - HTTPS. Реализовывать TLS руками -
// плохая идея по безопасности (легко ошибиться в проверке сертификатов),
// а тащить целую TLS-библиотеку (OpenSSL и т.п.) как внешнюю зависимость
// противоречит принципу проекта "никаких внешних библиотек, кроме zlib"
// (см. CMakeLists.txt). Поэтому HTTPS-скачивание здесь идёт через системный
// `curl` (запускается как подпроцесс) - curl.exe идёт в комплекте с
// Windows 10 1803+/Windows 11 "из коробки", на Linux/macOS почти всегда
// уже стоит. Если curl не найден в PATH - install_jdk()/install_maven()
// возвращают понятную ошибку вместо падения.
//
// СЛЕДСТВИЕ ЭТОГО РЕШЕНИЯ: живого прогресса скачивания по байтам (как в
// оригинале - progress_cb на каждый прочитанный чанк) здесь НЕТ - curl
// скачивает файл целиком, наружу видно только "начали"/"скачали, начинаем
// распаковку" (2 события прогресса на файл вместо потокового процента).
// GUI это переживёт нормально (проценты просто будут скакать 0% -> 100%
// вместо плавного роста), но это осознанное отличие от Python-оригинала.
//
// НЕ ПРОТЕСТИРОВАНО живым скачиванием - в песочнице сессии порта нет
// сети вообще (см. HANDOFF_1 и последующие - это ограничение среды, не
// решение). Логика извлечения архивов (zip через zip_reader.hpp, tar.gz
// вручную поверх zlib gzFile-API) написана по спецификации форматов и
// проверена на СИНТЕТИЧЕСКИХ архивах внутри этой сессии (см. HANDOFF_46),
// не на реальных JDK/Maven-дистрибутивах.
#pragma once

#include <cstdint>
#include <functional>
#include <optional>
#include <stdexcept>
#include <string>
#include <vector>

namespace nd {

// exe_names - варианты имени файла (напр. {"java","java.exe"}), kind -
// "java" | "maven" (влияет только на _well_known_dirs-эквивалент на
// Windows). Ищет сначала в PATH текущего процесса, затем (только Windows)
// в реестре (HKCU/HKLM Environment\Path) и типичных путях установки.
std::optional<std::string> resolve_tool_path(const std::vector<std::string>& exe_names, const std::string& kind);

std::string get_tools_dir();
std::optional<std::string> find_local_java();
std::optional<std::string> find_local_maven();

struct ToolInstallError : std::runtime_error {
    explicit ToolInstallError(const std::string& msg) : std::runtime_error(msg) {}
};

// label, downloaded_bytes, total_bytes_or_nullopt.
using ProgressCallback = std::function<void(const std::string&, uint64_t, std::optional<uint64_t>)>;

// Бросает ToolInstallError при неудаче. Возвращает путь к java.
std::string install_jdk(ProgressCallback progress_cb = nullptr);
// Бросает ToolInstallError при неудаче. Возвращает путь к mvn/mvn.cmd.
std::string install_maven(ProgressCallback progress_cb = nullptr);

struct InstallMissingResult {
    std::optional<std::string> java;
    std::optional<std::string> maven;
    std::vector<std::string> errors;
};
// НЕ бросает исключений - собирает ошибки в errors (см. оригинал -
// одна неудача, напр. Maven, не должна мешать попытке поставить JDK).
InstallMissingResult install_missing(bool need_java, bool need_maven, ProgressCallback progress_cb = nullptr);

}  // namespace nd
