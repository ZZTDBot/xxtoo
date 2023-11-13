//引入操作数据库的工具类
const baseDb = require('../../base-db/base-db');
//引入操作数据库的sql
const TbSysConfigSqlConfig = require('../config/sql/TbSysConfigSqlConfig');

/**
 * 更新或者删除系统参数
 * @param dbPool
 * @param params
 * @returns {Promise<unknown>}
 */
function upsertKeyValue(dbPool,params){
    return new Promise((resolve, reject) =>{
        baseDb.dbutils.excuteSqlSync(dbPool,TbSysConfigSqlConfig.upsertSql,params)
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}

/**
 * 通过Key查询系统参数
 * @param dbPool
 * @param params
 * @returns {Promise<unknown>}
 */
function queryValueByKeyName(dbPool,params){
    return new Promise((resolve, reject) =>{
        baseDb.dbutils.excuteSqlSync(dbPool,TbSysConfigSqlConfig.queryByKeyNameSql,params)
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}

/**
 * 查询所有的系统参数
 * @param dbPool
 * @param params
 * @returns {Promise<unknown>}
 */
function queryAll(dbPool){
    return new Promise((resolve, reject) =>{
        baseDb.dbutils.excuteSqlSync(dbPool,TbSysConfigSqlConfig.queryAllSql,[])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}

/**
 * 更新Key删除系统参数
 * @param dbPool
 * @param params
 * @returns {Promise<unknown>}
 */
function delByKeyName(dbPool,params){
    return new Promise((resolve, reject) =>{
        baseDb.dbutils.excuteSqlSync(dbPool,TbSysConfigSqlConfig.delByKeyNameSql,params)
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}

module.exports ={
    upsertKeyValue,
    queryValueByKeyName,
    queryAll,
    delByKeyName
}