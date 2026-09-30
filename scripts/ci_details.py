import requests
import subprocess
import re
import sys

sys.stdout.reconfigure(encoding='utf-8', errors='replace')

run_id = sys.argv[1] if len(sys.argv) > 1 else '36714849192'
remote_url = subprocess.check_output(['git', 'config', '--get', 'remote.origin.url'], text=True).strip()
m = re.search(r'https://([^@]+)@github\.com', remote_url)
token = m.group(1) if m else None

headers = {'User-Agent': 'Mozilla/5.0'}
if token:
    headers['Authorization'] = f'token {token}'

url = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs/{run_id}/jobs'
resp = requests.get(url, headers=headers, timeout=15)
data = resp.json()
for j in data.get('jobs', []):
    print(f"Job: {j['name']} (id={j['id']}) - status={j['status']}, conclusion={j['conclusion']}")
    if j['conclusion'] == 'failure':
        for s in j.get('steps', []):
            if s.get('conclusion') == 'failure':
                print(f"  FAILED STEP: {s['name']}")
