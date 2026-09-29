const https = require('https');
https.get({
    hostname: 'api.github.com',
    path: '/repos/NanoDev1488/NanoDecompiler/actions/runs?per_page=1',
    headers: { 'User-Agent': 'Node.js' }
}, (res) => {
    let data = '';
    res.on('data', chunk => data += chunk);
    res.on('end', () => {
        let runs = JSON.parse(data).workflow_runs;
        if (runs && runs.length > 0) {
            console.log('Run status: ' + runs[0].status + ', conclusion: ' + runs[0].conclusion);
        } else {
            console.log('No runs found');
        }
    });
}).on('error', err => console.error(err));
