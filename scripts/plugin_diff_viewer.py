import zipfile
import hashlib
import json
import sys
import os

def get_jar_classes(jar_path):
    classes = {}
    manifest = {}
    with zipfile.ZipFile(jar_path, 'r') as z:
        for info in z.infolist():
            name = info.filename
            if name.endswith('.class') and not 'module-info' in name:
                data = z.read(name)
                h = hashlib.sha256(data).hexdigest()
                classes[name] = {'size': info.file_size, 'hash': h, 'bytes': data}
            elif name == 'plugin.yml':
                try:
                    manifest['plugin.yml'] = z.read(name).decode('utf-8', errors='replace')
                except Exception:
                    pass
    return classes, manifest

def compare_jars(jar_a, jar_b):
    classes_a, man_a = get_jar_classes(jar_a)
    classes_b, man_b = get_jar_classes(jar_b)

    names_a = set(classes_a.keys())
    names_b = set(classes_b.keys())

    added = sorted(list(names_b - names_a))
    removed = sorted(list(names_a - names_b))
    common = names_a & names_b

    modified = []
    identical = []

    for name in sorted(list(common)):
        if classes_a[name]['hash'] != classes_b[name]['hash']:
            size_diff = classes_b[name]['size'] - classes_a[name]['size']
            modified.append({
                'class': name,
                'size_old': classes_a[name]['size'],
                'size_new': classes_b[name]['size'],
                'size_diff': size_diff
            })
        else:
            identical.append(name)

    manifest_changed = man_a.get('plugin.yml') != man_b.get('plugin.yml')

    return {
        'jar_a': os.path.basename(jar_a),
        'jar_b': os.path.basename(jar_b),
        'total_classes_a': len(classes_a),
        'total_classes_b': len(classes_b),
        'added_count': len(added),
        'removed_count': len(removed),
        'modified_count': len(modified),
        'identical_count': len(identical),
        'added': added,
        'removed': removed,
        'modified': modified,
        'manifest_changed': manifest_changed
    }

def main():
    if len(sys.argv) < 3:
        print("Использование: python plugin_diff_viewer.py <plugin_v1.jar> <plugin_v2.jar> [--json]")
        sys.exit(1)

    jar_a = sys.argv[1]
    jar_b = sys.argv[2]
    is_json = '--json' in sys.argv

    diff = compare_jars(jar_a, jar_b)

    if is_json:
        print(json.dumps(diff, indent=2, ensure_ascii=False))
        return

    print("==================================================")
    print(f" NanoDecompiler Plugin Diff Viewer (Experimental)")
    print("==================================================")
    print(f"Файл A: {diff['jar_a']} ({diff['total_classes_a']} классов)")
    print(f"Файл B: {diff['jar_b']} ({diff['total_classes_b']} классов)")
    print(f"Манифест (plugin.yml): {'ИЗМЕНЁН' if diff['manifest_changed'] else 'Без изменений'}")
    print(f"Идентичных классов: {diff['identical_count']}")
    print(f"Модифицированных классов: {diff['modified_count']}")
    print(f"Добавленных классов: {diff['added_count']}")
    print(f"Удалённых классов: {diff['removed_count']}")
    print("--------------------------------------------------")

    if diff['modified']:
        print("\n[*] Модифицированные классы (возможный патч/кряк/обновление):")
        for m in diff['modified'][:25]:
            diff_str = f"+{m['size_diff']}" if m['size_diff'] >= 0 else str(m['size_diff'])
            print(f"  * {m['class']} ({diff_str} байт)")
        if len(diff['modified']) > 25:
            print(f"  ... и ещё {len(diff['modified']) - 25} классов")

    if diff['added']:
        print("\n[+] Добавленные классы:")
        for a in diff['added'][:15]:
            print(f"  + {a}")
        if len(diff['added']) > 15:
            print(f"  ... и ещё {len(diff['added']) - 15} классов")

    if diff['removed']:
        print("\n[-] Удалённые классы:")
        for r in diff['removed'][:15]:
            print(f"  - {r}")

if __name__ == '__main__':
    main()
