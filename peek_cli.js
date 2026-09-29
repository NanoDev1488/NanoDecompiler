const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/cli_main.cpp', 'utf8');
console.log(code.substring(0, 500));
