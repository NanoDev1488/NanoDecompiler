const fs = require('fs');
const lines = fs.readFileSync('HANDOFF_URGENT_16_ITEMS.md', 'utf8').split('\n');
let i = 0;
while (i < lines.length) {
  if (lines[i].startsWith('6. ') || lines[i].startsWith('9. ')) {
    for (let j = 0; j < 15; j++) {
      if (i + j < lines.length && (j === 0 || !lines[i+j].match(/^\d+\.\s/))) {
        console.log(lines[i+j]);
      } else if (j > 0) {
        break;
      }
    }
  }
  i++;
}
