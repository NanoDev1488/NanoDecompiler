import requests
import subprocess
import json
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

    if len(sys.argv) > 1 and sys.argv[1].isdigit():
        run_id = sys.argv[1]
        url_jobs = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs/{run_id}/jobs'
        resp = requests.get(url_jobs, headers=headers, timeout=15)
        data = resp.json()
        for j in data.get('jobs', []):
            print(f"Job: {j['name']} (ID {j['id']}): status={j['status']}, conclusion={j['conclusion']}")
            for s in j.get('steps', []):
                if s.get('conclusion') == 'failure' or s.get('status') == 'in_progress':
                    print(f"    Step: {s.get('name')} -> {s.get('status')} / {s.get('conclusion')}")
        return

    url = 'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs?per_page=6'
    try:
        resp = requests.get(url, headers=headers, timeout=15)
        data = resp.json()
        for r in data.get('workflow_runs', []):
            head_commit = r.get('head_commit', {}) or {}
            msg = head_commit.get('message', '').split('\n')[0][:50]
            print(f"Run {r['id']} ({msg}): status={r['status']}, conclusion={r['conclusion']}, commit={r['head_sha'][:7]}")
    except Exception as e:
        print(f"Error checking CI: {e}")

if __name__ == '__main__':
    main()
