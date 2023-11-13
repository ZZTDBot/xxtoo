const tbScheduleRangeInterverJobDao = require('./dao/TbScheduleRangeInterverJobDao');
const baseDb = require('../../common/base-db');

//引入数据库组件
const mysql  = require('mysql');

//数据库连接池
const dbPool = mysql.createPool({
    host:"localhost",//如果是操作本地数据库，填写127.0.0.1；如果是远程服务器上的数据库填服务公网ip
    port:3306,//如果是操作本地数据库，填写127.0.0.1；如果是远程服务器上的数据库填服务公网ip
    user:"root",//通常为root
    password:"root",//通常为root
    database:"tg_schedule001",//你所要操作的数据库的名称（是你在mysql中建立的数据库）
    charset:'utf8mb4_general_ci'
})

const jobDao = new tbScheduleRangeInterverJobDao.TbScheduleRangeInterverJobDao(dbPool);

// baseDb.dbutils.print(
//     jobDao.insertJob('会话ID','用户ID','任务名称',new Date(),new Date(),1,2,'调度内容')
// )

// baseDb.dbutils.print(
//     jobDao.updateJob(1,'会话IDxxx','用户ID','任务名称',new Date(),new Date(),1,2,'调度内容')
// )

// baseDb.dbutils.print(
//     jobDao.selectById(1)
// )

// baseDb.dbutils.print(
//     jobDao.selectList()
// )

// baseDb.dbutils.print(
//     jobDao.deleteById(1)
// )

// baseDb.dbutils.print(
//     jobDao.deleteAll()
// )

// baseDb.dbutils.print(
//     jobDao.startJobById(4)
// )

// baseDb.dbutils.print(
//     jobDao.stopJobById(4)
// )

// baseDb.dbutils.print(
//     jobDao.startAllJob()
// )

// baseDb.dbutils.print(
//     jobDao.stopAllJob()
// )