import urllib.request
import json
import sys

def get_job_log(job_id):
    url = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/jobs/{job_id}/logs'
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    try:
        with urllib.request.urlopen(req) as resp:
            text = resp.read().decode('utf-8', errors='replace')
            lines = text.splitlines()
            print(f"--- Last 40 lines of job {job_id} log ---")
            for line in lines[-40:]:
                print(line)
    except Exception as e:
        print(f"Error fetching log: {e}")

def main():
    if len(sys.argv) > 1:
        arg = sys.argv[1]
        if sys.argv[1] == '--log' and len(sys.argv) > 2:
            get_job_log(sys.argv[2])
            return
        if arg.isdigit():
            run_id = arg
            url_jobs = f'https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/runs/{run_id}/jobs'
            req = urllib.request.Request(url_jobs, headers={'User-Agent': 'Mozilla/5.0'})
            with urllib.request.urlopen(req) as resp:
                data = json.loads(resp.read().decode('utf-8'))
                for j in data.get('jobs', []):
                    print(f"Job: {j['name']}, id={j['id']}, conclusion={j['conclusion']}")
                    if j['conclusion'] == 'failure':
                        get_job_log(j['id'])
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
