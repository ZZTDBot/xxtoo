// const dbPool = require('../../db/dbPool');
//1.插入或者更新一个用户的sql
// const params = ['a920','lll','fff','xxx','xxoo'] //测试用的参数
const upsertUserSql = 'INSERT INTO tb_users ( user_name) VALUES ( ?) ON DUPLICATE KEY UPDATE update_time = now()';
// dbPool.print(dbPool.excuteSql(upsertUserSql,params)) //测试方法

//2.通过用户查询的sql
// let params = ['a920']  //测试用的参数
const queryUserByUserNameSql = 'select user_name,create_time,update_time from tb_users where user_name = ?';
// dbPool.print(dbPool.excuteSql(queryUserByUserNameSql,params))  //测试方法

//3.删除用户的sql
// let params = ['a920']  //测试用的参数
const delUserByUserNameSql = 'delete from tb_users where user_name = ?';
// dbPool.print(dbPool.excuteSql(delUserByUserNameSql,params))  //测试方法

//4.查询所有用户的sql
const queryAllSql = 'select * from tb_users ';
// dbPool.print(dbPool.excuteSql(queryAllSql,[]))  //测试方法

module.exports = {
    upsertUserSql,queryUserByUserNameSql,delUserByUserNameSql,queryAllSql
}