# HANDOFF_51: кроссплатформенная сборка клиента (Linux/macOS) в CI

Продолжение HANDOFF_47/49/50. Пользователь явно попросил вернуться к
задаче "GUI Linux/macOS и т.д." из старых хэндоффов.

## ЧТО СДЕЛАНО

1. **`.github/workflows/build-and-release.yml`, `build-client` job** -
   переписан с одиночного `windows-latest` на матрицу из трёх ОС
   (windows-latest/ubuntu-latest/macos-latest). Для каждой ОС - свой
   способ собрать движок (MSYS2/MinGW на Windows, системный g++/cmake +
   `apt install zlib1g-dev` на Linux, системный clang (`g++`) + `brew
   install cmake` на macOS) - но ОДИН И ТОТ ЖЕ `CMakeLists.txt`
   (кроссплатформенный с самого начала, HANDOFF_45). Артефакты:
   `NanoDecompiler-Client-Setup.exe` / `.AppImage` / `.dmg`, каждый под
   своим именем артефакта (`client-artifact-{win,linux,mac}`).
2. **`package.json`** - добавлена секция `"mac"` (`target: dmg`, `icon:
   icon.png`, `identity: null` - без подписи, нет сертификата Apple
   Developer в секретах репозитория), `"linux"` дополнена `icon`/`category`
   (раньше не было иконки вообще для AppImage).
3. **`publish-release` job** - скачивает ВСЕ 3 `client-artifact-*` через
   `pattern`+`merge-multiple` (было - одно жёстко заданное имя), таблица
   и текст релиза дополнены под 3 клиентских файла + честная оговорка про
   macOS-подпись и Gatekeeper.
4. **`electron/updater.ts`** - три хардкода под Windows поправлены:
   - `update:check`: поиск ассета клиента теперь по platform-зависимому
     регэкспу (`Setup.exe`/`.dmg`/`.AppImage`), поиск ассета движка
     (`NanoDecompilerCLI.exe`) - ТОЛЬКО на Windows (`ENGINE_ASSET_NAME`),
     т.к. `build-api` job (голый движок для встраивания в чужие проекты)
     пока НЕ переведён на кроссплатформенную сборку (отдельная, не
     затронутая в этой сессии задача) - `updateKind` теперь никогда не
     станет `"engine"` на Linux/macOS (нечего было бы скачивать).
   - `update:installClientAndRestart`: на Windows - как было (тихое
     скачивание+запуск NSIS-инсталлятора+выход). На Linux/macOS -
     ЧЕСТНО НЕ притворяется, что работает так же (`.dmg` нельзя просто
     "запустить" - нужно смонтировать образ и вручную перетащить `.app`;
     `.AppImage` не имеет единого "инсталлятора" вообще) - вместо этого
     открывает страницу скачивания в браузере (`shell.openExternal`),
     возвращает `{ok:true, manual:true}`.
   - `update:apply` (тихий патч движка без переустановки): путь назначения
     теперь через `ENGINE_ASSET_NAME` вместо жёсткого `.exe`.
5. **`electron/App.tsx`/`preload.ts`/`global.d.ts`** - обработка нового
   поля `manual` в ответе `installClientAndRestart` (без этой правки
   кнопка "Обновить" на Linux/macOS "зависла" бы в состоянии загрузки
   навечно - реальный UX-баг, который я сам внёс и тут же поймал при
   ревью собственной правки) - теперь показывает toast "откройте браузер"
   и корректно сбрасывает состояние. Типы синхронизированы в
   `preload.ts` + `global.d.ts` (два места с одинаковой сигнатурой -
   паттерн electron-приложений, оба нужно обновлять вместе).

## ЧЕСТНО ПРО ГРАНИЦЫ

- **НЕ ПРОВЕРЕНО ЖИВЬЁМ** ни на одной из трёх ОС - в песочнице сессии
  порта нет доступа к GitHub Actions/Windows/macOS вообще, только Linux-
  контейнер без CI. TypeScript-компилятор тоже не проверял правки в
  `updater.ts`/`App.tsx` живой сборкой (`@types/node`/electron типы не
  установлены, нет сети) - только глазами и структурной сверкой (парность
  скобок, соответствие типов между `preload.ts`/`global.d.ts`).
- macOS-сборка НЕ подписана (нет сертификата в секретах) - пользователям
  придётся обходить Gatekeeper вручную.
- `build-api` job (голый движок, отдельный от клиента) остаётся
  Windows-only - если понадобится engine-only патчинг на Linux/macOS,
  его тоже нужно перевести на матрицу (аналогично `build-client`).
- Полноценного авто-обновления (тихая замена + перезапуск) на Linux/macOS
  НЕТ и сознательно не имитируется - только открытие страницы скачивания.

## ПРОВЕРКА

- `.github/workflows/build-and-release.yml` - валиден YAML (парсится
  `yaml.safe_load`), структура матрицы проверена.
- `package.json` - валиден JSON.
- Полная регрессия движка (13 базовых + malware + BukkitOfUtils) - 0
  падений, идентично прошлому прогону (эта сессия не трогала C++-код).

## ФАЙЛЫ ЭТОЙ СЕССИИ

- `.github/workflows/build-and-release.yml` - `build-client` матрица,
  `publish-release` под 3 клиента.
- `package.json` - секции `mac`/`linux`.
- `electron/updater.ts` - кроссплатформенные пути.
- `src/App.tsx`, `electron/preload.ts`, `src/global.d.ts` - обработка
  `manual` в ответе апдейтера.
- Этот файл.
