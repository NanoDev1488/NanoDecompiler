const fs = require('fs');
const lines = fs.readFileSync('src/lib/javaHighlight.tsx', 'utf8').split('\n');
for (let i = 295; i < 325; i++) {
  console.log(i + ': ' + lines[i]);
}
