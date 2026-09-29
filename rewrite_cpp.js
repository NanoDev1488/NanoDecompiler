const fs = require('fs');

const cpp = 
#include "auto_update.hpp"
#include <iostream>
#include <string>
#include <vector>
#include <cstdio>
#include <cstdlib>
#include "json_value.hpp"
#include "version.hpp"

#ifdef _WIN32
#include <windows.h>
#include <wininet.h>
#endif

namespace nd {

static std::string get_url(const std::string& url) {
#ifdef _WIN32
    HINTERNET hInternet = InternetOpenA("NanoDecompilerCLI", INTERNET_OPEN_TYPE_DIRECT, NULL, NULL, 0);
    if (!hInternet) return "";
    HINTERNET hConnect = InternetOpenUrlA(hInternet, url.c_str(), NULL, 0, INTERNET_FLAG_RELOAD | INTERNET_FLAG_SECURE, 0);
    if (!hConnect) { InternetCloseHandle(hInternet); return ""; }
    
    std::string result;
    char buffer[4096];
    DWORD bytesRead = 0;
    while (InternetReadFile(hConnect, buffer, sizeof(buffer), &bytesRead) && bytesRead > 0) {
        result.append(buffer, bytesRead);
    }
    InternetCloseHandle(hConnect);
    InternetCloseHandle(hInternet);
    return result;
#else
    std::string tmp = "nd_update_tmp.json";
    std::string cmd = "curl -sL '" + url + "' > " + tmp;
    int res = system(cmd.c_str());
    (void)res;
    std::string result = "";
    FILE* f = fopen(tmp.c_str(), "r");
    if (f) {
        char buffer[4096];
        while (fgets(buffer, sizeof(buffer), f) != nullptr) {
            result += buffer;
        }
        fclose(f);
        remove(tmp.c_str());
    }
    return result;
#endif
}

static std::string download_file(const std::string& url, const std::string& out_path) {
#ifdef _WIN32
    HINTERNET hInternet = InternetOpenA("NanoDecompilerCLI", INTERNET_OPEN_TYPE_DIRECT, NULL, NULL, 0);
    if (!hInternet) return "InternetOpen failed";
    HINTERNET hConnect = InternetOpenUrlA(hInternet, url.c_str(), NULL, 0, INTERNET_FLAG_RELOAD | INTERNET_FLAG_SECURE, 0);
    if (!hConnect) { InternetCloseHandle(hInternet); return "InternetOpenUrl failed"; }
    
    FILE* f = fopen(out_path.c_str(), "wb");
    if (!f) {
        InternetCloseHandle(hConnect);
        InternetCloseHandle(hInternet);
        return "Cannot open output file";
    }

    char buffer[4096];
    DWORD bytesRead = 0;
    while (InternetReadFile(hConnect, buffer, sizeof(buffer), &bytesRead) && bytesRead > 0) {
        fwrite(buffer, 1, bytesRead, f);
    }
    fclose(f);
    InternetCloseHandle(hConnect);
    InternetCloseHandle(hInternet);
    return "";
#else
    std::string cmd = "curl -sL -o '" + out_path + "' '" + url + "'";
    int res = system(cmd.c_str());
    if (res != 0) return "curl failed";
    return "";
#endif
}

void check_update() {
    std::cout << "Checking for updates..." << std::endl;
    std::string json_str = get_url("https://api.github.com/repos/NanoDev1488/NanoDecompiler/releases/latest");
    if (json_str.empty()) {
        std::cerr << "Failed to check updates." << std::endl;
        return;
    }
    auto opt_val = json_parse(json_str);
    if (!opt_val) {
        std::cerr << "Failed to parse GitHub response." << std::endl;
        return;
    }
    JsonValue val = *opt_val;
    if (val.is_object() && val.get("tag_name")) {
        std::string tag = val.get("tag_name")->as_string().value_or("");
        std::cout << "Current version: " << NANO_DECOMPILER_VERSION << std::endl;
        std::cout << "Latest version:  " << tag << std::endl;
        if (tag != NANO_DECOMPILER_VERSION) {
            std::cout << "\\nUpdate available! Run with --update to upgrade." << std::endl;
        } else {
            std::cout << "\\nYou are on the latest version." << std::endl;
        }
    } else {
        std::cerr << "Invalid response from GitHub." << std::endl;
    }
}

void do_update(const std::string& self_path_argv) {
    std::cout << "Fetching latest release info..." << std::endl;
    std::string json_str = get_url("https://api.github.com/repos/NanoDev1488/NanoDecompiler/releases/latest");
    if (json_str.empty()) {
        std::cerr << "Failed to get release info." << std::endl;
        return;
    }
    std::string tag;
    std::string asset_url;
    auto opt_val = json_parse(json_str);
    if (!opt_val) {
        std::cerr << "Failed to parse JSON." << std::endl;
        return;
    }
    JsonValue val = *opt_val;
    if (val.is_object() && val.get("tag_name") && val.get("assets")) {
        tag = val.get("tag_name")->as_string().value_or("");
        if (tag == NANO_DECOMPILER_VERSION) {
            std::cout << "Already at the latest version (" << tag << ")." << std::endl;
            return;
        }
        auto assets_val = val.get("assets");
        if (assets_val->is_array() && assets_val->arr_v) {
            std::string target_name;
#ifdef _WIN32
            target_name = "NanoDecompilerClApi-windows.exe";
#elif defined(__APPLE__)
            target_name = "NanoDecompilerClApi-macos";
#else
            target_name = "NanoDecompilerClApi-linux";
#endif
            for (auto& a : *(assets_val->arr_v)) {
                if (a.is_object() && a.get("name") && a.get("name")->as_string().value_or("") == target_name) {
                    if (a.get("browser_download_url")) {
                        asset_url = a.get("browser_download_url")->as_string().value_or("");
                        break;
                    }
                }
            }
        }
    }

    if (asset_url.empty()) {
        std::cerr << "Could not find compatible asset for this OS in the latest release." << std::endl;
        return;
    }

    std::cout << "Downloading update from " << asset_url << " ..." << std::endl;
    std::string exe_path = "NanoDecompilerCLI_new";
#ifdef _WIN32
    exe_path += ".exe";
#endif
    std::string err = download_file(asset_url, exe_path);
    if (!err.empty()) {
        std::cerr << "Download failed: " << err << std::endl;
        return;
    }
    
    std::cout << "Download complete. Replacing executable..." << std::endl;
#ifdef _WIN32
    char self_path[MAX_PATH];
    GetModuleFileNameA(NULL, self_path, MAX_PATH);
    std::string old_path = std::string(self_path) + ".old";
    DeleteFileA(old_path.c_str());
    if (MoveFileA(self_path, old_path.c_str())) {
        if (MoveFileA(exe_path.c_str(), self_path)) {
            std::cout << "Update successful! Restart the application." << std::endl;
        } else {
            std::cerr << "Failed to rename new executable to " << self_path << std::endl;
        }
    } else {
        std::cerr << "Failed to rename current executable." << std::endl;
    }
#else
    std::string cmd = "chmod +x " + exe_path + " && mv " + exe_path + " '" + self_path_argv + "'";
    system(cmd.c_str());
    std::cout << "Update successful! Restart the application." << std::endl;
#endif
}

} // namespace nd
;

fs.writeFileSync('resources/engine_cpp/src/auto_update.cpp', cpp);
console.log('Fixed auto_update.cpp completely.');
