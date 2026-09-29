const fs = require('fs');
const lines = fs.readFileSync('HANDOFF_URGENT_16_ITEMS.md', 'utf8').split('\n');
let found = false;
for (let i = 0; i < lines.length; i++) {
  if (lines[i].startsWith('14.')) found = true;
  if (found) {
    console.log(lines[i]);
    if (lines[i].startsWith('15.')) break;
  }
}
