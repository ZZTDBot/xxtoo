const argv = require("minimist")(process.argv.slice(2));
console.log(argv);
console.log(
  "port=%d, env=%s, logLevel=%s",
  argv["port"],
  argv["env"],
  argv["logLevel"]
);
