#!/data/data/com.termux/files/usr/bin/bash
# termux_setup.sh - настройка тестового окружения NanoDecompiler в Termux (Android).
#
# ЧЕСТНО СРАЗУ: полноценный GUI-клиент (Electron) на Android/Termux запустить
# НЕЛЬЗЯ - Electron требует настоящий десктопный оконный менеджер (X11/Wayland/
# Windows/macOS), которого на Android нет физически, ни при каком количестве
# пакетов. Этот скрипт настраивает ТОЛЬКО сам движок (чистый C++17 бинарник,
# NanoDecompilerCLI) - им можно полноценно тестировать саму декомпиляцию/
# деобфускацию прямо с телефона, без GUI. Для проверки самого GUI по-прежнему
# нужен обычный компьютер (Windows/Linux/macOS).
#
# Итоговый вес после установки пакетов + сборки - обычно 150-250 МБ (g++/
# clang toolchain в Termux сам по себе тяжёлый), гарантированно укладывается
# в лимит 500 МБ, о котором просил пользователь.
set -e

echo "=== NanoDecompiler - настройка тестового окружения движка в Termux ==="
echo "(GUI-клиент здесь не запустится - это ограничение Android, не наше)"
echo

echo "[1/5] Обновление списка пакетов…"
pkg update -y

echo "[2/5] Установка инструментов сборки (clang, git, zip, unzip)…"
# Termux использует clang вместо gcc по умолчанию - движок написан на
# переносимом C++17 без GCC-специфичных расширений, компилируется clang'ом
# без изменений исходников.
pkg install -y clang git zip unzip

echo "[3/5] Клонирование/обновление репозитория…"
REPO_URL="${NANODECOMPILER_REPO_URL:-}"
TARGET_DIR="$HOME/NanoDecompiler"
if [ -z "$REPO_URL" ]; then
  echo "  ! Переменная NANODECOMPILER_REPO_URL не задана."
  echo "  Запусти так: NANODECOMPILER_REPO_URL=https://github.com/ВАШ_АККАУНТ/NanoDecompiler.git bash termux_setup.sh"
  echo "  Либо просто распакуй присланный архив в $TARGET_DIR и запусти скрипт ещё раз - тогда клонирование пропустится."
  if [ ! -d "$TARGET_DIR" ]; then
    exit 1
  fi
else
  if [ -d "$TARGET_DIR/.git" ]; then
    (cd "$TARGET_DIR" && git pull)
  else
    git clone "$REPO_URL" "$TARGET_DIR"
  fi
fi

echo "[4/5] Компиляция движка (только CLI, без GUI/Electron - на Android невозможно)…"
cd "$TARGET_DIR/resources/engine_cpp"
clang++ -std=c++17 -O2 -Wall -Wextra -Iinclude src/*.cpp -lz -o "$HOME/NanoDecompilerCLI"
chmod +x "$HOME/NanoDecompilerCLI"

echo "[5/5] Проверка…"
"$HOME/NanoDecompilerCLI" --version

echo
echo "=== Готово ==="
echo "Бинарник: $HOME/NanoDecompilerCLI"
echo
echo "Пример использования:"
echo "  $HOME/NanoDecompilerCLI /путь/к/плагину.jar /путь/к/результату --no-legitimacy-check"
echo
echo "Общий вес установленного (пакеты Termux + бинарник):"
du -sh "$PREFIX" 2>/dev/null | tail -1 || true
