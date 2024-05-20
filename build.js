#!/usr/bin/env node
const version = require('fs').readFileSync("./app/build.gradle").toString().split("\n").map(i => i.trim()).find(i => i.startsWith("versionName")).split('"')[1];

console.log("Build the project in Android Studio and press any key to publish.");
process.stdin.setRawMode(true);
process.stdin.resume();
process.stdin.on('data', () => {
    require('child_process').execSync(`curl -v --header "PRIVATE-TOKEN: $(cat ~/.deploy.txt)" --header "Content-Type: multipart/form-data" --upload-file ./app/release/app-release.apk https://source.equestria.dev/api/v4/projects/89/packages/generic/ponypush/${version}/dev.equestria.ponypush.apk`, { stdio: "inherit" });
    process.exit();
});