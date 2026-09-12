// zip_reader.cpp - см. zip_reader.hpp. Читает центральный каталог ZIP с
// конца файла (End Of Central Directory), затем локальные заголовки по
// смещениям оттуда - стандартный подход (тот же, что использует Python
// `zipfile`). Распаковка DEFLATE - через системную zlib.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "zip_reader.hpp"

#include <cstdio>
#include <cstring>
#include <fstream>
#include <stdexcept>

#include <zlib.h>

namespace nd {

namespace {

uint16_t rd_u16(const uint8_t* p) { return uint16_t(p[0]) | (uint16_t(p[1]) << 8); }
uint32_t rd_u32(const uint8_t* p) {
    return uint32_t(p[0]) | (uint32_t(p[1]) << 8) | (uint32_t(p[2]) << 16) | (uint32_t(p[3]) << 24);
}

std::vector<uint8_t> inflate_raw(const std::vector<uint8_t>& compressed, uint64_t expected_size) {
    std::vector<uint8_t> out(expected_size ? expected_size : 1);
    z_stream strm{};
    // -15: сырой DEFLATE без zlib/gzip-обёртки (именно так хранятся данные в ZIP-записях).
    if (inflateInit2(&strm, -15) != Z_OK) throw std::runtime_error("inflateInit2 failed");
    strm.next_in = const_cast<Bytef*>(compressed.data());
    strm.avail_in = static_cast<uInt>(compressed.size());
    strm.next_out = out.data();
    strm.avail_out = static_cast<uInt>(out.size());
    int ret;
    size_t produced = 0;
    do {
        if (strm.avail_out == 0) {
            size_t old = out.size();
            out.resize(old * 2 + 64);
            strm.next_out = out.data() + old;
            strm.avail_out = static_cast<uInt>(out.size() - old);
        }
        ret = inflate(&strm, Z_NO_FLUSH);
        if (ret != Z_OK && ret != Z_STREAM_END) {
            inflateEnd(&strm);
            throw std::runtime_error("inflate failed");
        }
        produced = out.size() - strm.avail_out;
    } while (ret != Z_STREAM_END);
    inflateEnd(&strm);
    out.resize(produced);
    return out;
}

}  // namespace

ZipReader::ZipReader(const std::string& path) : path_(path) {
    std::ifstream f(path, std::ios::binary);
    if (!f) throw std::runtime_error("cannot open " + path);
    f.seekg(0, std::ios::end);
    std::streamoff file_size = f.tellg();
    if (file_size < 22) throw std::runtime_error("not a zip (too small)");

    // Ищем End Of Central Directory (сигнатура PK\x05\x06) с конца - в
    // общем случае она в последних 22..(22+65535) байтах (комментарий
    // архива переменной длины).
    std::streamoff search_size = std::min<std::streamoff>(file_size, 22 + 65535);
    f.seekg(file_size - search_size);
    std::vector<uint8_t> tail(static_cast<size_t>(search_size));
    f.read(reinterpret_cast<char*>(tail.data()), search_size);

    int64_t eocd_pos = -1;
    for (int64_t i = static_cast<int64_t>(tail.size()) - 22; i >= 0; --i) {
        if (tail[i] == 0x50 && tail[i + 1] == 0x4b && tail[i + 2] == 0x05 && tail[i + 3] == 0x06) {
            eocd_pos = i;
            break;
        }
    }
    if (eocd_pos < 0) throw std::runtime_error("not a zip (no EOCD)");

    uint16_t total_entries = rd_u16(&tail[eocd_pos + 10]);
    uint32_t cd_size = rd_u32(&tail[eocd_pos + 12]);
    uint32_t cd_offset = rd_u32(&tail[eocd_pos + 16]);

    std::vector<uint8_t> cd(cd_size);
    f.seekg(cd_offset);
    f.read(reinterpret_cast<char*>(cd.data()), cd_size);

    size_t pos = 0;
    for (uint16_t i = 0; i < total_entries; ++i) {
        if (pos + 46 > cd.size()) break;
        if (!(cd[pos] == 0x50 && cd[pos + 1] == 0x4b && cd[pos + 2] == 0x01 && cd[pos + 3] == 0x02)) break;
        uint16_t method = rd_u16(&cd[pos + 10]);
        uint32_t compressed_size_cd = rd_u32(&cd[pos + 20]);
        uint32_t uncompressed_size = rd_u32(&cd[pos + 24]);
        uint16_t name_len = rd_u16(&cd[pos + 28]);
        uint16_t extra_len = rd_u16(&cd[pos + 30]);
        uint16_t comment_len = rd_u16(&cd[pos + 32]);
        uint32_t local_header_offset = rd_u32(&cd[pos + 42]);
        std::string name(reinterpret_cast<const char*>(&cd[pos + 46]), name_len);

        ZipEntryInfo info;
        info.name = name;
        info.uncompressed_size = uncompressed_size;
        info.compressed_size = compressed_size_cd;
        info.compression_method = method;
        entries_[name] = info;
        order_.push_back(name);
        local_header_offset_[name] = local_header_offset;

        pos += 46 + name_len + extra_len + comment_len;
    }
}

const ZipEntryInfo* ZipReader::getinfo(const std::string& name) const {
    auto it = entries_.find(name);
    return it != entries_.end() ? &it->second : nullptr;
}

std::vector<uint8_t> ZipReader::read(const std::string& name) const {
    auto it = entries_.find(name);
    if (it == entries_.end()) throw std::runtime_error("no such entry: " + name);
    auto off_it = local_header_offset_.find(name);
    uint64_t local_offset = off_it->second;

    std::ifstream f(path_, std::ios::binary);
    if (!f) throw std::runtime_error("cannot open " + path_);
    f.seekg(local_offset);
    uint8_t lh[30];
    f.read(reinterpret_cast<char*>(lh), 30);
    if (!(lh[0] == 0x50 && lh[1] == 0x4b && lh[2] == 0x03 && lh[3] == 0x04)) {
        throw std::runtime_error("bad local file header for " + name);
    }
    uint16_t name_len = rd_u16(&lh[26]);
    uint16_t extra_len = rd_u16(&lh[28]);
    f.seekg(local_offset + 30 + name_len + extra_len);

    const ZipEntryInfo& info = it->second;
    // Размер сжатых данных берём ИЗ ЦЕНТРАЛЬНОГО КАТАЛОГА (info.compressed_size),
    // а НЕ из локального заголовка - когда архиватор писал запись потоково
    // (general purpose flag bit 3, "data descriptor"), поля crc32/размеров
    // в локальном заголовке нулевые, и полагаться на них нельзя; в
    // центральном каталоге эти поля всегда достоверны.
    std::vector<uint8_t> raw(info.compressed_size);
    f.read(reinterpret_cast<char*>(raw.data()), static_cast<std::streamsize>(info.compressed_size));

    if (info.compression_method == 0) {
        return raw;  // STORED
    }
    if (info.compression_method == 8) {
        return inflate_raw(raw, info.uncompressed_size);  // DEFLATE
    }
    throw std::runtime_error("unsupported compression method " + std::to_string(info.compression_method) + " for " + name);
}

}  // namespace nd
