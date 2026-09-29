const https = require('https');
https.get({
    hostname: 'api.github.com',
    path: '/repos/NanoDev1488/NanoDecompiler/actions/jobs/109474950103/logs',
    headers: { 'User-Agent': 'Node.js' }
}, (res) => {
    if (res.statusCode === 302) {
        https.get(res.headers.location, (res2) => {
            let data = '';
            res2.on('data', chunk => data += chunk);
            res2.on('end', () => console.log(data.substring(data.length - 2000)));
        });
    } else {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => console.log(data));
    }
}).on('error', err => console.error(err));
