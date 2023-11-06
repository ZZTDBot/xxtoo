const dotenv = require("dotenv");
dotenv.config();

const {
  DATABASE_ENGINE,
  DATABASE_HOST,
  DATABASE_NAME,
  DATABASE_USERNAME,
  DATABASE_PASSWORD,
  DATABASE_PORT,
} = process.env;

console.log(
  "database: ",
  DATABASE_ENGINE,
  DATABASE_HOST,
  DATABASE_PORT,
  DATABASE_NAME,
  DATABASE_USERNAME,
  DATABASE_PASSWORD
);

const { SERVER_PORT, SERVER_ENV, LOG_LEVEL } = process.env;

console.log("Result: ", SERVER_PORT, SERVER_ENV, LOG_LEVEL);

