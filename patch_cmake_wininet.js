const fs = require('fs');
let cmake = fs.readFileSync('resources/engine_cpp/CMakeLists.txt', 'utf8');
if (!cmake.includes('wininet')) {
    cmake = cmake.replace(/target_link_libraries\(NanoDecompilerCLI PRIVATE shell32\)/, 'target_link_libraries(NanoDecompilerCLI PRIVATE shell32)\n  target_link_libraries(NanoDecompilerCLI PRIVATE wininet)');
    fs.writeFileSync('resources/engine_cpp/CMakeLists.txt', cmake);
}
