const fs = require('fs');
let yml = fs.readFileSync('.github/workflows/build-and-release.yml', 'utf8');

yml = yml.replace(/run: cmake -S \. -B build -DCMAKE_BUILD_TYPE=Release -DCMAKE_CXX_COMPILER_LAUNCHER=ccache -DCMAKE_C_COMPILER_LAUNCHER=ccache\n          cmake --build build --parallel\n          cp build\/NanoDecompilerCLI \.\.\/\.\.\/NanoDecompilerClApi/g, 
  "run: |\n          cmake -S . -B build -DCMAKE_BUILD_TYPE=Release -DCMAKE_CXX_COMPILER_LAUNCHER=ccache -DCMAKE_C_COMPILER_LAUNCHER=ccache\n          cmake --build build --parallel\n          cp build/NanoDecompilerCLI ../../NanoDecompilerClApi");

fs.writeFileSync('.github/workflows/build-and-release.yml', yml);
console.log('Fixed YAML syntax error.');
