import sys

def patch_file(filepath, target, replacement):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    if target in content:
        content = content.replace(target, replacement, 1)
    elif target.replace('\n', '\r\n') in content:
        content = content.replace(target.replace('\n', '\r\n'), replacement.replace('\n', '\r\n'), 1)
    elif target.replace('\r\n', '\n') in content:
        content = content.replace(target.replace('\r\n', '\n'), replacement.replace('\r\n', '\n'), 1)
    else:
        raise ValueError(f"Target not found in {filepath}:\n{target[:100]}...")

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"Patched {filepath} successfully.")

if __name__ == "__main__":
    pass
