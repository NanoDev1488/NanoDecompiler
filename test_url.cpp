#include <iostream>
#include <string>

#ifdef _WIN32
#include <windows.h>
#include <wininet.h>
#pragma comment(lib, "wininet.lib")
#else
#include <cstdio>
#endif

std::string get_url(const std::string& url) {
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
    std::string cmd = "curl -sL \"" + url + "\"";
    FILE* pipe = popen(cmd.c_str(), "r");
    if (!pipe) return "";
    char buffer[128];
    std::string result = "";
    while (fgets(buffer, sizeof(buffer), pipe) != nullptr) {
        result += buffer;
    }
    pclose(pipe);
    return result;
#endif
}

int main() {
    std::cout << get_url("https://api.github.com/repos/NanoDev1488/NanoDecompiler/releases/latest") << std::endl;
    return 0;
}
