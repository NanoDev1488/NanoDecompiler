const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/auto_update.cpp', 'utf8');
code = code.replace(/\\n/g, '\n');
fs.writeFileSync('resources/engine_cpp/src/auto_update.cpp', code);
