import requests
import subprocess
import re
import sys

sys.stdout.reconfigure(encoding='utf-8', errors='replace')

job_id = sys.argv[1] if len(sys.argv) > 1 else '109878736247'
remote_url = subprocess.check_output(['git', 'config', '--get', 'remote.origin.url'], text=True).strip()
m = re.search(r'https://([^@]+)@github\.com', remote_url)
token = m.group(1) if m else None

headers = {'User-Agent': 'Mozilla/5.0'}
if token:
    headers['Authorization'] = f'token {token}'

url = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/jobs/{job_id}/logs'
r = requests.get(url, headers=headers, timeout=20)
lines = r.text.splitlines()
start = int(sys.argv[2]) if len(sys.argv) > 2 else max(0, len(lines) - 80)
end = int(sys.argv[3]) if len(sys.argv) > 3 else len(lines)
for i in range(start, min(end, len(lines))):
    print(f"[{i}] {lines[i]}")
