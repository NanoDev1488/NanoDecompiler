const fs = require('fs');
const lines = fs.readFileSync('src/lib/mcColors.ts', 'utf8').split('\n');
for (let i = 180; i <= 210; i++) {
  console.log(i + ': ' + lines[i]);
}
