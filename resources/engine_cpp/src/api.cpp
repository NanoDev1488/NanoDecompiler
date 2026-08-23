// api.cpp - см. api.hpp.
#include "api.hpp"

#include <chrono>
#include <cstdio>
#include <cstring>
#include <filesystem>
#include <fstream>
#include <iostream>
#include <random>
#include <sstream>
#include <thread>

#include "json_value.hpp"
#include "process_jar.hpp"
#include "stats_json.hpp"
#include "version.hpp"

#ifdef _WIN32
#include <winsock2.h>
#include <ws2tcpip.h>
using socket_t = SOCKET;
#define ND_CLOSESOCK closesocket
#define ND_INVALID_SOCK INVALID_SOCKET
#else
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
using socket_t = int;
#define ND_CLOSESOCK close
#define ND_INVALID_SOCK (-1)
#endif

namespace fs = std::filesystem;

namespace nd {

namespace {

std::string random_hex(int n) {
    static std::random_device rd;
    static std::mt19937_64 gen(rd());
    std::uniform_int_distribution<int> dist(0, 15);
    static const char* hexd = "0123456789abcdef";
    std::string out;
    out.reserve(n);
    for (int i = 0; i < n; ++i) out += hexd[dist(gen)];
    return out;
}

// Единая точка вызова декомпиляции для обоих режимов (--json-output и
// HTTP /decompile) - порт api.py::decompile_silent(). В python это ещё и
// перехватывало консольный вывод process_jar_with_stats() через
// redirect_stdout - здесь process_jar_with_stats() и так МОЛЧА работает
// (см. process_jar.hpp, HANDOFF_44), перехватывать нечего.
std::string decompile_silent_json(const std::string& jar_path, const std::string& out_dir, bool skip_legitimacy) {
    auto t0 = std::chrono::steady_clock::now();
    try {
        JarProcessResult jr = process_jar_with_stats(jar_path, out_dir, skip_legitimacy);
        double elapsed = std::chrono::duration<double>(std::chrono::steady_clock::now() - t0).count();
        return jar_process_result_to_json(jr, out_dir, elapsed);
    } catch (const std::exception& e) {
        return json_error_response(std::string(e.what()));
    }
}

}  // namespace

int run_json_output(const std::string& jar_path, const std::string& out_dir, bool skip_legitimacy) {
    std::string json = decompile_silent_json(jar_path, out_dir, skip_legitimacy);
    std::cout << json << "\n";
    // status:"ok" всегда идёт первым полем - дешёвая проверка без полного
    // JSON-парсинга (зеркалит `result.get("status") == "ok"` оригинала).
    return json.rfind("{\"status\":\"ok\"", 0) == 0 ? 0 : 1;
}

// ---------------------------------------------------------------------
// HTTP-сервер (см. предупреждение в api.hpp про WinSock2-ветку).
// ---------------------------------------------------------------------
namespace {

struct HttpRequest {
    std::string method;
    std::string path;
    std::string query;
    std::string content_type;
    std::vector<uint8_t> body;
};

std::string url_decode(const std::string& s) {
    std::string out;
    for (size_t i = 0; i < s.size(); ++i) {
        if (s[i] == '%' && i + 2 < s.size()) {
            int v = 0;
            std::sscanf(s.substr(i + 1, 2).c_str(), "%x", &v);
            out += static_cast<char>(v);
            i += 2;
        } else if (s[i] == '+') {
            out += ' ';
        } else {
            out += s[i];
        }
    }
    return out;
}

std::optional<std::string> query_param(const std::string& query, const std::string& key) {
    size_t pos = 0;
    while (pos < query.size()) {
        size_t amp = query.find('&', pos);
        std::string pair = query.substr(pos, amp == std::string::npos ? std::string::npos : amp - pos);
        size_t eq = pair.find('=');
        if (eq != std::string::npos && url_decode(pair.substr(0, eq)) == key) return url_decode(pair.substr(eq + 1));
        if (amp == std::string::npos) break;
        pos = amp + 1;
    }
    return std::nullopt;
}

void send_json(socket_t fd, int code, const std::string& body) {
    const char* status_text = (code == 200) ? "OK" : (code == 404) ? "Not Found" : (code == 400) ? "Bad Request" : "Internal Server Error";
    std::ostringstream head;
    head << "HTTP/1.1 " << code << " " << status_text << "\r\n";
    head << "Content-Type: application/json; charset=utf-8\r\n";
    head << "Content-Length: " << body.size() << "\r\n";
    head << "Connection: close\r\n\r\n";
    std::string h = head.str();
    ::send(fd, h.data(), static_cast<int>(h.size()), 0);
    ::send(fd, body.data(), static_cast<int>(body.size()), 0);
}

// Читает и разбирает ОДИН HTTP-запрос из сокета (запросная строка +
// заголовки до \r\n\r\n, затем ровно Content-Length байт тела) - без
// keep-alive/chunked (оригинал на BaseHTTPRequestHandler тоже не
// поддерживал chunked для входящих запросов).
std::optional<HttpRequest> read_request(socket_t fd) {
    std::string buf;
    char chunk[4096];
    size_t header_end = std::string::npos;
    while (header_end == std::string::npos) {
        int n = ::recv(fd, chunk, sizeof(chunk), 0);
        if (n <= 0) return std::nullopt;
        buf.append(chunk, static_cast<size_t>(n));
        header_end = buf.find("\r\n\r\n");
        if (buf.size() > 1 * 1024 * 1024 && header_end == std::string::npos) return std::nullopt;  // защита от мусора без конца заголовков
    }
    std::string headers_part = buf.substr(0, header_end);
    std::string body_so_far = buf.substr(header_end + 4);

    std::istringstream hs(headers_part);
    std::string request_line;
    std::getline(hs, request_line);
    if (!request_line.empty() && request_line.back() == '\r') request_line.pop_back();
    std::istringstream rl(request_line);
    HttpRequest req;
    std::string http_ver;
    std::string full_path;
    rl >> req.method >> full_path >> http_ver;
    auto qpos = full_path.find('?');
    if (qpos == std::string::npos) {
        req.path = full_path;
    } else {
        req.path = full_path.substr(0, qpos);
        req.query = full_path.substr(qpos + 1);
    }

    size_t content_length = 0;
    std::string line;
    while (std::getline(hs, line)) {
        if (!line.empty() && line.back() == '\r') line.pop_back();
        if (line.empty()) continue;
        auto colon = line.find(':');
        if (colon == std::string::npos) continue;
        std::string key = line.substr(0, colon);
        std::string val = line.substr(colon + 1);
        size_t b = val.find_first_not_of(' ');
        val = (b == std::string::npos) ? "" : val.substr(b);
        for (auto& c : key) c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));
        if (key == "content-length") content_length = static_cast<size_t>(std::stoul(val));
        if (key == "content-type") req.content_type = val;
    }

    req.body.assign(body_so_far.begin(), body_so_far.end());
    while (req.body.size() < content_length) {
        int n = ::recv(fd, chunk, sizeof(chunk), 0);
        if (n <= 0) break;
        req.body.insert(req.body.end(), chunk, chunk + n);
    }
    return req;
}

std::string default_out_dir_for(const std::string& jar_path) {
    std::string base = fs::path(jar_path).stem().string();
    return (fs::temp_directory_path() / (base + "_decompiled")).string();
}

void handle_health(socket_t fd) { send_json(fd, 200, std::string("{\"status\":\"ok\",\"version\":\"") + NANO_DECOMPILER_VERSION + "\"}"); }

void handle_decompile(socket_t fd, const HttpRequest& req) {
    std::string ct = req.content_type;
    size_t semi = ct.find(';');
    if (semi != std::string::npos) ct = ct.substr(0, semi);
    size_t b = ct.find_first_not_of(' ');
    size_t e = ct.find_last_not_of(' ');
    ct = (b == std::string::npos) ? "" : ct.substr(b, e - b + 1);

    std::string jar_path;
    std::string out_dir;
    std::string tmp_jar_path;

    if (ct == "application/json") {
        std::string body_text(req.body.begin(), req.body.end());
        auto parsed = json_parse(body_text.empty() ? "{}" : body_text);
        if (!parsed.has_value()) {
            send_json(fd, 400, json_error_response("невалидный JSON"));
            return;
        }
        auto jp = parsed->get("jar_path");
        auto op = parsed->get("out_dir");
        if (jp == nullptr || !jp->as_string().has_value() || jp->as_string()->empty()) {
            send_json(fd, 400, json_error_response("поле 'jar_path' обязательно"));
            return;
        }
        jar_path = *jp->as_string();
        if (!fs::is_regular_file(jar_path)) {
            send_json(fd, 400, json_error_response("файл не найден: " + jar_path));
            return;
        }
        if (op != nullptr && op->as_string().has_value()) out_dir = *op->as_string();
    } else if (ct == "application/java-archive" || ct == "application/octet-stream" || ct == "application/zip" ||
               (ct.empty() && req.body.size() >= 2 && req.body[0] == 'P' && req.body[1] == 'K')) {
        if (req.body.empty()) {
            send_json(fd, 400, json_error_response("пустое тело запроса"));
            return;
        }
        tmp_jar_path = (fs::temp_directory_path() / ("nanodecompiler_upload_" + random_hex(32) + ".jar")).string();
        std::ofstream f(tmp_jar_path, std::ios::binary);
        f.write(reinterpret_cast<const char*>(req.body.data()), static_cast<std::streamsize>(req.body.size()));
        f.close();
        jar_path = tmp_jar_path;
        auto qod = query_param(req.query, "out_dir");
        if (qod.has_value()) out_dir = *qod;
    } else {
        send_json(fd, 400,
                   json_error_response("непонятное тело запроса - используйте Content-Type: application/json с "
                                        "{\"jar_path\": ...} или пришлите сырые байты .jar (application/java-archive)"));
        return;
    }

    if (out_dir.empty()) out_dir = default_out_dir_for(jar_path);

    std::string result_json = decompile_silent_json(jar_path, out_dir, /*skip_legitimacy=*/false);
    int code = result_json.rfind("{\"status\":\"ok\"", 0) == 0 ? 200 : 500;
    send_json(fd, code, result_json);

    if (!tmp_jar_path.empty()) {
        std::error_code ec;
        fs::remove(tmp_jar_path, ec);
    }
}

void handle_connection(socket_t fd) {
    auto req = read_request(fd);
    if (req.has_value()) {
        if (req->method == "GET" && req->path == "/health") {
            handle_health(fd);
        } else if (req->method == "POST" && req->path == "/decompile") {
            handle_decompile(fd, *req);
        } else {
            send_json(fd, 404, json_error_response("неизвестный маршрут"));
        }
    }
    ND_CLOSESOCK(fd);
}

}  // namespace

bool run_api_server(const std::string& host, int port) {
#ifdef _WIN32
    WSADATA wsa;
    if (WSAStartup(MAKEWORD(2, 2), &wsa) != 0) {
        std::cerr << "[api] WSAStartup не удался\n";
        return false;
    }
#endif

    socket_t server_fd = ::socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd == ND_INVALID_SOCK) {
        std::cerr << "[api] не удалось создать сокет\n";
        return false;
    }
    int opt = 1;
#ifdef _WIN32
    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, reinterpret_cast<const char*>(&opt), sizeof(opt));
#else
    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));
#endif

    sockaddr_in addr{};
    addr.sin_family = AF_INET;
    addr.sin_port = htons(static_cast<uint16_t>(port));
    if (host.empty() || host == "0.0.0.0") {
        addr.sin_addr.s_addr = INADDR_ANY;
    } else {
        inet_pton(AF_INET, host.c_str(), &addr.sin_addr);
    }

    if (::bind(server_fd, reinterpret_cast<sockaddr*>(&addr), sizeof(addr)) != 0) {
        std::cerr << "[api] не удалось привязаться к " << host << ":" << port << " (порт занят?)\n";
        ND_CLOSESOCK(server_fd);
        return false;
    }
    if (::listen(server_fd, 16) != 0) {
        std::cerr << "[api] listen() не удался\n";
        ND_CLOSESOCK(server_fd);
        return false;
    }

    std::cout << "[api] " << NANO_DECOMPILER_VERSION << " - HTTP API слушает на http://" << host << ":" << port
              << "  (POST /decompile, GET /health)\n";

    while (true) {
        sockaddr_in client_addr{};
#ifdef _WIN32
        int client_len = sizeof(client_addr);
#else
        socklen_t client_len = sizeof(client_addr);
#endif
        socket_t client_fd = ::accept(server_fd, reinterpret_cast<sockaddr*>(&client_addr), &client_len);
        if (client_fd == ND_INVALID_SOCK) continue;
        std::thread(handle_connection, client_fd).detach();
    }
    // недостижимо (serve_forever-эквивалент) - ND_CLOSESOCK(server_fd) намеренно не вызывается
}

}  // namespace nd
