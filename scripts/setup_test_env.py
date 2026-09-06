#!/usr/bin/env python3
"""
setup_test_env.py - ЗАПУСКАЕТСЯ У ПОЛЬЗОВАТЕЛЯ (не у агента - у агента
заблокирован доступ к registry.npmjs.org и добавить хост в allowlist
нельзя). Собирает всё, что требует настоящего npm/интернета, и упаковывает
в архив(ы) ≤250 МБ, которые нужно отдать агенту файлом - дальше агент сам
прогонит Playwright-автоматизацию (скриншоты, клики) на уже собранном
результате, для этого у него всё уже готово локально.

Что делает этот скрипт:
  1. npm install (react/vite/tailwind/electron и т.д.)
  2. npm run build - собирает src/ в dist/ (обычное веб-приложение)
  3. Компилирует C++ движок (NanoDecompilerCLI)
  4. Подставляет лёгкий мок window.nano в dist/index.html, чтобы GUI не
     падал сразу на первом вызове несуществующего в браузере IPC - агент
     сможет открыть dist/ в браузере/headless-Chromium и увидеть реальную
     раскладку/стили без необходимости настоящего Electron.
  5. Упаковывает dist/ + скомпилированный движок в один или несколько
     .zip по ≤250 МБ каждый.

Использование:
  python3 setup_test_env.py
  (запускать из корня распакованного проекта NanoDecompiler, либо задать
   NANODECOMPILER_DIR=/путь/до/NanoDecompiler)

После завершения — отдай агенту файл(ы) nd_test_bundle*.zip из папки
рядом со скриптом.
"""
import os
import shutil
import subprocess
import sys
import zipfile
from pathlib import Path

REPO_DIR = Path(os.environ.get("NANODECOMPILER_DIR", Path.cwd()))
WORK_DIR = Path(os.environ.get("NANODECOMPILER_WORKDIR", Path.cwd() / "nd_test_env"))
MAX_CHUNK_BYTES = 250 * 1024 * 1024  # 250 МБ, по просьбе пользователя


def run(cmd, cwd=None, check=True):
    print(f"$ {' '.join(cmd)}" + (f"   (в {cwd})" if cwd else ""))
    result = subprocess.run(cmd, cwd=cwd, capture_output=True, text=True)
    if result.stdout.strip():
        print(result.stdout[-4000:])
    if result.stderr.strip():
        print(result.stderr[-4000:], file=sys.stderr)
    if check and result.returncode != 0:
        raise RuntimeError(f"команда завершилась с кодом {result.returncode}: {' '.join(cmd)}")
    return result


def step(title):
    print(f"\n=== {title} ===")


def try_step(title, fn):
    step(title)
    try:
        fn()
        print(f"[OK] {title}")
        return True
    except Exception as e:
        print(f"[ОШИБКА] {title}: {e}")
        return False


def ensure_repo():
    if not (REPO_DIR / "package.json").exists():
        raise RuntimeError(f"package.json не найден в {REPO_DIR} - запусти скрипт из корня проекта NanoDecompiler, "
                            f"или задай переменную NANODECOMPILER_DIR=/путь/до/NanoDecompiler")


def npm_install():
    # БАГ-ФИКС/подстраховка: electron - devDependency этого проекта, при
    # обычном `npm install` он пытается скачать нативный бинарник под
    # текущую ОС/архитектуру - на Termux (Android) такого бинарника не
    # существует вообще, и npm install падает ЦЕЛИКОМ, не дав установить
    # даже react/vite (они в том же package.json). Пробуем обычную
    # установку первой, а при неудаче - откатываемся на --ignore-scripts
    # (пропускает postinstall electron'а, но react/vite/tailwind всё равно
    # установятся - для сборки dist/ реальный Electron-бинарник не нужен).
    try:
        run(["npm", "install"], cwd=str(REPO_DIR))
    except RuntimeError:
        print("  ! обычный npm install не прошёл (вероятно, Electron не может скачать бинарник под эту платформу) - "
              "пробую --ignore-scripts…")
        run(["npm", "install", "--ignore-scripts"], cwd=str(REPO_DIR))


def build_react_app():
    run(["npm", "run", "build"], cwd=str(REPO_DIR))


def compile_engine():
    engine_dir = REPO_DIR / "resources" / "engine_cpp"
    out_bin = REPO_DIR / "resources" / "engine" / "NanoDecompilerCLI"
    cxx = shutil.which("g++") or shutil.which("clang++")
    if not cxx:
        raise RuntimeError("не найден ни g++, ни clang++ - установи build-essential (Linux) или MinGW/clang (Windows)")
    src_files = [str(p) for p in (engine_dir / "src").glob("*.cpp")]
    run([cxx, "-std=c++17", "-O2", "-Wall", "-Wextra", "-I", str(engine_dir / "include"),
         *src_files, "-lz", "-o", str(out_bin)])
    out_bin.chmod(0o755)
    run([str(out_bin), "--version"])


def mock_electron_for_browser():
    # Подкладывает лёгкий window.nano-мок в собранный dist/index.html, чтобы
    # React-приложение не падало сразу же на первом обращении к
    # несуществующему в браузере window.nano - агент сможет открыть dist/
    # в headless-Chromium (Playwright) и увидеть реальную раскладку/стили,
    # без необходимости настоящего Electron/IPC.
    index_html = REPO_DIR / "dist" / "index.html"
    if not index_html.exists():
        raise RuntimeError("dist/index.html не найден - сначала собери приложение (npm run build)")
    mock_js = """
<script>
window.nano = new Proxy({}, {
  get(_, prop) {
    return (...args) => Promise.resolve(
      prop.toString().startsWith('get') || prop.toString().startsWith('check')
        ? {}
        : (Array.isArray(args[0]) ? [] : {})
    );
  }
});
</script>
"""
    html = index_html.read_text(encoding="utf-8")
    if "window.nano = new Proxy" not in html:
        html = html.replace("<head>", "<head>" + mock_js, 1)
        index_html.write_text(html, encoding="utf-8")


def package_results():
    WORK_DIR.mkdir(parents=True, exist_ok=True)
    bundle_root = WORK_DIR / "bundle"
    if bundle_root.exists():
        shutil.rmtree(bundle_root)
    bundle_root.mkdir()

    for rel in ["dist", "resources/engine/NanoDecompilerCLI", "resources/engine/NanoDecompilerCLI.exe"]:
        src = REPO_DIR / rel
        if src.exists():
            dst = bundle_root / rel
            dst.parent.mkdir(parents=True, exist_ok=True)
            if src.is_dir():
                shutil.copytree(src, dst, dirs_exist_ok=True)
            else:
                shutil.copy2(src, dst)

    all_files = [p for p in bundle_root.rglob("*") if p.is_file()]
    parts, current_part, current_size = [], [], 0
    for f in all_files:
        fsize = f.stat().st_size
        if current_size + fsize > MAX_CHUNK_BYTES and current_part:
            parts.append(current_part)
            current_part, current_size = [], 0
        current_part.append(f)
        current_size += fsize
    if current_part:
        parts.append(current_part)

    out_paths = []
    for i, part in enumerate(parts, 1):
        suffix = f".part{i}" if len(parts) > 1 else ""
        zip_path = WORK_DIR / f"nd_test_bundle{suffix}.zip"
        with zipfile.ZipFile(zip_path, "w", zipfile.ZIP_DEFLATED) as zf:
            for f in part:
                zf.write(f, f.relative_to(bundle_root))
        out_paths.append(zip_path)
        print(f"  -> {zip_path} ({zip_path.stat().st_size / 1024 / 1024:.1f} МБ)")
    return out_paths


def main():
    ensure_repo()
    ok_npm = try_step("npm install (зависимости React/Vite)", npm_install)
    if ok_npm:
        try_step("сборка React-приложения (dist/)", build_react_app)
        try_step("мок window.nano для браузерного просмотра агентом", mock_electron_for_browser)
    try_step("компиляция C++ движка", compile_engine)
    step("Упаковка результатов")
    paths = package_results()
    print("\nГотово. Отдай агенту файл(ы):")
    for p in paths:
        print(" ", p)


if __name__ == "__main__":
    main()
