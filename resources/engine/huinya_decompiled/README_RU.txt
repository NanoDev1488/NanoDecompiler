Результат разбора: huinya.jar
Классов успешно разобрано: 2893
Ошибок парсинга: 0

============================================================
СТАТИСТИКА ДЕОБФУСКАЦИИ ИМЁН
(эвристика, см. javatypes.py: looks_obfuscated - может как пропустить,
так и переименовать нормальное имя по ошибке; проверяйте MAPPING_RU.txt)

  Классов всего: 2893, переименовано: 1271 (43.9%)
  Методов всего: 23100, переименовано: 1946 (8.4%)
  Полей всего: 25881, переименовано: 1020 (3.9%)

======================================================================
ПРОВЕРКА КАЧЕСТВА ДЕКОМПИЛЯЦИИ
======================================================================

Классов в jar: 4187, успешно распарсено байткода: 4187 (100.0%)
  Классов из известных сторонних библиотек НЕ декомпилировано (не бандлятся - добавлены в pom.xml как maven-зависимость): 1294 (обнаружено: org.jetbrains.kotlin:kotlin-stdlib, org.jetbrains.kotlinx:kotlinx-coroutines-core, org.jetbrains:annotations, org.slf4j:slf4j-api)

Методов с телом (есть байткод): 20471
  - Полностью восстановлены в структурированный Java (if/else, while/for, switch, try/catch, выражения): 20023 (97.8%)
  - Не удалось безопасно восстановить -> честный дизассемблированный листинг байткода (см. комментарий в самом методе): 448 (2.2%)

  Крутизна декомпиляции: 🔥 Идеально! Практически весь код восстановлен в чистый структурированный Java.

  Причины отката на байткод (сгруппировано):
      164  многозначное пересечение стека между блоками (напр. arr[i] = cond ? a : b)
      141  нередуцируемый goto (сложный control-flow, не сведённый к структурам)
       50  неподдерживаемый invokedynamic bootstrap: java/lang/runtime/ObjectMethods.bootstrap
       33  synchronized-блок не свёрнут (monitorenter/monitorexit)
       20  переменная объявлена в блоке, но используется за его пределами (типично для switch(String) через hashCode) - структуризация ненадёжна
       18  нестандартный паттерн вызова конструктора
       14  нередуцируемый переход внутри региона
        3  <clinit> (static-инициализатор)
        3  stack underflow
        1  if/else-цепочка длиннее 800 уровней подряд - похоже на сгенерированную таблицу диспетчеризации, рекурсивный построитель для такого не годится
        1  structuring guard limit exceeded

Восстановлено настоящих switch(enum){...} вместо synthetic switch-map классов компилятора: скрыто 32 вспомогательных классов (их никогда не было в исходнике).

Баланс скобок {} () [] проверен по всем сгенерированным .java файлам - проблем не найдено.

ВНИМАНИЕ: 147 коллизий коротких имён классов (разные полные имена сведены к одному simple-имени в одном файле - возможна неоднозначность, при ручной доводке используйте полное имя):
  Type: com.kenai.jffi.Type, java.lang.reflect.Type, jnr.ffi.Type, org.objectweb.asm.Type
  Logger: com.kenai.jnr.x86asm.Logger, java.util.logging.Logger, jnr.a64asm.Logger, jnr.x86asm.Logger, org.slf4j.Logger
  Array: com.kenai.jffi.Array, java.lang.reflect.Array
  CallingConvention: com.kenai.jffi.CallingConvention, jnr.ffi.CallingConvention
  ClosureManager: com.kenai.jffi.ClosureManager, jnr.ffi.provider.ClosureManager
  Platform: com.kenai.jffi.Platform, jnr.ffi.Platform, jnr.posix.util.Platform
  Function: com.kenai.jffi.Function, java.util.function.Function
  Invoker: com.kenai.jffi.Invoker, jnr.ffi.provider.Invoker, org.spongepowered.asm.mixin.gen.Invoker
  LastError: com.kenai.jffi.LastError, jnr.constants.platform.LastError, jnr.constants.platform.windows.LastError, jnr.ffi.LastError
  Library: com.kenai.jffi.Library, com.sun.jna.Library, jnr.ffi.Library
  Platform_OS: com.kenai.jffi.Platform_OS, jnr.ffi.Platform_OS
  Platform_CPU: com.kenai.jffi.Platform_CPU, jnr.ffi.Platform_CPU
  Util: com.kenai.jffi.Util, jnr.ffi.provider.jffi.Util, org.freedesktop.dbus.utils.Util
  Platform_Darwin: com.kenai.jffi.Platform_Darwin, jnr.ffi.Platform_Darwin
  Platform_Default: com.kenai.jffi.Platform_Default, jnr.ffi.Platform_Default
  Platform_SingletonHolder: com.kenai.jffi.Platform_SingletonHolder, jnr.ffi.Platform_SingletonHolder
  Locale: java.util.Locale, jnr.constants.platform.Locale, jnr.constants.platform.darwin.Locale, jnr.constants.platform.dragonflybsd.Locale, jnr.constants.platform.freebsd.Locale, jnr.constants.platform.freebsd.aarch64.Locale, jnr.constants.platform.linux.Locale, jnr.constants.platform.linux.aarch64.Locale, jnr.constants.platform.linux.loongarch64.Locale, jnr.constants.platform.linux.mips64el.Locale, jnr.constants.platform.linux.powerpc64.Locale, jnr.constants.platform.linux.s390x.Locale, jnr.constants.platform.openbsd.Locale, jnr.constants.platform.solaris.Locale
  Platform_Windows: com.kenai.jffi.Platform_Windows, jnr.ffi.Platform_Windows
  Platform_Anon1: com.kenai.jffi.Platform_Anon1, jnr.ffi.Platform_Anon1
  Struct: com.kenai.jffi.Struct, jnr.ffi.Struct, org.freedesktop.dbus.Struct
  NativeType: com.kenai.jffi.NativeType, jnr.ffi.NativeType
  Properties: java.util.Properties, org.freedesktop.dbus.interfaces.Properties
  CPU: com.kenai.jnr.x86asm.CPU, jnr.x86asm.CPU
  Register: com.kenai.jnr.x86asm.Register, jnr.a64asm.Register, jnr.x86asm.Register
  MMRegister: com.kenai.jnr.x86asm.MMRegister, jnr.x86asm.MMRegister
  XMMRegister: com.kenai.jnr.x86asm.XMMRegister, jnr.x86asm.XMMRegister
  Asm: com.kenai.jnr.x86asm.Asm, jnr.a64asm.Asm, jnr.x86asm.Asm
  Mem: com.kenai.jnr.x86asm.Mem, jnr.a64asm.Mem, jnr.x86asm.Mem
  Label: com.kenai.jnr.x86asm.Label, jnr.a64asm.Label, jnr.x86asm.Label, org.objectweb.asm.Label
  SEGMENT: com.kenai.jnr.x86asm.SEGMENT, jnr.x86asm.SEGMENT

ЧТО ЭТО ЗНАЧИТ НА ПРАКТИКЕ:
  В этом окружении сборки нет javac, поэтому мы не можем гарантировать компиляцию
  на 100% - НО каждый метод, помеченный как 'восстановлен', прошёл через:
    1) полную символическую интерпретацию байткода (стек-машина -> выражения),
    2) структуризацию control-flow (if/while/for/switch/try) через дерево
       доминаторов/постдоминаторов,
    3) проверку баланса скобок сгенерированного текста.
  Если на любом из этих шагов декомпилятор не был уверен на 100% - метод
  автоматически откатывается на честный дизассемблированный листинг байткода
  вместо того, чтобы 'угадывать' и рисковать неверной логикой.


============================================================
ЧТО РЕАЛЬНО ДЕЛАЕТ ЭТОТ ИНСТРУМЕНТ:

  - Парсит constant pool, поля, методы, атрибут Code, BootstrapMethods,
    InnerClasses - вручную, по спецификации JVM (свой парсер).
  - Для каждого метода: строит CFG, символически исполняет байткод как
    стек-машину (арифметика, вызовы, new/anewarray, касты, инкременты,
    конкатенация строк через StringBuilder/invokedynamic, лямбды через
    LambdaMetafactory) и СТРУКТУРИРУЕТ управляющий поток через дерево
    доминаторов/постдоминаторов в if/else, while/do-while/for, switch,
    try/catch - настоящий Java-код, а не листинг байткода.
  - Если конкретный метод не удаётся восстановить с полной уверенностью -
    он НЕ гадает: откатывается на честный дизассемблированный листинг
    именно для этого метода (см. статистику выше), остальные методы это
    не затрагивает.
  - Эвристически деобфусцирует имена классов/методов/полей/пакетов,
    переименовывая согласованно по всему проекту.

ЧЕСТНО О ГРАНИЦАХ:
  - В окружении сборки нет javac, поэтому финальная компиляция не была
    проверена настоящим компилятором - только баланс скобок и структурная
    самосогласованность (см. статистику проверок выше). Перед боевым
    использованием рекомендуется прогнать через javac/IDE и поправить то,
    что покажет реальный компилятор (в первую очередь - конфликты коротких
    имён импортов, если они указаны выше).
  - synchronized-блоки не сворачиваются в `synchronized (x) { ... }` -
    вместо этого метод честно откатывается на дизассемблированный листинг
    байткода (см. `synchronized-блок не свёрнут` в причинах ниже, если есть) -
    компилировать такой листинг всё равно нельзя, зато семантика не теряется
    молча.
  - try/finally, скомпилированный через дублирование кода finally-блока
    (стандартно для javac 7+), восстанавливается как несколько отдельных
    catch(Throwable)-блоков с повторяющимся кодом, а не как единый
    красивый `finally {}` - семантика верна, но не свёрнута.
