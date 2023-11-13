const axios = require('axios') // 引入http客户端

//波场查trx和usdt价格的api
const TOKEN_TYPE_TRX = 'trx'
const TOKEN_TYPE_USDT='usdt'
// const queryTronTokenPriceUrl = "https://apilist.tronscanapi.com/api/token/price?token=%s";
const queryTronTokenPriceUrl = "https://apilist.tronscanapi.com/api/token/price";


//波场链TRX的交易查询api
// const queryTronTrxOrderUrl = "https://apilist.tronscanapi.com/api/transaction?count=true&sort=-timestamp&start=0&start_timestamp=%s&address=%s&limit=%s";
const queryTronTrxOrderUrl = "https://apilist.tronscanapi.com/api/transaction?count=true&sort=-timestamp&start=0";

//波场链USDT的交易查询api
// const queryTronUsdtOrderUrl = "https://apilist.tronscanapi.com/api/token_trc20/transfers?start=0&sort=-timestamp&count=true&start_timestamp=%s&relatedAddress=%s&limit=%s";
const queryTronUsdtOrderUrl = "https://apilist.tronscanapi.com/api/token_trc20/transfers?start=0&sort=-timestamp&count=true";

/**
 * 获取币种的价格
 * @param tokenTypeEnum
 * @return
 */
async function getTronTokenPrice(tokenType){
    return new Promise((resolve, reject) => {
        const queryUrl = queryTronTokenPriceUrl + '?token='+tokenType;
        console.log(`准备查询${tokenType}的价格......  查询连接:${queryUrl}`);
        axios({
            url:queryUrl,
            method:'get',
            headers:{
                "User-Agent" : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36',
                "Accept": 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7'
            }
        }).then(res => {
            if (res.data){
                resolve(res.data)
            }
        }).catch(err => {
            console.error("获取币种的价格失败........",err)
            reject(err)
        })
    })
}
// getTronTokenPrice(TOKEN_TYPE_TRX).then((res,err) => {
//     console.log('res:' + JSON.stringify(res))
// })

/**
 * 获取波场链上最近的N笔已经确认了的TRX转账记录
 * @param relatedAddress                   Trc钱包地址
 * @param limit                            最近多少条
 * @param isFilterNotConfirmedOrder        是否过滤没有确认的订单
 * @param startTimestamp                   查询什么时间点之后的交易
 * @return
 */
function scanTronTrxList(startTimestamp,relatedAddress, limit){
// &start_timestamp=%s&address=%s&limit=%s
    return new Promise((resolve, reject) => {
        const queryUrl = queryTronTrxOrderUrl + '&start_timestamp=' + startTimestamp + '&address=' + relatedAddress + '&limit=' + limit;
        console.log(`准备查询${relatedAddress}地址Trx的交易记录......  查询连接:${queryUrl}`);
        axios({
            url:queryUrl,
            method:'get',
            headers:{
                "User-Agent" : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36',
                "Accept": 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7'
            }
        }).then(res => {
            if (res.data){
                resolve(res.data)
            }
        })
            .catch(err => {
            console.error("查询TRX的交易记录失败........",err)
            reject(err)
        })
        //     .catch((e: AxiosError) => {
        //         console.log(e.message)
        //         console.log(e.request)
        //         console.log(e.code)
        //     })
    })
}

// for (let i = 0; i < 100; i++) {
//     console.log(`请求第${i}次`)
//     scanTronTrxList(new Date().getTime() - (3 * 24 * 60 * 60 * 1000),'TCh1G6aSTzYBMZGE1PxY8YoDfvxvoz8RnL',10).then((res,err) => {
//         console.log('res:' + JSON.stringify(res))
//     })
// }

/**
 * 获取波场链上最近的N笔已经确认了的TRX转账记录
 * @param startTimestamp                   查询什么时间点之后的交易
 * @param relatedAddress                   Trc钱包地址
 * @param limit                            最近多少条
 * @return
 */
function scanTronUsdtList(startTimestamp,relatedAddress, limit) {
    // const queryTronUsdtOrderUrl = "https://apilist.tronscanapi.com/api/token_trc20/transfers?start=0&sort=-timestamp&count=true&start_timestamp=%s&relatedAddress=%s&limit=%s";

    return new Promise((resolve, reject) => {
        const queryUrl = queryTronUsdtOrderUrl + '&start_timestamp=' + startTimestamp + '&relatedAddress=' + relatedAddress + '&limit=' + limit;
        console.log(`准备查询${relatedAddress}地址USDT的交易记录......  查询连接:${queryUrl}`);
        axios({
            url:queryUrl,
            method:'get',
            headers:{
                "User-Agent" : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36',
                "Accept": 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7'
            }
        }).then(res => {
            if (res.data){
                resolve(res.data)
            }
        }).catch(err => {
            console.error("查询USDT的交易记录失败........",err)
            reject(err)
        })
    })
}
// scanTronUsdtList(new Date().getTime() - (3 * 24 * 60 * 60 * 1000),'TCh1G6aSTzYBMZGE1PxY8YoDfvxvoz8RnL',10).then((res,err) => {
//     console.log('res:' + JSON.stringify(res))
// })


module.exports = {
    TOKEN_TYPE_TRX,
    TOKEN_TYPE_USDT,
    getTronTokenPrice,
    scanTronTrxList,
    scanTronUsdtList
}