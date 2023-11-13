const baseDb = require('../../../common/base-db');

/**
 * 禁言关键词
 */
class TbChatKeywordsJyDao{

    constructor(dbPool) {
        this.pool = dbPool;
    }

    /**
     * 插入或更新一个禁言词
     * @param tg_chat_id     群ID
     * @param key_word
     * @returns {Promise<*>}
     */
    insertOrUpdate(tg_chat_id,key_word){
        const sql = 'INSERT INTO tb_chat_keywords_jy(tg_chat_id,key_word) values(?, ?) ON DUPLICATE KEY UPDATE update_time = now()'
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[tg_chat_id,key_word,key_word])
    }

    /**
     * 查询指定群所有的禁言词
     * @param tg_chat_id
     * @returns {Promise<*>}
     */
    selectAllByChatId(tg_chat_id){
        const sql = 'select tg_chat_id,key_word,update_time from tb_chat_keywords_jy where tg_chat_id = ?'
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[tg_chat_id])
    }

    /**
     * 检查是否是禁言词
     * @param word
     * @returns {Promise<unknown>}
     */
    isJyWords(tg_chat_id,word){
        return new Promise((resolve, reject) => {
            const sql = "SELECT * FROM tb_chat_keywords_jy WHERE tg_chat_id = ? and ? LIKE CONCAT('%',key_word ,'%')"
            baseDb.dbutils.excuteSqlSync(this.pool,sql,[tg_chat_id,word])
                .then((res,err) => {
                    if (err){
                        console.error(err)
                        reject(err)
                    }
                    if(res && res.length > 0){
                        resolve(true)
                    }
                })
        })
    }

    /**
     * 删除指定群的指定禁言关键词
     * @param tg_chat_id
     * @param key_word
     * @returns {Promise<*>}
     */
    deleteByChatIdAndKeyWord(tg_chat_id,key_word){
        const sql = `delete from tb_chat_keywords_jy
                     WHERE tg_chat_id = ? and key_word = ?;`
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[tg_chat_id,key_word])
    }

    /**
     * 删除指定群的所有禁言关键词
     * @param tg_chat_id
     * @returns {Promise<*>}
     */
    deleteAllByChatId(tg_chat_id){
        const sql = `delete from tb_chat_keywords_jy
                     WHERE tg_chat_id = ?;`
        return baseDb.dbutils.excuteSqlSync(this.pool,sql,[tg_chat_id])
    }

}

module.exports = {
    TbChatKeywordsJyDao
}

// // 引入数据库组件
// const mysql  = require('mysql');
//
// //数据库连接池
// const dbPool = mysql.createPool({
//     host:"localhost",//如果是操作本地数据库，填写127.0.0.1；如果是远程服务器上的数据库填服务公网ip
//     port:3306,//通常为root
//     user:"root",//通常为root
//     // password:"mId-MCwBF3MC!",//密码
//     password:"root",//密码
//     database:"test",//你所要操作的数据库的名称（是你在mysql中建立的数据库）
//     charset:'utf8mb4_general_ci'
// })
//
// const tbChatKeywordsJyDaoImpl = new TbChatKeywordsJyDao(dbPool);
// baseDb.dbutils.print(
//     tbChatKeywordsJyDaoImpl.insertOrUpdate('111','代号11')
// )

// baseDb.dbutils.print(
//     tbChatKeywordsJyDaoImpl.deleteByChatIdAndKeyWord('111','代号11')
// )

// baseDb.dbutils.print(
//     tbChatKeywordsJyDaoImpl.deleteAllByChatId('111')
// )
