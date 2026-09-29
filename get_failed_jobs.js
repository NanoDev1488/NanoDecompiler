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
            let run_id = runs[0].id;
            https.get({
                hostname: 'api.github.com',
                path: '/repos/NanoDev1488/NanoDecompiler/actions/runs/' + run_id + '/jobs',
                headers: { 'User-Agent': 'Node.js' }
            }, (res2) => {
                let data2 = '';
                res2.on('data', chunk => data2 += chunk);
                res2.on('end', () => {
                    let jobs = JSON.parse(data2).jobs;
                    if (jobs) {
                        jobs.forEach(j => {
                            if (j.conclusion === 'failure') {
                                console.log('Failed job: ' + j.name + ' ID: ' + j.id);
                            }
                        });
                    }
                });
            });
        }
    });
}).on('error', err => console.error(err));
