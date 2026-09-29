const fs = require('fs');
const lines = fs.readFileSync('HANDOFF_URGENT_16_ITEMS.md', 'utf8').split('\n');
for (let i = 0; i < lines.length; i++) {
  if (lines[i].includes('11.')) {
    console.log(lines[i]);
    console.log(lines[i+1]);
    console.log(lines[i+2]);
    console.log(lines[i+3]);
    console.log(lines[i+4]);
    console.log(lines[i+5]);
  }
}
