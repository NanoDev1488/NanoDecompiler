const fs = require('fs');
let cpp = fs.readFileSync('resources/engine_cpp/src/auto_update.cpp', 'utf8');

cpp = cpp.replace(/FILE\* pipe = popen\(cmd\.c_str\(\), "r"\);\n    if \(!pipe\) return "";\n    char buffer\[4096\];\n    std::string result = "";\n    while \(fgets\(buffer, sizeof\(buffer\), pipe\) != nullptr\) {\n        result \+= buffer;\n    }\n    pclose\(pipe\);\n    return result;/g, 
    "std::string tmp = \"nd_update_tmp.json\";\n" +
    "    std::string full_cmd = cmd + \" > \" + tmp;\n" +
    "    int res = system(full_cmd.c_str());\n" +
    "    (void)res;\n" +
    "    std::string result = \"\";\n" +
    "    FILE* f = fopen(tmp.c_str(), \"r\");\n" +
    "    if (f) {\n" +
    "        char buffer[4096];\n" +
    "        while (fgets(buffer, sizeof(buffer), f) != nullptr) {\n" +
    "            result += buffer;\n" +
    "        }\n" +
    "        fclose(f);\n" +
    "        remove(tmp.c_str());\n" +
    "    }\n" +
    "    return result;");

fs.writeFileSync('resources/engine_cpp/src/auto_update.cpp', cpp);
console.log('Patched popen to system + fopen.');
