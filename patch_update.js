const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/auto_update.cpp', 'utf8');

code = code.replace(/\\\/proc\\\/self\\\/exe/, '\'); // placeholder 

code = code.replace(/std::string cmd = "chmod \+x " \+ exe_path \+ " && mv " \+ exe_path \+ " \/proc\/self\/exe";\n    system\(cmd.c_str\(\)\);\n    std::cout << "Update successful! Restart the application." << std::endl;/g,
  "// Linux/macOS\n    // Cannot easily determine self path robustly without /proc, so we'll just rename it in current dir if ran locally\n    std::cout << \"Update downloaded to \" << exe_path << \". Please replace the old executable manually.\" << std::endl;");

fs.writeFileSync('resources/engine_cpp/src/auto_update.cpp', code);
