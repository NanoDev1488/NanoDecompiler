const fs = require('fs');
const lines = fs.readFileSync('src/lib/javaHighlight.tsx', 'utf8').split('\n');
for (let i = 0; i < lines.length; i++) {
  if (lines[i].includes('renderMcColored') || lines[i].includes('renderNamedColorText') || lines[i].includes('renderUnknownColorMarked')) {
    console.log(i + ': ' + lines[i]);
  }
}
