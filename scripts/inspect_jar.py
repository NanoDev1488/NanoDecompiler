import zipfile
import sys
import os

jar_path = sys.argv[1] if len(sys.argv) > 1 else 'test_jars/TCCR-crack.jar'
if not os.path.exists(jar_path):
    print(f"File not found: {jar_path}")
    sys.exit(1)

with zipfile.ZipFile(jar_path, 'r') as z:
    names = z.namelist()
    print(f"Total entries in {jar_path}: {len(names)}")
    classes = [n for n in names if n.endswith('.class')]
    print(f"Total .class files: {len(classes)}")
    
    # Check plugin.yml
    if 'plugin.yml' in names:
        print("\n--- plugin.yml ---")
        try:
            print(z.read('plugin.yml').decode('utf-8', errors='replace')[:500])
        except Exception as e:
            print(e)

    print("\n--- Sample classes (first 30) ---")
    for c in classes[:30]:
        print(c)

    print("\n--- Package structure ---")
    pkg_counts = {}
    for c in classes:
        parts = c.split('/')
        pkg = '/'.join(parts[:-1]) if len(parts) > 1 else '<root>'
        pkg_counts[pkg] = pkg_counts.get(pkg, 0) + 1
    for p, count in sorted(pkg_counts.items()):
        print(f"  {p} ({count} classes)")
