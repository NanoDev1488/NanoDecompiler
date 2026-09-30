import urllib.request
import json
import sys

def main():
    if len(sys.argv) > 1 and sys.argv[1].isdigit():
        run_id = sys.argv[1]
        url = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs/{run_id}/jobs'
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(req) as resp:
            data = json.loads(resp.read().decode('utf-8'))
            print(f"Jobs for Run {run_id}:")
            for j in data.get('jobs', []):
                print(f"  - {j['name']}: {j['status']} / {j['conclusion']}")
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
