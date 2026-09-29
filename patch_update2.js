const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/auto_update.cpp', 'utf8');

code = code.replace(
  /void do_update\(\) \{/g,
  'void do_update(const std::string& self_path_argv) {'
);

code = code.replace(
  /\/\/ Linux\/macOS\n    std::string cmd = "chmod \+x " \+ exe_path \+ " && mv " \+ exe_path \+ " \/proc\/self\/exe";\n    system\(cmd.c_str\(\)\);\n    std::cout << "Update successful! Restart the application." << std::endl;/g,
  "// Linux/macOS\n    std::string cmd = \"chmod +x \" + exe_path + \" && mv \" + exe_path + \" '\" + self_path_argv + \"'\";\n    system(cmd.c_str());\n    std::cout << \"Update successful! Restart the application.\" << std::endl;"
);

fs.writeFileSync('resources/engine_cpp/src/auto_update.cpp', code);
