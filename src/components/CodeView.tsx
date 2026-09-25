import { Copy, Search, WrapText } from "lucide-react";
import { memo, useEffect, useMemo, useRef, useState } from "react";
import { useEngine } from "../state/engine";
import { JavaCode } from "../lib/javaHighlight";
import { PlainCode, PropertiesCode, JsonCode, XmlCode, YamlCode } from "../lib/textHighlight";
import { joinOutDir, type SourceFile } from "../lib/model";
import { FindBar } from "./FindBar";
import { OpenInMenu } from "./OpenInMenu";

// БАГ-ФИКС: раньше ЛЮБОЙ файл в просмотрщике рендерился через JavaCode
// независимо от расширения - .yml подсвечивался java-ключевыми словами.
// Выбираем токенизатор по расширению реального имени файла.
function codeComponentFor(name: string) {
  if (/\.java$/i.test(name)) return JavaCode;
  if (/\.ya?ml$/i.test(name)) return YamlCode;
  // БАГ-ФИКС v1.7.2: .properties использует "=" как разделитель, а
  // YAML_KEY_RE в YamlCode понимает только ":" - подсветка ключей никогда
  // не срабатывала. Отдельный токенизатор PropertiesCode понимает оба.
  if (/\.properties$/i.test(name)) return PropertiesCode;
  // НОВОЕ v1.7.3 (реальная жалоба - "нет подсветки для .json, например
  // fabric.mod.json") - раньше .json не имел своего токенизатора вообще,
  // шёл через PlainCode (только pretty-print без цвета).
  if (/\.json$/i.test(name)) return JsonCode;
  if (/\.xml$/i.test(name)) return XmlCode;
  return PlainCode;
}

// НОВОЕ v1.7.2 (HANDOFF_NEXT_AGENT_HANDOVER п.17): .json-файлы (например
// mapping/stats-отчёты движка) раньше показывались ОДНОЙ строкой как есть -
// движок пишет компактный JSON без переносов, читать такое в просмотрщике
// невозможно. Красиво печатаем ТОЛЬКО для отображения (сам файл на диске не
// трогаем) - если JSON невалиден (обрезан/не JSON вовсе, несмотря на
// расширение), тихо показываем исходный текст как есть, не роняем вьюер.
function prettyPrintIfJson(name: string, code: string): string {
  if (!/\.json$/i.test(name)) return code;
  try {
    return JSON.stringify(JSON.parse(code), null, 2);
  } catch {
    return code;
  }
}

export const CodeView = memo(function CodeView({
  file,
  jobId,
  outDir,
}: {
  file: SourceFile | null;
  jobId?: string;
  outDir?: string;
}) {
  const { copyText, selectFile, updateFileCode, toast } = useEngine();
  const [wrap, setWrap] = useState(false);
  // БАГ-ФИКС 1.9.10 (прямая правка предыдущей версии - "редактор всегда
  // должен работать, без отдельной кнопки входа, и подсветка не должна
  // пропадать"): 1.9.9 переключался в РАЗДЕЛЬНЫЙ textarea-режим по кнопке
  // (подсветка пропадала, потому что показывался textarea ВМЕСТО
  // подсвеченного кода). Теперь редактирование ВСЕГДА активно, когда файл
  // редактируемый (canEdit) - textarea лежит ПРОЗРАЧНЫМ слоем ПОВЕРХ
  // подсвеченного кода (тот же приём, что у react-simple-code-editor:
  // видимый текст - из подсветки снизу, курсор/выделение/ввод - от
  // невидимого textarea сверху, идеально совпадающие по шрифту/отступам).
  //
  // ЧЕСТНАЯ ОГОВОРКА (нет возможности визуально проверить в песочнице -
  // тут нет Electron/браузера для рендера и скриншота): перенос строк
  // (wrap) ПРИНУДИТЕЛЬНО выключен, пока файл редактируется - оверлей
  // держится на том, что каждая СТРОКА текста в textarea и в подсветке
  // занимает одну и ту же физическую строку по вертикали; перенос строк
  // зависит от ширины контейнера и мог бы читаться по-разному в textarea
  // (нет своих цветных span'ов, ширина текста чуть отличается) и в
  // подсветке - чтобы не рисковать рассинхроном, при редактировании
  // всегда используется горизонтальная прокрутка. Если после реальной
  // проверки на живой сборке окажется, что что-то не совпадает по
  // пикселям - дайте знать конкретику, поправить возможно, но не вслепую.
  const [draft, setDraft] = useState("");
  const lastSavedRef = useRef<string>("");
  const [saveState, setSaveState] = useState<"saving" | "err" | null>(null);
  // НОВОЕ v1.7.6: ref именно на контейнер С КОДОМ (не на весь CodeView) -
  // FindBar ищет ТОЛЬКО внутри него, см. БАГ-ФИКС в FindBar.tsx.
  const codeContainerRef = useRef<HTMLDivElement>(null);
  // НОВОЕ v1.7.6 (реальная жалоба - "если в .txt много текста, тяжело
  // читать" - у ViaVersion и похожих есть настраиваемые .txt-файлы с
  // длинными абзацами): по умолчанию включаем перенос строк ИМЕННО для
  // прозы (.txt/.md), а не для кода - код без переноса читать привычнее
  // (сохраняет визуальную структуру отступов), а длинный абзац текста без
  // переноса требует горизонтальной прокрутки для каждой строки. Ручную
  // кнопку переноса это не отменяет - просто разумный дефолт на каждый
  // новый открытый файл.
  useEffect(() => {
    if (!file) return;
    setWrap(/\.(txt|md)$/i.test(file.name));
    // Смена файла - подхватываем его код как новый черновик и сбрасываем
    // индикатор сохранения. Несохранённые правки ПРЕДЫДУЩЕГО файла молча
    // теряются при переключении - как и раньше, отдельный dirty-guard на
    // закрытие вкладки не делаем (нет вкладок как таковых).
    setDraft(file.code ?? "");
    lastSavedRef.current = file.code ?? "";
    setSaveState(null);
  }, [file?.id]);

  // НОВОЕ 1.9.10: сохранение - Ctrl+S ИЛИ автосохранение раз в 5с, БЕЗ
  // отдельной кнопки (по прямой просьбе - "уберите кнопку, редактор
  // должен работать сам"). saveNow вынесен в ref, чтобы не пересоздавать
  // интервал/обработчик клавиш при каждом изменении draft.
  const saveNowRef = useRef<() => void>(() => {});
  useEffect(() => {
    saveNowRef.current = () => {
      if (!outDir || !jobId || !file) return;
      if (draft === lastSavedRef.current) return; // нечего сохранять
      setSaveState("saving");
      window.nano
        .writeTextFile(outDir, file.relPath, draft)
        .then(res => {
          if (res.ok) {
            lastSavedRef.current = draft;
            updateFileCode(jobId, file.id, draft);
            setSaveState(null);
          } else {
            setSaveState("err");
            toast(`Не удалось сохранить ${file.name}: ${res.error ?? "неизвестная ошибка"}`, "err");
          }
        })
        .catch(e => {
          setSaveState("err");
          toast(`Не удалось сохранить ${file.name}: ${String(e)}`, "err");
        });
    };
  });

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === "s") {
        e.preventDefault();
        saveNowRef.current();
      }
    };
    window.addEventListener("keydown", onKey);
    const timer = window.setInterval(() => saveNowRef.current(), 5000);
    return () => {
      window.removeEventListener("keydown", onKey);
      window.clearInterval(timer);
    };
  }, []);
  // НОВОЕ v1.8.0 (реальный запрос - "хороший поиск в файле"): Ctrl+F
  // локально в этом компоненте (не глобальный шорткат в engine.tsx) -
  // поиск в файле имеет смысл, только пока файл вообще открыт.
  const [findOpen, setFindOpen] = useState(false);
  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if ((e.ctrlKey || e.metaKey) && !e.shiftKey && e.key.toLowerCase() === "f") {
        e.preventDefault();
        setFindOpen(true);
      } else if (e.key === "Escape") {
        setFindOpen(false);
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, []);
  // useMemo вызывается БЕЗУСЛОВНО (до раннего return ниже) - иначе при
  // переключении file между null/не-null менялось бы число вызванных хуков
  // между рендерами, что React запрещает (Rules of Hooks).
  //
  // БАГ-ФИКС 1.9.10: когда файл редактируемый, показываем СЫРОЙ draft
  // (без prettyPrintIfJson) - редактирование теперь ВСЕГДА активно (нет
  // отдельного read-only просмотра для .json), а pretty-print только для
  // отображения при живом редактировании развёл бы то, что человек видит
  // и печатает, с тем, что реально уйдёт на диск. Для НЕредактируемых
  // файлов (нет outDir/jobId - см. canEdit ниже) pretty-print остаётся,
  // это чисто просмотровый режим, сохранять там нечего.
  const canEdit = !!outDir && !!jobId && file?.code !== undefined && file?.loadError === undefined;
  const displayCode = useMemo(() => {
    if (file?.code === undefined) return undefined;
    return canEdit ? draft : prettyPrintIfJson(file.name, file.code);
  }, [file?.name, file?.code, canEdit, draft]);

  if (!file) {
    return (
      <div className="flex min-w-0 flex-1 items-center justify-center bg-bg">
        <p className="mono text-[11.5px] text-faint">// файл не выбран</p>
      </div>
    );
  }

  // БАГ-ФИКС v1.9.6 - см. комментарий у pkg в state/engine.tsx.
  const crumbs = [...file.pkg.split("/").filter(Boolean), file.name];
  const CodeComponent = codeComponentFor(file.name);

  return (
    <section className="relative flex min-w-0 flex-1 flex-col bg-bg">
      {findOpen && <FindBar containerRef={codeContainerRef} onClose={() => setFindOpen(false)} />}
      <div className="flex h-9 flex-none items-center gap-2.5 border-b border-line px-3">
        <nav className="mono flex min-w-0 items-center gap-1 text-[11.5px] text-faint" aria-label="Путь к файлу">
          {crumbs.map((c, i) => (
            <span key={i} className="flex min-w-0 items-center gap-1">
              {i > 0 && <span className="text-line-strong">/</span>}
              <span className={i === crumbs.length - 1 ? "truncate text-ink" : "truncate"}>{c}</span>
            </span>
          ))}
        </nav>

        {file.note && (
          <span className="chip hidden border-warn/35 text-warn lg:inline-flex" title={file.note}>
            замечание движка
          </span>
        )}

        <div className="flex-1" />

        <span className="mono hidden text-[10.5px] text-faint md:inline">
          {file.loc} строк{canEdit ? "" : " · read-only"}
        </span>
        {outDir && (
          <OpenInMenu filePath={joinOutDir(outDir, file.relPath)} projectDir={outDir} />
        )}
        {/* НОВОЕ v1.7.5 (реальная жалоба - "поиск вообще не работает"):
            раньше поиск открывался ТОЛЬКО горячими клавишами (Ctrl+F/
            Ctrl+Shift+F) без единой видимой кнопки - пользователь просто
            не мог узнать, что фича существует. Видимая кнопка + подсказка
            с сочетанием клавиш в title. */}
        <button className="icon-btn h-7 w-7" title="Найти в файле (Ctrl+F)" onClick={() => setFindOpen(true)}>
          <Search size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title={wrap ? "Отключить перенос строк" : "Переносить строки"}
          data-active={wrap}
          disabled={canEdit}
          onClick={() => setWrap(v => !v)}
        >
          <WrapText size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title="Скопировать исходник"
          disabled={displayCode === undefined}
          onClick={() => displayCode !== undefined && copyText(displayCode, `Исходник ${file.name}`)}
        >
          <Copy size={14} />
        </button>
        {/* НОВОЕ 1.9.10: без кнопки "редактировать" - см. оговорку у
            объявления draft/saveState выше. Индикатор вместо кнопки -
            статус САМ отражает, что происходит, действие от человека не
            требуется (Ctrl+S или просто подождать до 5с). */}
        {canEdit && (
          <span className="mono flex items-center gap-1 text-[10.5px] text-faint" title="Ctrl+S сохраняет сразу; иначе автосохранение раз в 5с">
            {saveState === "saving" ? (
              "Сохранение…"
            ) : saveState === "err" ? (
              <span className="text-err">ошибка сохранения</span>
            ) : draft === lastSavedRef.current ? (
              "сохранено"
            ) : (
              "есть изменения"
            )}
          </span>
        )}
      </div>

      <div ref={codeContainerRef} className="min-h-0 flex-1 overflow-auto py-3">
        {file.note && (
          <div className="mono mx-4 mb-3 rounded-lg border border-warn/25 bg-warn/5 px-3 py-2 text-[11px] leading-relaxed text-warn/90">
            {file.note}
          </div>
        )}
        {file.loadError !== undefined ? (
          <div className="mono flex flex-col items-start gap-2 px-4 text-[11.5px]">
            <p className="text-err">// не удалось загрузить файл: {file.loadError}</p>
            {/* БАГ-ФИКС v1.8.3 (HANDOFF_URGENT п.7 - "hex-viewer бинарников"):
                полноценный hex-viewer - отдельная большая фича (новый IPC для
                чтения сырых байт + новый UI-компонент), не стал делать
                вслепую. Но бэкенд УЖЕ детектит бинарные файлы честной
                эвристикой (нулевой байт в первых 8000 байтах, см.
                fs:readTextFile в main.ts) - раньше при этой ОДНОЙ конкретной
                ошибке всё равно показывалась кнопка "Повторить", хотя для
                бинарника результат гарантированно тот же самый при каждой
                попытке. OpenInMenu (открыть в системном приложении / показать
                в папке) уже существует и работает для ЛЮБОГО файла - просто
                был виден только в хедере сверху, не рядом с самой ошибкой. */}
            {/(?:похоже на )?бинарн/i.test(file.loadError) ? (
              <p className="text-dim">
                Просмотр бинарных файлов внутри вьюера пока не поддерживается - воспользуйтесь кнопкой «Открыть
                в…» справа сверху (системное приложение или папка с файлом).
              </p>
            ) : /слишком больш/i.test(file.loadError) ? (
              // БАГ-ФИКС 1.9.6 (HANDOFF п.13): "слишком большой файл" -
              // ОТДЕЛЬНАЯ ветка от бинарной (лимит MAX_TEXT_FILE_BYTES в
              // fs:readTextFile, main.ts), результат так же детерминирован
              // при повторе - "Повторить" тут вводит в заблуждение так же,
              // как раньше для бинарников. Особенно часто встречается для
              // .jar (они обычно больше лимита, но при этом не всегда
              // проходят через binary-эвристику первой).
              <p className="text-dim">
                Файл слишком большой для просмотра в редакторе - воспользуйтесь кнопкой «Открыть в…» справа сверху
                (системное приложение или папка с файлом).
              </p>
            ) : (
              <button className="btn btn-tonal h-7 text-[11px]" onClick={() => jobId && selectFile(jobId, file.id)}>
                Повторить
              </button>
            )}
          </div>
        ) : displayCode === undefined ? (
          <p className="mono px-4 text-[11.5px] text-faint">// загрузка…</p>
        ) : canEdit ? (
          // НОВОЕ 1.9.10: оверлей - textarea НЕВИДИМЫЙ (прозрачный текст,
          // виден только курсор через caret-color), лежит В ТОЙ ЖЕ ячейке
          // grid, что и подсвеченный код снизу (приём react-simple-code-
          // editor). Шрифт/отступы/leading у textarea и у CodeComponent
          // ниже должны совпадать СИМВОЛ В СИМВОЛ - см. честную оговорку
          // про непроверенность вживую у объявления draft выше.
          <div className="grid min-w-max" style={{ gridTemplateAreas: '"stack"' }}>
            <div style={{ gridArea: "stack" }} aria-hidden className="pointer-events-none">
              <CodeComponent code={displayCode} wrap={false} />
            </div>
            <textarea
              style={{ gridArea: "stack" }}
              className="mono h-full w-full resize-none border-0 bg-transparent px-4 pl-[64px] text-[12.5px] leading-[1.75] whitespace-pre text-transparent caret-ink outline-none"
              value={draft}
              spellCheck={false}
              onChange={e => setDraft(e.target.value)}
            />
          </div>
        ) : (
          <div className={wrap ? undefined : "min-w-max"}>
            <CodeComponent code={displayCode} wrap={wrap} />
          </div>
        )}
      </div>
    </section>
  );
});
