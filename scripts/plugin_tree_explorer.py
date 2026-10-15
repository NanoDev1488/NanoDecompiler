import zipfile
import json
import sys
import os
import re

sys.stdout.reconfigure(encoding='utf-8', errors='replace')

def parse_yaml_simple(text):
    data = {}
    current_section = None
    sub_key = None
    for line in text.splitlines():
        trimmed = line.strip()
        if not trimmed or trimmed.startswith('#'):
            continue
        indent = len(line) - len(line.lstrip())
        if indent == 0 and ':' in trimmed:
            k, v = trimmed.split(':', 1)
            k, v = k.strip(), v.strip().strip('"').strip("'")
            current_section = k
            sub_key = None
            if v:
                data[k] = v
            else:
                data[k] = {}
        elif indent == 2 and current_section and ':' in trimmed:
            k, v = trimmed.split(':', 1)
            k, v = k.strip(), v.strip().strip('"').strip("'")
            sub_key = k
            if isinstance(data.get(current_section), dict):
                if v:
                    data[current_section][k] = v
                else:
                    data[current_section][k] = {}
        elif indent == 4 and current_section and sub_key and ':' in trimmed:
            k, v = trimmed.split(':', 1)
            k, v = k.strip(), v.strip().strip('"').strip("'")
            if isinstance(data.get(current_section), dict) and isinstance(data[current_section].get(sub_key), dict):
                data[current_section][sub_key][k] = v
    return data

def build_plugin_tree(jar_path):
    tree = {
        'jar': os.path.basename(jar_path),
        'manifest': {},
        'commands': [],
        'permissions': [],
        'listeners': []
    }

    plugin_yml_text = ""
    class_names = []

    with zipfile.ZipFile(jar_path, 'r') as z:
        for name in z.namelist():
            if name == 'plugin.yml':
                try:
                    plugin_yml_text = z.read(name).decode('utf-8', errors='replace')
                except Exception:
                    pass
            elif name.endswith('.class') and not 'module-info' in name:
                class_names.append(name)

    if plugin_yml_text:
        yml = parse_yaml_simple(plugin_yml_text)
        tree['manifest'] = {
            'name': yml.get('name', 'Unknown'),
            'version': yml.get('version', 'Unknown'),
            'main': yml.get('main', 'Unknown'),
            'api_version': yml.get('api-version', 'legacy'),
            'author': yml.get('author', yml.get('authors', 'Unknown'))
        }

        # Команды
        cmds = yml.get('commands', {})
        if isinstance(cmds, dict):
            for cmd_name, cmd_info in cmds.items():
                if isinstance(cmd_info, dict):
                    tree['commands'].append({
                        'command': f"/{cmd_name}",
                        'description': cmd_info.get('description', ''),
                        'permission': cmd_info.get('permission', 'None'),
                        'aliases': cmd_info.get('aliases', '')
                    })
                else:
                    tree['commands'].append({'command': f"/{cmd_name}", 'description': str(cmd_info)})

        # Permissions
        perms = yml.get('permissions', {})
        if isinstance(perms, dict):
            for perm_name, perm_info in perms.items():
                if isinstance(perm_info, dict):
                    tree['permissions'].append({
                        'permission': perm_name,
                        'description': perm_info.get('description', ''),
                        'default': perm_info.get('default', 'op')
                    })
                else:
                    tree['permissions'].append({'permission': perm_name, 'description': str(perm_info), 'default': 'op'})

    # Эвристика поиска слушателей эвентов по именам классов
    for c in class_names:
        c_clean = c.replace('.class', '').replace('/', '.')
        lower = c_clean.lower()
        if 'listener' in lower or 'event' in lower or 'handler' in lower:
            tree['listeners'].append(c_clean)

    return tree

def print_ascii_tree(tree):
    m = tree['manifest']
    print(f"📦 Plugin: {m.get('name', 'Unknown')} v{m.get('version', '')}")
    print(f" ├── 👑 Main Class: {m.get('main', '')}")
    print(f" ├── 🎯 API Version: {m.get('api_version', '')}")
    print(f" ├── 👤 Author: {m.get('author', '')}")
    print(f" │")
    print(f" ├── ⚡ Commands ({len(tree['commands'])}):")
    if tree['commands']:
        for i, cmd in enumerate(tree['commands']):
            is_last = (i == len(tree['commands']) - 1)
            prefix = " └──" if is_last else " ├──"
            desc = f" - {cmd['description']}" if cmd.get('description') else ""
            perm = f" [perm: {cmd.get('permission')}]" if cmd.get('permission') and cmd['permission'] != 'None' else ""
            print(f" │   {prefix} {cmd['command']}{desc}{perm}")
    else:
        print(f" │   └── (нет зарегистрированных команд в plugin.yml)")
    print(f" │")
    print(f" ├── 🛡️ Permissions ({len(tree['permissions'])}):")
    if tree['permissions']:
        for i, p in enumerate(tree['permissions'][:15]):
            is_last = (i == min(len(tree['permissions']), 15) - 1)
            prefix = " └──" if is_last else " ├──"
            print(f" │   {prefix} {p['permission']} (default: {p.get('default', 'op')})")
        if len(tree['permissions']) > 15:
            print(f" │   └── ... и ещё {len(tree['permissions']) - 15} прав")
    else:
        print(f" │   └── (нет зарегистрированных прав)")
    print(f" │")
    print(f" └── 🎧 Event Listeners ({len(tree['listeners'])} классов):")
    for i, l in enumerate(tree['listeners'][:10]):
        is_last = (i == min(len(tree['listeners']), 10) - 1)
        prefix = " └──" if is_last else " ├──"
        print(f"     {prefix} {l}")
    if len(tree['listeners']) > 10:
        print(f"     └── ... и ещё {len(tree['listeners']) - 10} классов")

def main():
    if len(sys.argv) < 2:
        print("Использование: python plugin_tree_explorer.py <plugin.jar> [--json]")
        sys.exit(1)

    jar_path = sys.argv[1]
    is_json = '--json' in sys.argv

    tree = build_plugin_tree(jar_path)

    if is_json:
        print(json.dumps(tree, indent=2, ensure_ascii=False))
    else:
        print_ascii_tree(tree)

if __name__ == '__main__':
    main()
