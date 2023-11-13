// const dbPool = require('../db/dbPool');
//1.插入或者更新一个用户的sql
// const params = ['k1','lll','333'] //测试用的参数
const upsertSql = 'INSERT INTO tb_sys_config ( key_name , value_) VALUES ( ? , ?) ON DUPLICATE KEY UPDATE value_ = ?';
// dbPool.print(dbPool.excuteSql(upsertSql,params)) //测试方法

//2.通过key查询value的sql
// let params = ['k1']  //key
const queryByKeyNameSql = 'select value_ from tb_sys_config where key_name = ?';
// dbPool.print(dbPool.excuteSql(queryByKeyNameSql,params))  //测试方法

//3.查询所有用户的sql
const queryAllSql = 'select * from tb_sys_config ';
// dbPool.print(dbPool.excuteSql(queryAllSql,[]))  //测试方法

//4.通过key删除的sql
// let params = ['k1']  //测试用的参数
const delByKeyNameSql = 'delete from tb_sys_config where key_name = ?';
// dbPool.print(dbPool.excuteSql(delByKeyNameSql,params))  //测试方法


module.exports = {
    upsertSql,
    queryByKeyNameSql,
    queryAllSql,
    delByKeyNameSql
}