const fs = require('fs');
let code = fs.readFileSync('resources/engine_cpp/src/cli_main.cpp', 'utf8');

code = code.replace(/#include "toolinstaller\.hpp"/, '#include "toolinstaller.hpp"\n#include "auto_update.hpp"');

let new_cmds = "    if (args[0] == \"--check-update\") {\n        nd::check_update();\n        return 0;\n    }\n    if (args[0] == \"--update\") {\n        nd::do_update(argv[0]);\n        return 0;\n    }\n";

code = code.replace(/if \(args\[0\] == "--version"\) \{/, new_cmds + '    if (args[0] == "--version") {');

code = code.replace(/NanoDecompilerCLI --version   \(JSON/g, "NanoDecompilerCLI --check-update   (Check latest version)\n       NanoDecompilerCLI --update   (Download and apply latest version)\n       NanoDecompilerCLI --version   (JSON");

fs.writeFileSync('resources/engine_cpp/src/cli_main.cpp', code);
