import urllib.request
import subprocess

def main():
    out = subprocess.check_output(['git', 'config', 'remote.origin.url'], text=True)
    token = out.split('@github.com')[0].split('https://')[1]
    
    class NoAuthRedirect(urllib.request.HTTPRedirectHandler):
        def redirect_request(self, req, fp, code, msg, headers, newurl):
            new_req = super().redirect_request(req, fp, code, msg, headers, newurl)
            if new_req is not None and "github.com" not in newurl:
                new_req.headers.pop("Authorization", None)
                new_req.headers.pop("authorization", None)
            return new_req

    opener = urllib.request.build_opener(NoAuthRedirect)
    job_id = "116696024026"
    url = f"https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/jobs/{job_id}/logs"
    req = urllib.request.Request(url, headers={
        "User-Agent": "Mozilla/5.0",
        "Authorization": f"Bearer {token}",
    })
    
    with opener.open(req) as resp:
        text = resp.read().decode('utf-8', errors='replace')
    
    lines = text.splitlines()
    error_indices = [i for i, l in enumerate(lines) if "error" in l.lower() or "failed" in l.lower() or "msb" in l.lower()]
    print(f"Total log lines: {len(lines)}, error matches: {len(error_indices)}")
    if error_indices:
        last = error_indices[-1]
        start = max(0, last - 15)
        end = min(len(lines), last + 25)
        print("=== RELEVANT ERROR LOG ===")
        for i in range(start, end):
            print(lines[i])

if __name__ == "__main__":
    main()
