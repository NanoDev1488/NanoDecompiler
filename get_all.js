const fs = require('fs');
const lines = fs.readFileSync('HANDOFF_URGENT_16_ITEMS.md', 'utf8').split('\n');
for (let i = 0; i < lines.length; i++) {
  if (lines[i].match(/^\d+\.\s/)) {
    console.log(lines[i]);
  }
}
