const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/auto_update.cpp', 'utf8');

// The file currently has "" everywhere because of my previous powershell error.
// So I will just replace all "" with " and then fix the one edge case where we actually meant empty string "".
code = code.replace(/""/g, '"');

fs.writeFileSync('resources/engine_cpp/src/auto_update.cpp', code);
