const https = require('https');
https.get({
    hostname: 'api.github.com',
    path: '/repos/NanoDev1488/NanoDecompiler/actions/runs/36588430056/jobs',
    headers: { 'User-Agent': 'Node.js' }
}, (res) => {
    let data = '';
    res.on('data', chunk => data += chunk);
    res.on('end', () => {
        let jobs = JSON.parse(data).jobs;
        if (jobs) {
            jobs.forEach(j => {
                if (j.conclusion === 'failure') {
                    console.log('Failed job: ' + j.name + ' - ' + j.html_url);
                }
            });
        }
    });
}).on('error', err => console.error(err));
