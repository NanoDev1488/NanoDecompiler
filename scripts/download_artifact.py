import requests
import subprocess
import re
import zipfile
import io
import json

remote_url = subprocess.check_output(['git', 'config', '--get', 'remote.origin.url'], text=True).strip()
m = re.search(r'https://([^@]+)@github\.com', remote_url)
token = m.group(1) if m else None

headers = {'User-Agent': 'Mozilla/5.0'}
if token:
    headers['Authorization'] = f'token {token}'

artifact_id = '11975372551'
url = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/artifacts/{artifact_id}/zip'
r = requests.get(url, headers=headers, stream=True, timeout=30)
if r.status_code == 200:
    with zipfile.ZipFile(io.BytesIO(r.content)) as z:
        z.extractall("artifact_itemsadder")
    print("Artifact extracted to artifact_itemsadder/")
else:
    print(f"Failed to download artifact: {r.status_code} {r.text}")
