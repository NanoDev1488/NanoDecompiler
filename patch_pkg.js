const fs = require('fs');
let pkg = JSON.parse(fs.readFileSync('package.json', 'utf8'));
if (!pkg.build.nsis) pkg.build.nsis = {};
pkg.build.nsis.solid = true;
pkg.build.nsis.packElevateHelper = false;
pkg.build.nsis.allowToChangeInstallationDirectory = true;
fs.writeFileSync('package.json', JSON.stringify(pkg, null, 2) + '\n');
