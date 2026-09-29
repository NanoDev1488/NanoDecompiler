import re

with open('resources/engine_cpp/src/renamer.cpp', 'r', encoding='utf-8') as f:
    code = f.read()

code = code.replace(
    'new_name = name;',
    'new_name = name; std::replace(new_name.begin(), new_name.end(), \'$\', \'_\');'
)

with open('resources/engine_cpp/src/renamer.cpp', 'w', encoding='utf-8') as f:
    f.write(code)

with open('resources/engine_cpp/src/stackvm.cpp', 'r', encoding='utf-8') as f:
    code2 = f.read()

code2 = code2.replace(
    'if (!by_slot.count(e.slot)) by_slot[e.slot] = e.name;',
    'if (!by_slot.count(e.slot)) { std::string s = e.name; std::replace(s.begin(), s.end(), \'$\', \'_\'); by_slot[e.slot] = s; }'
)

with open('resources/engine_cpp/src/stackvm.cpp', 'w', encoding='utf-8') as f:
    f.write(code2)
