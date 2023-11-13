//引入数据库组件
const mysql  = require('mysql');
//引入数据库配置
const dbconfig  = require('../../../../../common/base-db/dbconfig');

// const dbutils = require('../../../../../common/base-db/dbutils');

//数据库连接池
const dbPool = mysql.createPool({
    host:dbconfig.host,//如果是操作本地数据库，填写127.0.0.1；如果是远程服务器上的数据库填服务公网ip
    port:dbconfig.port,
    user:dbconfig.user,//通常为root
    password:dbconfig.password,//密码
    database:"tg_usdttrx001_bot",//你所要操作的数据库的名称（是你在mysql中建立的数据库）
    charset:'utf8mb4_general_ci'
})

// let p = dbutils.excuteSqlSync(dbPool,'select now()',[])
// dbutils.print(p)

module.exports = {
    dbPool
}