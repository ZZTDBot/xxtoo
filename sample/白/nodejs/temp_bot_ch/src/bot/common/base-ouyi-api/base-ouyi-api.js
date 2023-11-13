const axios = require('axios')

const payTypeMap = new Map()

const ALL_PAY_TYPE = "all";
const ZFB_PAY_TYPE = "aliPay";
const WX_PAY_TYPE = "wxPay";
const BANK_PAY_TYPE = "bank";

payTypeMap.set(ALL_PAY_TYPE,'<strong>Okex商家实时交易汇率top10</strong>');
payTypeMap.set(ZFB_PAY_TYPE,'<strong>Okex商家支付宝实时交易汇率top10</strong>');
payTypeMap.set(WX_PAY_TYPE,'<strong>Okex商家微信实时交易汇率top10</strong>');
payTypeMap.set(BANK_PAY_TYPE,'<strong>Okex商家银行卡实时交易汇率top10</strong>');

//欧易USDT查询接口
const ouyiBPriceQueryUrl =
    'https://www.okx.com/v3/c2c/tradingOrders/books';
// ?t=1683172490136&quoteCurrency=cny&baseCurrency=usdt&side=sell&paymentMethod=all&userType=all&receivingAds=false
/**
 * 查询usdt币价
 * @param payType
 * @param limit
 * @param isSortAsc
 */
async function queryZfbUsdtReltimePriceProccess(payType, limit){
    return new Promise((resolve, reject) => {
        axios({
            url:'https://www.okx.com/v3/c2c/tradingOrders/books?t=1683172490136&quoteCurrency=cny&baseCurrency=usdt&side=sell&paymentMethod='+payType+'&userType=all&receivingAds=false',
            method:'get',
            headers:{
                "User-Agent" : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.53'
            }
        }).then(res => {
            if (res.data.data.sell){
                resolve(res.data.data.sell.slice(0,limit))
            }
        }).catch(err => {
            reject(err)
        })
    })
}



/**
 * 获取文本类型
 * @param sell
 * @param payType
 */
function getReplayContext(sell,payType){
    let titleContext = payTypeMap.get(payType) + '\n';
    for (let i = 0; i < sell.length ; i++) {
        titleContext = titleContext + i + ')   ' + sell[i].price + '      ' + sell[i].nickName + '\n'
    }
    titleContext += '\n'
    return titleContext;
}

/**
 * 查询usdt币价
 * @param payType
 * @param limit
 * @param isSortAsc
 */
async function queryZfbUsdtReltimePrice(payType, limit){
    const data = await queryZfbUsdtReltimePriceProccess(payType, limit)
    return data;
}



// const promise = queryZfbUsdtReltimePriceProccess('all',10,false)
//
// promise.then((res,err) => {
//     const replayContext = getReplayContext(res,'all');
//     console.log('replayContext:' + replayContext)
// })

module.exports = {
    queryZfbUsdtReltimePriceProccess,
    getReplayContext,
    ALL_PAY_TYPE,
    ZFB_PAY_TYPE,
    WX_PAY_TYPE,
    BANK_PAY_TYPE
}

