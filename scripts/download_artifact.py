import urllib.request
import zipfile
import io
import os
import subprocess

def get_token():
    try:
        out = subprocess.check_output(["git", "config", "remote.origin.url"], text=True)
        if "@github.com" in out:
            user_part = out.split("@github.com")[0]
            if "https://" in user_part:
                return user_part.split("https://")[1]
    except:
        pass
    return None

class NoAuthRedirect(urllib.request.HTTPRedirectHandler):
    def redirect_request(self, req, fp, code, msg, headers, newurl):
        new_req = super().redirect_request(req, fp, code, msg, headers, newurl)
        if new_req is not None and "github.com" not in newurl:
            new_req.headers.pop("Authorization", None)
            new_req.headers.pop("authorization", None)
        return new_req

token = get_token()
opener = urllib.request.build_opener(NoAuthRedirect)

url = "https://api.github.com/repos/NanoDev1488/NanoDecompiler/actions/artifacts/12009945962/zip"
req = urllib.request.Request(url, headers={
    "User-Agent": "Mozilla/5.0",
    "Authorization": f"Bearer {token}",
    "Accept": "application/vnd.github.v3+json"
})

try:
    with opener.open(req) as resp:
        data = resp.read()
        print(f"Downloaded zip: {len(data)} bytes")
        with zipfile.ZipFile(io.BytesIO(data)) as z:
            os.makedirs("artifact_itemsadder_new", exist_ok=True)
            z.extractall("artifact_itemsadder_new")
            print("Extracted files:", z.namelist())
except Exception as e:
    print("Download error:", e)
