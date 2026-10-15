import requests
import subprocess
import re
import sys

def main():
    headers = {'User-Agent': 'Mozilla/5.0'}
    try:
        remote_url = subprocess.check_output(['git', 'config', '--get', 'remote.origin.url'], text=True).strip()
        m = re.search(r'https://([^@]+)@github\.com', remote_url)
        if m:
            headers['Authorization'] = f'token {m.group(1)}'
    except Exception:
        pass

    run_id = sys.argv[1] if len(sys.argv) > 1 else '38777129059'
    url = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs/{run_id}/cancel'
    r = requests.post(url, headers=headers, timeout=15)
    print("Cancel status:", r.status_code)

if __name__ == '__main__':
    main()
