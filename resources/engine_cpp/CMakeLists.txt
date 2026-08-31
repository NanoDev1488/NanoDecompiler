# CMakeLists.txt - сборка NanoDecompilerCLI (HANDOFF_45). Кросс-платформенная
# (Linux/macOS/Windows-MinGW) - список исходников собирается glob'ом по
# src/*.cpp (кроме tools/ - это отдельные dump_*/batch_* регрессионные
# утилиты для сверки с Python-оригиналом, не часть движка, см. HANDOFF_25).
#
# Собрано и проверено (g++ 13, Linux, см. HANDOFF_44/45) через:
#   g++ -std=c++17 -O2 -Iinclude src/*.cpp -lz -o NanoDecompilerCLI
# Этот CMakeLists.txt используется для Windows-сборки в CI
# (.github/workflows/build-and-release.yml, MinGW) - первый реальный
# прогон на GitHub Actions поймал недостающую линковку ws2_32 (см. ниже,
# исправлено по логу реальной ошибки линковщика, не вслепую).
cmake_minimum_required(VERSION 3.16)
project(NanoDecompiler CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
if(NOT CMAKE_BUILD_TYPE)
  set(CMAKE_BUILD_TYPE Release)
endif()

file(GLOB ND_SOURCES "${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp")

find_package(ZLIB REQUIRED)

add_executable(NanoDecompilerCLI ${ND_SOURCES})
target_include_directories(NanoDecompilerCLI PRIVATE "${CMAKE_CURRENT_SOURCE_DIR}/include")
target_link_libraries(NanoDecompilerCLI PRIVATE ZLIB::ZLIB)

# Статическая линковка рантайма MinGW на Windows - чтобы рядом с exe не
# нужно было таскать libgcc_s_seh-1.dll/libstdc++-6.dll/libwinpthread-1.dll
# (см. .github/workflows/build-and-release.yml - exe кладётся напрямую в
# extraResources, никакого установщика рядом с ним нет).
if(MINGW)
  target_link_options(NanoDecompilerCLI PRIVATE -static -static-libgcc -static-libstdc++)
  # БАГ-ФИКС (реальный, подозреваемая причина "движок вообще не запускается
  # на чистой Windows" - код ошибки похож на STATUS_DLL_NOT_FOUND): api.cpp
  # использует std::thread (для --api-server) - на "posix"-модели потоков
  # MinGW (нужна для полноценного std::thread, в отличие от "win32"-модели)
  # зависимость от libwinpthread-1.dll НЕ гарантированно покрывается одним
  # только общим -static флагом на некоторых вариантах тулчейна - Windows
  # подгружает ВСЕ DLL-зависимости exe при старте процесса целиком, даже
  # если конкретный запуск (обычная декомпиляция) никогда не касается
  # потокового кода API-сервера - без этой DLL рядом (и без её статической
  # линковки) exe отказывается стартовать вообще, ещё до main().
  target_link_options(NanoDecompilerCLI PRIVATE -Wl,-Bstatic -lwinpthread -Wl,-Bdynamic)
  # БАГ-ФИКС: api.cpp использует Winsock (WSAStartup/socket/bind/... для
  # --api-server) под #ifdef _WIN32, но ws2_32 нигде не линковалась - на
  # MinGW линкер не подтягивает системные .lib неявно (в отличие от
  # MSVC + #pragma comment(lib,...), которого здесь и нет) - "undefined
  # reference to __imp_WSAStartup" и т.п. на реальной сборке в CI.
  target_link_libraries(NanoDecompilerCLI PRIVATE ws2_32)
endif()
