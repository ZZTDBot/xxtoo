const envs = process.env;

const server_port = envs.SERVER_PORT;
const server_env = envs.SERVER_ENV;
const log_lever = envs.LOG_LEVEL;
console.log("set 1: ", server_port, server_env, log_lever);

const { SERVER_PORT, SERVER_ENV, LOG_LEVEL } = process.env;
console.log("set 2: ", SERVER_PORT, SERVER_ENV, LOG_LEVEL);

const {
  DATABASE_HOST,
  DATABASE_USERNAME,
  DATABASE_PORT,
  DATABASE_NAME,
} = process.env;
console.log(
  "database connection: ",
  DATABASE_HOST,
  DATABASE_PORT,
  DATABASE_NAME,
  DATABASE_USERNAME
);
