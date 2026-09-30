const https = require('https');
const fs = require('fs');

function getToken() {
    if (process.env.GITHUB_TOKEN) return process.env.GITHUB_TOKEN;
    if (fs.existsSync('.git/config')) {
        const conf = fs.readFileSync('.git/config', 'utf8');
        const m = conf.match(/https:\/\/([a-zA-Z0-9_]+)@github\.com/);
        if (m) return m[1];
    }
    return '';
}

function check() {
    const token = getToken();
    const headers = { 'User-Agent': 'Node.js' };
    if (token) headers['Authorization'] = 'token ' + token;

    https.get({
        hostname: 'api.github.com',
        path: '/repos/NanoDev1488/NanoDecompiler/actions/runs?per_page=5',
        headers: headers
    }, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
            try {
                let runs = JSON.parse(data).workflow_runs;
                if (runs && runs.length > 0) {
                    console.log('=== Recent Workflow Runs ===');
                    for (const r of runs) {
                        const commitMsg = r.head_commit ? r.head_commit.message.split('\n')[0] : '';
                        console.log(`Run ${r.id} | ${r.name} | ${commitMsg} | ${r.status} | ${r.conclusion}`);
                    }
                } else {
                    console.log('No runs found or error:', data);
                }
            } catch (e) {
                console.error('Error parsing response:', e);
            }
        });
    }).on('error', err => {
        console.error('Request error:', err);
    });
}

check();
