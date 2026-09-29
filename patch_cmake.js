const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/CMakeLists.txt', 'utf8');
code = code.replace(/src\/api\.cpp/g, 'src/api.cpp\n  src/auto_update.cpp');
fs.writeFileSync('resources/engine_cpp/CMakeLists.txt', code);
