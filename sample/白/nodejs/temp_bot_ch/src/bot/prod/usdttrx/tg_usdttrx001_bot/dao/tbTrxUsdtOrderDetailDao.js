


//数据库操作工具
const dbutils = require('../../../../common/base-db/dbutils');

//数据库操作工具
const dbPool = require('../config/db/dbPool');

/**
 * 通过fromHashCode查询记录
 * @param fromHashCode
 * @returns {Promise<unknown>}
 */
function selectByFromHashCode(fromHashCode){

    const selectByFromHashCodeSql = 'select from_hash_code from tb_trx_usdt_order_detail where from_hash_code = ?'
    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,selectByFromHashCodeSql,[fromHashCode])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })

}

/**
 * 插入波场查询到的交易记录
 * @param hash
 * @param block
 * @param timestamp
 * @param confirmed
 * @param ownerAddress
 * @param toAddress
 * @param fromCoinType
 * @param fromAmount
 */
function insertFromRow(hash, block, timestamp, confirmed, ownerAddress, toAddress, fromCoinType, fromAmount) {
    const insertFromRow = 'INSERT INTO tb_trx_usdt_order_detail(from_hash_code,from_block,from_timestamp,from_confirmed,from_out_address,from_in_address,from_coin_type,from_amount) ' +
        'VALUES(?,?,?,?,?,?,?,?)'
    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,insertFromRow,[hash, block, timestamp, confirmed, ownerAddress, toAddress, fromCoinType, fromAmount])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}

/**
 * 查询未完成的兑换订单
 * @param fromCoinType   转入币种  1:trx  2:usdt
 * @param lastMinutes      最近几小时的未完成订单
 */
function selectNotFinishedOrder(fromCoinType,lastMinutes) {
    const selectNotFinishedOrderSql = `SELECT
    from_hash_code,from_block,from_timestamp,
        from_confirmed,from_out_address,from_in_address,
        from_coin_type,from_amount,to_hash_code,
        to_timestamp,to_out_address,to_in_address,
        to_coin_type,to_amount,broad_time,broad_qun_id,create_time,
        update_time
    FROM tb_trx_usdt_order_detail
    WHERE to_hash_code IS NULL
    AND from_timestamp >= DATE_SUB(NOW(), INTERVAL ${lastMinutes} MINUTE)
    AND from_coin_type = ${fromCoinType}`

    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,selectNotFinishedOrderSql,[])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}
// dbutils.print(selectNotFinishedOrder(1,240))
/**
 * 更新结算记录
 * @param from_hash_code                     转入的hash
 * @param to_hash_code                       结算的hash
 * @param amount                             结算金额
 * @param to_coin_type                       结算金额类型
 * @param from_out_address                   转出账户
 * @param from_in_address                    转出目标账户
 * @param to_timestamp                       转出时间
 */
function updateJieSuanByFromHash(from_hash_code, to_hash_code, amount, to_coin_type, from_out_address, from_in_address, to_timestamp) {
    // const updateJieSuanByFromHashsQL = `UPDATE tb_trx_usdt_order_detail
    //     SET to_hash_code = ${triggerSmartContractTransferRes},
    //     to_timestamp = ${to_timestamp},
    //     to_out_address = ${from_out_address} ,
    //     to_in_address = ${from_in_address},
    //     to_coin_type = ${to_coin_type} ,
    //     to_amount = ${amount}
    //     WHERE from_hash_code = ${from_hash_code}`
    const updateJieSuanByFromHashsQL = `UPDATE tb_trx_usdt_order_detail
        SET to_hash_code = ?,
        to_timestamp = ?,
        to_out_address = ? ,
        to_in_address = ?,
        to_coin_type = ?,
        to_amount = ?
        WHERE from_hash_code = ?`

    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,updateJieSuanByFromHashsQL,[to_hash_code,to_timestamp,from_out_address,from_in_address,to_coin_type,amount,from_hash_code])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}
// dbutils.print(updateJieSuanByFromHash(
//     '3836fdff22cb21cd3323b815c27cb6a0b19128817bc612cac1ba891b26404056',
//     'hashxxxxx',
//     100,
//     1, 'from_out_address', 'from_in_address', new Date()
// ))

/**
 * 查询最近N小时未广播的订单
 * @param lastHoursNotGbOrder
 * @returns {Promise<unknown>}
 */
function selectNotBroadOrder(lastHoursNotGbOrder) {
    const selectNotBroadOrder = `SELECT
                                     from_hash_code,from_block,from_timestamp,
                                     from_confirmed,from_out_address,from_in_address,
                                     from_coin_type,from_amount,to_hash_code,
                                     to_timestamp,to_out_address,to_in_address,
                                     to_coin_type,to_amount,broad_time,broad_qun_id,create_time,
                                     update_time
                                 FROM tb_trx_usdt_order_detail
                                 WHERE to_hash_code IS NOT NULL
                                   AND to_timestamp >= DATE_SUB(NOW(), INTERVAL ${lastHoursNotGbOrder} HOUR)
                                   AND broad_time IS NULL`

    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,selectNotBroadOrder,[])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}

/**
 * 广播交易信息
 * @param from_hash_code       转入交易的哈希
 * @param orderGbTime          订单广播的时间
 * @param orderGbQunId         订单广播的群ID
 * @returns {Promise<unknown>}
 */
function updateOrderGbInfo(from_hash_code, orderGbTime, orderGbQunId) {
    const updateOrderGbInfoSql = `UPDATE tb_trx_usdt_order_detail
                                 SET broad_time = ?,
                                     broad_qun_id = ?
                                 WHERE from_hash_code = ?`
    return new Promise((resolve, reject) =>{
        dbutils.excuteSqlSync(dbPool.dbPool,updateOrderGbInfoSql,[orderGbTime, orderGbQunId,from_hash_code])
            .then((res,err) => {
                if (err){
                    reject(err)
                }
                resolve(res)
            })
    })
}
// dbutils.print(updateOrderGbInfo(
//     '6293be19c26c1416efd1f318072edfbb23df25fb33f9d0def6051e4123ccc51d',
//     new Date(),
//     111111
// ))



module.exports={
    selectByFromHashCode,
    insertFromRow,
    selectNotFinishedOrder,
    updateJieSuanByFromHash,
    selectNotBroadOrder,
    updateOrderGbInfo
}