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

add_executable(NanoDecompilerCLI ${ND_SOURCES})
target_include_directories(NanoDecompilerCLI PRIVATE "${CMAKE_CURRENT_SOURCE_DIR}/include")

# БАГ-ФИКС v1.7.3.1 (реальный краш у пользователя - STATUS_DLL_NOT_FOUND/
# 0xC0000135 - тот же код ошибки, что уже однажды чинили для
# libwinpthread-1.dll, но на этот раз причина ДРУГАЯ): find_package(ZLIB)
# по умолчанию находит ЛЮБУЮ доступную версию, а на MSYS2 пакет
# mingw-w64-x86_64-zlib ставит ОБЕ - и статическую libz.a, и динамическую
# zlib1.dll+libzlib.dll.a - и по умолчанию предпочитает ИМЕННО shared.
# `-static`/`-static-libgcc`/`-static-libstdc++` ниже НЕ спасают - это
# отдельная, ЯВНО заданная через target_link_libraries библиотека, а не
# неявный компиляторный рантайм, на который распространяется -static.
# В итоге exe у конечного пользователя (без zlib1.dll рядом и без её
# наличия в PATH - её никто не устанавливает вручную) отказывался
# стартовать вообще, ещё до main() - ровно то же поведение, что раньше
# было с libwinpthread. Явно ищем СТАТИЧЕСКИЙ .a - если он есть (на MSYS2
# он есть всегда, раз пакет ставит обе версии) - используем его, полностью
# обходя find_package(ZLIB)'s предпочтение shared. Fallback на обычный
# find_package(ZLIB) оставлен для Linux/macOS, где эта проблема
# принципиально не может возникнуть (см. -static ниже - там нет -static
# вообще, и системная libz.so - это нормально ожидаемая зависимость,
# всегда есть на любой Linux/Mac системе "из коробки").
if(MINGW)
  # Временно ограничиваем поиск ТОЛЬКО статическими архивами (".a") -
  # find_library() с именем "z" иначе может неоднозначно матчить import-lib
  # динамической версии (".dll.a" тоже в списке суффиксов MinGW по
  # умолчанию, и порядок предпочтения не гарантирован везде одинаково) -
  # так надёжнее, чем полагаться на порядок совпадения имени файла.
  set(ND_SAVED_LIB_SUFFIXES "${CMAKE_FIND_LIBRARY_SUFFIXES}")
  set(CMAKE_FIND_LIBRARY_SUFFIXES ".a")
  find_library(ND_ZLIB_STATIC NAMES z)
  set(CMAKE_FIND_LIBRARY_SUFFIXES "${ND_SAVED_LIB_SUFFIXES}")
  if(ND_ZLIB_STATIC)
    message(STATUS "NanoDecompiler: используем статическую zlib: ${ND_ZLIB_STATIC}")
    target_link_libraries(NanoDecompilerCLI PRIVATE ${ND_ZLIB_STATIC})
    # find_library() выше находит только сам .a - заголовок ищем отдельно
    # (обычно уже лежит в штатном include-пути MinGW-сборки zlib, но не
    # полагаемся на "обычно" молча - добавляем явно, если нашёлся).
    find_path(ND_ZLIB_INCLUDE_DIR NAMES zlib.h)
    if(ND_ZLIB_INCLUDE_DIR)
      target_include_directories(NanoDecompilerCLI PRIVATE ${ND_ZLIB_INCLUDE_DIR})
    endif()
  else()
    message(WARNING "NanoDecompiler: статическая zlib (libz.a) НЕ найдена - "
                     "линкуем shared ZLIB::ZLIB, итоговый exe будет ЗАВИСЕТЬ от zlib1.dll в PATH!")
    find_package(ZLIB REQUIRED)
    target_link_libraries(NanoDecompilerCLI PRIVATE ZLIB::ZLIB)
  endif()
else()
  find_package(ZLIB REQUIRED)
  target_link_libraries(NanoDecompilerCLI PRIVATE ZLIB::ZLIB)
endif()

# Статическая линковка рантайма MinGW на Windows - чтобы рядом с exe не
# нужно было таскать libgcc_s_seh-1.dll/libstdc++-6.dll/libwinpthread-1.dll
# (см. .github/workflows/build-and-release.yml - exe кладётся напрямую в
# extraResources, никакого установщика рядом с ним нет).
if(MINGW)
  target_link_options(NanoDecompilerCLI PRIVATE -static -static-libgcc -static-libstdc++)
  # БАГ-ФИКС v1.7.3 (найдено при разборе НОВОГО краша в CI -
  # STATUS_STACK_BUFFER_OVERRUN/0xC0000409 - падение происходило ДО единого
  # байта stdout, то есть ещё во время старта рантайма, не в коде движка):
  # предыдущая версия этой строки была `-Wl,-Bstatic -lwinpthread
  # -Wl,-Bdynamic` - явный "-Bdynamic" В СЕРЕДИНЕ команды линковки
  # переключает линкер обратно в динамический режим для ВСЕГО, что идёт
  # ПОСЛЕ этой строки в итоговой команде - включая library НЕЯВНО
  # добавляемые самим компилятором в конце (стандартный CRT/рантайм g++
  # добавляет их сам, мы их не видим в этом файле). Получался "франкенштейн"
  # бинарник - часть рантайма статическая, часть (то, что добавилось ПОСЛЕ
  # неявного -Bdynamic) - динамическая; смешивание статического и
  # динамического CRT/exception-handling рантайма в одном процессе -
  # классический источник неопределённого поведения, в диапазон которого
  # ВПОЛНЕ попадает /GS-краш ещё на старте, до main(). `-static` уже
  # выставлен ВЫШЕ и должен действовать на всю команду линковки целиком -
  # НЕ переключаем режим обратно, просто просим winpthread как обычно.
  target_link_options(NanoDecompilerCLI PRIVATE -lwinpthread)
  # БАГ-ФИКС: api.cpp использует Winsock (WSAStartup/socket/bind/... для
  # --api-server) под #ifdef _WIN32, но ws2_32 нигде не линковалась - на
  # MinGW линкер не подтягивает системные .lib неявно (в отличие от
  # MSVC + #pragma comment(lib,...), которого здесь и нет) - "undefined
  # reference to __imp_WSAStartup" и т.п. на реальной сборке в CI.
  target_link_libraries(NanoDecompilerCLI PRIVATE ws2_32)
endif()
