const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/auto_update.cpp', 'utf8');
console.log(code);
