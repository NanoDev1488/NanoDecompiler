# HANDOFF_26: Модуль 3 - disassembler.py -> C++, сверено на всех 13 джарах
# пользователя (4075 классов, 0 расхождений)

Продолжение HANDOFF_24/25. С этого хэндоффа дальнейшие сообщения в чате -
по-русски (просьба пользователя). Работа продолжается без остановки до
переноса всего движка.

## disassembler.py -> disassembler.hpp/cpp

Перенесено полностью: все виды операндов (`none/byte/short/ubyte_cp/
ushort_cp/local_ubyte/iinc/branch2/branch4/atype/invokeinterface/
invokedynamic/multianewarray/tableswitch/lookupswitch/wide`), включая
выравнивание `tableswitch`/`lookupswitch` на границу 4 байт и особый
случай `wide iinc` (u2-операнд вместо u1). Печать таблицы исключений
метода (`Exception table:`) - тоже перенесена, с той же заменой `/` на
`.` в имени типа исключения.

`OperandKind` - уже был enum'ом (модуль 2a), здесь просто `switch` по
нему вместо Python `if/elif` по строкам - логика 1:1, просто быстрее.

### Сверка паритета

`dump_disasm` (C++) / `dump_disasm_ref.py` (Python, оригинальный
`disassembler.py`) - для каждого метода с кодом в `.class`-файле печатают
дизассемблированные строки, `diff` построчно.

Прогнано на **ВСЕХ 4075 `.class`-файлах из ВСЕХ 13 джарников пользователя**
(`all-test-jars.zip` целиком - это тот же корпус, что уже использовался
для модуля 1, ничего нового распаковывать не пришлось):

```
total=4075 mismatches=0 errors=0
```

0 расхождений, 0 ошибок парсинга/дизассемблирования - включая оба больших
джарника (EryBuyer, Salaires).

Команды для повтора:
```bash
g++ -std=c++17 -O2 -Wall -Iinclude src/classfile.cpp src/opcodes.cpp src/disassembler.cpp tools/dump_disasm.cpp -o dump_disasm
python3 tools/dump_disasm_ref.py some.class > py.txt
./dump_disasm some.class > cpp.txt
diff py.txt cpp.txt
```

## Готово на данный момент (модули 1-3)

`classfile.cpp` + `opcodes.cpp` + `javatypes.cpp` + `disassembler.cpp` -
все вместе линкуются без конфликтов символов, все прошли сверку на
реальных данных из джарников пользователя (не на синтетических
примерах "для галочки").

## Что дальше

По дереву зависимостей `engine.py` дальше идёт **`ir.py`** (168 строк) -
следующий уровень поверх `disassembler.py`: превращает плоский список
инструкций в объекты Instruction с резолвнутыми операндами (не текстовые
строки для человека, а структуры для дальнейшего анализа стек-машиной).
Продолжаю сразу без остановки, как попросили.
