import os
import sys
import requests

desktop_dir = r"C:\Users\nanodev\Desktop\NanoDecompiler"
os.makedirs(desktop_dir, exist_ok=True)

url = "https://api.github.com/repos/NanoDev1488/NanoDecompiler/releases/latest"
headers = {"User-Agent": "Mozilla/5.0"}

print(f"Fetching latest release from {url}...")
resp = requests.get(url, headers=headers, timeout=20)
resp.raise_for_status()
data = resp.json()

tag = data.get("tag_name", "unknown")
print(f"Latest release: {tag}")

assets = data.get("assets", [])
windows_assets = [a for a in assets if "Windows" in a["name"] or a["name"].endswith(".exe")]

print(f"Found {len(windows_assets)} Windows assets:")
for a in windows_assets:
    print(f" - {a['name']} ({a['size']} bytes)")

for a in windows_assets:
    fname = a["name"]
    target_path = os.path.join(desktop_dir, fname)
    download_url = a["browser_download_url"]
    print(f"Downloading {fname} to {target_path}...")
    with requests.get(download_url, headers=headers, stream=True, timeout=60) as r:
        r.raise_for_status()
        with open(target_path, "wb") as f:
            for chunk in r.iter_content(chunk_size=1024 * 1024):
                if chunk:
                    f.write(chunk)
    size_mb = os.path.getsize(target_path) / (1024 * 1024)
    print(f"Done: {fname} ({size_mb:.2f} MB)")

print("\nAll Windows assets successfully downloaded to Desktop\\NanoDecompiler!")
