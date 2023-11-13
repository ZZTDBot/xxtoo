//引入数据库连接池
const baseDb = require('../base-db');

//引入操作数据库表的Sql类
const TbUsersSqlConfig = require('./config/sql/TbUsersSqlConfig');

//-------------------管理命令-----------------------
//添加管理员
function addAdmin(telebot,dbPool){
    telebot.onText(/^addAdmin(.+)/, (msg, match) => {
        if(msg.chat.type === 'private') {
            baseDb.dbutils.excuteSql(dbPool,TbUsersSqlConfig.upsertUserSql, [match[1]]).then((res, err) => {
                    if (err) {
                        console.log(err.message)
                        telebot.sendMessage(msg.chat.id, `添加失败，未知异常！！！`)
                        return;
                    }
                    telebot.sendMessage(msg.chat.id, `添加管理员成功！！！`)
                }
            )
        }
    });
}


//删除管理员
function delAdmin(telebot,dbPool){
    telebot.onText(/^delAdmin(.+)/, (msg, match) => {
        if(msg.chat.type === 'private') {
            baseDb.dbutils.excuteSql(dbPool,TbUsersSqlConfig.delUserByUserNameSql, [match[1]]).then((res, err) => {
                    if (err) {
                        console.log(err.message)
                        telebot.sendMessage(msg.chat.id, `删除失败，未知异常！！！`)
                        return;
                    }
                    telebot.sendMessage(msg.chat.id, `删除管理员成功！！！`)
                }
            )
        }
    });
}


//查询管理员
function queryAdmin(telebot,dbPool){
    telebot.onText(/^queryAdmin/, (msg, match) => {
        if(msg.chat.type === 'private') {
            baseDb.dbutils.excuteSql(dbPool,TbUsersSqlConfig.queryAllSql, []).then((res, err) => {
                    if (err) {
                        console.log(err.message)
                        telebot.sendMessage(msg.chat.id, `查询失败，未知异常！！！`)
                        return;
                    }
                    if(!res || res.length == 0){
                        telebot.sendMessage(msg.chat.id,`没有记录！！！`)
                        return;
                    }
                    let result = '';
                    for(let index in res){
                        result = result + JSON.stringify(res[index]) + "\r\n"
                    }
                    telebot.sendMessage(msg.chat.id, result)
                }
            )
        }
    });
}

module.exports = {
    addAdmin,
    delAdmin,
    queryAdmin
}