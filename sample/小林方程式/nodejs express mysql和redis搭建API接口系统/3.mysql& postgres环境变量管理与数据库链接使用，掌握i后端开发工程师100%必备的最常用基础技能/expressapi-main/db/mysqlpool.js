const dotenv = require("dotenv");
const mysql = require('mysql2/promise');

dotenv.config();
const {
    DATABASE_MY_HOST,
    DATABASE_MY_PORT,
    DATABASE_MY_NAME,
    DATABASE_MY_PASSWORD,
    DATABASE_MY_USERNAME,
    DATABASE_MY_POOLSIZE
} = process.env;

const pool  = mysql.createPool({
    host     : DATABASE_MY_HOST,
    port     : DATABASE_MY_PORT,
    database : DATABASE_MY_NAME,
    user     : DATABASE_MY_USERNAME,
    password : DATABASE_MY_PASSWORD,
    connectionLimit : DATABASE_MY_POOLSIZE
});
console.log("Created a connection pool with 10 active connections to MySQL");

const initDb = async () =>{
    try{
        /**
         * await pool.getConnection() 是手动获取一个数据库的链接方法 如果不使用这个语句 pool将自动分配一个链接给每个query 
         * 而使用getConnection()方法 可以让多个query使用一个固定的数据库连接，这个在事务控制的时候非常有用 
         */
        let connection = await pool.getConnection();
        console.log("Manually acquired a connection from the pool");
        /**
         * mysqlpool.js文件创建的表名是customers,不过表结构和users表是一样的
         */
        let createtableresult = await connection.execute("CREATE TABLE IF NOT EXISTS customers (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))");
        console.log("Created table customers");
        let insertresult = await connection.execute("INSERT INTO customers( name , email ) values ( 'Tom' , 'tom@test.com'),( 'Jerry' , 'jerry@test.com');");
        console.log("Inserted customers data");
        await connection.release();
        console.log("Release this connection to the pool");
    }catch(err){
        console.error(err);
    }
};

const query = async () => {
    try{
        console.log("Automatically acquired a connection from the pool");
        let [rows, fields] = await pool.query("SELECT id,name,email from customers");
        console.log("Queried data from table customers");
        console.log(rows);
    }catch(err){
        console.error(err);
    }
};

const closePool = async () => {
    await pool.end();
    console.log("Close all connections to MySQL");
};

const run = async () =>{
    await initDb();
    await query();
    await closePool();
};

run();


