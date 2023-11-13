/**
 * 执行sql的工具类
 * @param params['绑定ID']
 * @returns {Promise<unknown>}
 */
function excuteSql(dbPool,sql,params) {
    console.log(`sql:${sql} \r\n参数:${params}`)
    let promise = new Promise (function(resolve, reject){
        dbPool.query(sql,params ,(err, result) => {
            // 插入数据
            if (err) {
                reject(err)
                // throw err;
                return;
            }
            resolve(result)
        });
    })
    return promise
}

/**
 * 同步执行sql的方法
 * @param sql
 * @param values
 * @returns {Promise<*>}
 */
let excuteSqlSync = async function (dbPool,sql, values){
    return await excuteSql(dbPool,sql, values)
}
// const promise = excuteSqlSync('SELECT * FROM tb_trx_zy_detail',[]);
//
// print(promise)

/**
 * 打印sql的响应结果
 * @param promise
 */
function print(promise) {
    promise.then((res,err) => {
        if(err){
            console.log(err.message)
        }
        console.log(`res:${JSON.stringify(res)}`);
    })
}
module.exports = {
    excuteSql,excuteSqlSync,print
}