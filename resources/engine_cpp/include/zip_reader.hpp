// zip_reader.hpp - минимальный ZIP/jar-ридер (только чтение) поверх
// системной zlib (inflate) - аналог того, для чего Python-оригинал
// использует stdlib `zipfile`. Не Python-модуль сам по себе - общая
// инфраструктура, нужная malware_scan.cpp (и позже main.cpp) - см.
// HANDOFF_31.
#pragma once

#include <cstdint>
#include <map>
#include <optional>
#include <string>
#include <vector>

namespace nd {

struct ZipEntryInfo {
    std::string name;
    uint64_t uncompressed_size = 0;
    uint64_t compressed_size = 0;
    uint16_t compression_method = 0;  // 0 = STORED, 8 = DEFLATE (два практически всегда встречающихся в jar)
};

class ZipReader {
public:
    // Бросает std::runtime_error, если файл не открывается или это не ZIP
    // (нет EOCD-сигнатуры) - зеркалит `zipfile.BadZipFile`.
    explicit ZipReader(const std::string& path);

    // Имена файлов в порядке центрального каталога (аналог ZipFile.namelist()).
    const std::vector<std::string>& namelist() const { return order_; }
    const ZipEntryInfo* getinfo(const std::string& name) const;

    // Бросает std::runtime_error на ошибке чтения/распаковки (аналог
    // исключения при ZipFile.read()).
    std::vector<uint8_t> read(const std::string& name) const;

private:
    std::string path_;
    std::vector<std::string> order_;
    std::map<std::string, ZipEntryInfo> entries_;
    std::map<std::string, uint64_t> local_header_offset_;
};

}  // namespace nd
