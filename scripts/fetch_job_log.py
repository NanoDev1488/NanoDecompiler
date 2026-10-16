import urllib.request
import subprocess
import sys

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
    job_id = sys.argv[1] if len(sys.argv) > 1 else "116696024026"
    url = f"https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/jobs/{job_id}/logs"
    req = urllib.request.Request(url, headers={
        "User-Agent": "Mozilla/5.0",
        "Authorization": f"Bearer {token}",
    })

    with opener.open(req) as resp:
        text = resp.read().decode('utf-8', errors='replace')

    lines = text.splitlines()

    # Show lines around errors
    start = int(sys.argv[2]) if len(sys.argv) > 2 else None
    end = int(sys.argv[3]) if len(sys.argv) > 3 else None

    if start is not None:
        end = end or min(len(lines), start + 50)
        for i in range(start, min(end, len(lines))):
            print(f"[{i}] {lines[i]}")
    else:
        error_indices = [i for i, l in enumerate(lines)
                         if "error" in l.lower() or "failed" in l.lower()
                         or "avl" in l.lower() or "exception" in l.lower()
                         or "warning cs" in l.lower() or "error cs" in l.lower()]
        print(f"Total log lines: {len(lines)}, error matches: {len(error_indices)}")
        for idx in error_indices[-15:]:
            start_ctx = max(0, idx - 3)
            for i in range(start_ctx, min(idx + 2, len(lines))):
                print(f"[{i}] {lines[i]}")
            print("---")

if __name__ == "__main__":
    main()
