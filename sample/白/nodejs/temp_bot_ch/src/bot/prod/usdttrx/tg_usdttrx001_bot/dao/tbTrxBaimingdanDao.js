//数据库操作工具
const dbutils = require('../../../../common/base-db/dbutils');

//数据库操作工具
const dbPool = require('../config/db/dbPool');

/**
 * 查询白名单地址
 */
function selectAllAddress(){
    const selectAllAddressSql = 'select zy_adrress from tb_trx_baimingdan'
    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,selectAllAddressSql,[])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}
// const promise = selectAllAddress();
// dbutils.print(promise)

module.exports = {
    selectAllAddress
}