const https = require('https');
function check() {
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
                let status = runs[0].status;
                let conclusion = runs[0].conclusion;
                if (status === 'completed') {
                    console.log('Run completed! Conclusion: ' + conclusion);
                    process.exit(0);
                } else {
                    console.log('Still ' + status + '...');
                }
            }
        });
    });
}
setInterval(check, 10000);
check();
