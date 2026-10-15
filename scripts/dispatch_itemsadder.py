import requests
import subprocess
import re
import sys

def main():
    headers = {
        'User-Agent': 'Mozilla/5.0',
        'Accept': 'application/vnd.github.v3+json'
    }
    remote_url = subprocess.check_output(['git', 'config', '--get', 'remote.origin.url'], text=True).strip()
    m = re.search(r'https://([^@]+)@github\.com', remote_url)
    if m:
        headers['Authorization'] = f'token {m.group(1)}'

    url = 'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/workflows/test-itemsadder.yml/dispatches'
    data = {'ref': 'main'}
    resp = requests.post(url, headers=headers, json=data, timeout=15)
    print(f"Dispatch status: {resp.status_code}")
    if resp.status_code not in (200, 204):
        print(f"Response: {resp.text}")

if __name__ == '__main__':
    main()
