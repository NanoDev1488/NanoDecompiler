const fs = require('fs');
let yml = fs.readFileSync('.github/workflows/build-and-release.yml', 'utf8');

yml = yml.replace(/ccache g\+\+ -std=c\+\+17 -O2 -Iinclude src\/\*\.cpp -lz -o \.\.\/\.\.\/NanoDecompilerClApi/g, 
  "cmake -S . -B build -DCMAKE_BUILD_TYPE=Release -DCMAKE_CXX_COMPILER_LAUNCHER=ccache -DCMAKE_C_COMPILER_LAUNCHER=ccache\n          cmake --build build --parallel\n          cp build/NanoDecompilerCLI ../../NanoDecompilerClApi");

fs.writeFileSync('.github/workflows/build-and-release.yml', yml);
console.log('Fixed ClApi compilation to use cmake and ccache properly.');
