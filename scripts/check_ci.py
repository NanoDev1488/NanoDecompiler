import urllib.request
import json
import sys

def main():
    if len(sys.argv) > 1 and sys.argv[1].isdigit():
        run_id = sys.argv[1]
        url_jobs = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs/{run_id}/jobs'
        req = urllib.request.Request(url_jobs, headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            for j in data.get('jobs', []):
                print(f"Job: {j['name']} (ID {j['id']}): status={j['status']}, conclusion={j['conclusion']}")
                for s in j.get('steps', []):
                    if s.get('conclusion') == 'failure' or s.get('status') == 'in_progress':
                        print(f"    Step: {s.get('name')} -> {s.get('status')} / {s.get('conclusion')}")
        return

    url = 'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs?per_page=8'
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            for r in data.get('workflow_runs', []):
                print(f"Run {r['id']} ({r.get('display_title', '')[:60]}): status={r['status']}, conclusion={r['conclusion']}, commit={r['head_sha'][:7]}")
    except Exception as e:
        print(f"Error checking CI: {e}")

if __name__ == '__main__':
    main()
