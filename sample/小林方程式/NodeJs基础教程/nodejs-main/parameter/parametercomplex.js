let port = 3000;
let env = "dev";
let logLevel = "debug";

const argv = require("minimist")(process.argv.slice(2));
if (argv["p"]) port = argv["p"];
if (argv["port"]) port = argv["port"];
if (argv["e"]) env = argv["e"];
if (argv["env"]) env = argv["env"];
if (argv["l"]) logLevel = argv["l"];
if (argv["logLevel"]) logLevel = argv["logLevel"];

console.log("port=%d, env=%s, logLevel=%s", port, env, logLevel);
