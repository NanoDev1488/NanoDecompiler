#!/data/data/com.termux/files/usr/bin/env bash
# Работает и в Termux (Android), и в обычном Linux/macOS.
# Использование: ./run.sh plugin.jar

set -e

if [ -z "$1" ]; then
    echo "Использование: ./run.sh путь/к/plugin.jar"
    exit 1
fi

# в Termux и обычном Linux бинарь называется python3
PYBIN=$(command -v python3 || command -v python)

if [ -z "$PYBIN" ]; then
    echo "Python 3 не найден."
    echo "В Termux установите: pkg install python"
    echo "В обычном Linux: sudo apt install python3 (или аналог для вашего дистрибутива)"
    exit 1
fi

"$PYBIN" "$(dirname "$0")/main.py" "$1"
echo ""
echo "Готово. Смотрите папку рядом со скриптом."
