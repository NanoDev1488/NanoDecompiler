const fs = require('fs');
let pkg = JSON.parse(fs.readFileSync('package.json', 'utf8'));
pkg.version = '1.9.105';
fs.writeFileSync('package.json', JSON.stringify(pkg, null, 2) + '\n');

let ver = fs.readFileSync('resources/engine_cpp/include/version.hpp', 'utf8');
ver = ver.replace(/1\.9\.104/g, '1.9.105');
fs.writeFileSync('resources/engine_cpp/include/version.hpp', ver);
