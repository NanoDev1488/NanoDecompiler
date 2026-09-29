const fs = require('fs');
let yml = fs.readFileSync('.github/workflows/build-and-release.yml', 'utf8');

yml = yml.replace(/cmake --build build --parallel 1/g, 'cmake --build build --parallel');

fs.writeFileSync('.github/workflows/build-and-release.yml', yml);
console.log('Fixed parallel 1 in workflow.');
