# HANDOFF_16: Версия - единый источник правды (package.json), v1.4.1

Пользователь явно попросил: версия должна браться "всегда в одном месте
(package.json)" - раньше она была продублирована в ТРЁХ независимых
местах, рассинхронизированных между собой:

- `package.json`: `"version": "1.2.0"` - НЕ обновлялась вообще (застряла
  на 1.2.0), хотя релизы уже назывались "v1.2", потом "v1.4" в разных
  местах ad hoc.
- `.github/workflows/build-and-release.yml`: `env: RELEASE_TAG: v1.2,
  CLIENT_VERSION: v1.2, API_VERSION: v1.2` - хардкод, независимый от
  package.json, руками поднимался при каждом релизе.
- Реальные архивы/README в разговоре с пользователем назывались "v1.4" -
  ещё одно, третье, число, нигде не записанное в самом репозитории.

## Фикс

1. **`package.json`**: `"version": "1.2.0"` -> `"1.4.1"` - теперь ЭТО
   единственное место, которое поднимают при релизе.

2. **`.github/workflows/build-and-release.yml`**: новый первый job
   `resolve-version` - checkout репозитория, читает
   `require('./package.json').version` через `node -p`, отдаёт как
   `outputs.version` (с префиксом `v`, т.е. `v1.4.1`). Остальные три job
   (`build-client`, `build-api`, `publish-release`) объявляют `needs:
   [resolve-version, ...]` и берут `${{ needs.resolve-version.outputs.version
   }}` вместо прежних хардкоженных `env: RELEASE_TAG/CLIENT_VERSION/
   API_VERSION` на уровне всего workflow.

3. Проверено, что БОЛЬШЕ НИГДЕ версия не хардкожена по-другому:
   - `app.getVersion()` (Electron, `electron/updater.ts:209`) - уже и так
     нативно читает `package.json` в собранном приложении, ничего чинить
     не нужно.
   - `electron-builder` (сборка инсталлятора) - тоже нативно читает
     `package.json` для версии продукта.
   - `.engine-api-version` (файл, который апдейтер клиента сверяет с
     `versions.json` из релиза) - пишется в CI из `$env:API_VERSION`,
     который теперь тоже происходит из `resolve-version`.

Итог: поднять версию всего проекта (тег релиза, версия клиента, версия
API/CLI-exe, версия внутри самого установленного .exe) - теперь ОДНА
правка, `"version"` в `package.json`. CI, инсталлятор и рантайм-дисплей
версии в футере клиента все следуют за ней автоматически.

## Проверено

- `python3 -c "import yaml; yaml.safe_load(open('.github/workflows/build-and-release.yml'))"`
  - YAML валиден.
- Граф зависимостей job'ов проверен явно (`resolve-version` <-
  `build-client`/`build-api` <- `publish-release`) - корректный порядок,
  `publish-release` дожидается всех трёх.
- НЕ проверено вживую (нет доступа к реальному GitHub Actions раннеру в
  этой песочнице) - следующий push в `main` должен быть первой реальной
  проверкой. Если `node -p` почему-то не сработает на `ubuntu-latest`
  (маловероятно - node предустановлен на GitHub-хостед раннерах) - есть
  простой запасной вариант: `jq -r .version package.json` (jq тоже
  предустановлен).

## README.md

Заголовок обновлён `v1.4` -> `v1.4.1` (текстовая метка, ручная синхронизация
при релизе - README не может сам себя темплейтить, но теперь хотя бы
понятно, ЧТО поднимать одновременно: `package.json` + заголовок README).
