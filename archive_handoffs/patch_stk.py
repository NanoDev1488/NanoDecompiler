with open('resources/engine_cpp/src/stackvm.cpp', 'r', encoding='utf-8') as f:
    code = f.read()

code = code.replace('\"__stk\"', '\"temp\"')

with open('resources/engine_cpp/src/stackvm.cpp', 'w', encoding='utf-8') as f:
    f.write(code)
