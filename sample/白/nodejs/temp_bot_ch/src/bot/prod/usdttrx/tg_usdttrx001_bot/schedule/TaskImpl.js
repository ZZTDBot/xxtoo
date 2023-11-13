//引入波场的操作API
const baseTronApi = require('../../../../common/base-tron-api');
const botconfig = require('../config/bot/botconfig');
const tbTrxUsdtOrderDetailServiceImpl = require("../service/TbTrxUsdtOrderDetailServiceImpl");

//引入调度框架
const schedule = require('node-schedule');

/**
 * 查询波场trx订单写入数据库
 */
function queryTrxDetail() {
    console.log("波场trx订单查询方法启动......");
    baseTronApi.scanTronTrxList(
        new Date().getTime() - (botconfig.tronOrderMinute * 60 * 1000),
        botconfig.reciveAdrress,
        botconfig.tronOrderMinuteCount)
        .then((res,err) =>{
            if(err){
                console.log('请求波场的trx交易记录失败....')
            }
            console.log(JSON.stringify(res))
            tbTrxUsdtOrderDetailServiceImpl.insertTrxOrderDetail(res)
        })
}

/**
 * 查询波场usdt订单写入数据库
 */
function queryUsdtDetail() {
    console.log("波场usdt订单查询方法启动......");
    baseTronApi.scanTronUsdtList(
        new Date().getTime() - (botconfig.tronOrderMinute * 60 * 1000),
        botconfig.reciveAdrress,
        botconfig.tronOrderMinuteCount)
        .then((res,err) =>{
            if(err){
                console.log('请求波场的usdt交易记录失败....')
            }
            console.log(JSON.stringify(res))
            tbTrxUsdtOrderDetailServiceImpl.insertUsdtOrderDetail(res)
        })
}

/**
 * 对转入的trx进行结算
 */
function exchangeTrxToUsdt(){
    console.log("波场兑换trx兑换Usdt方法启动......");
    tbTrxUsdtOrderDetailServiceImpl.exchangeTrxToUsdt()
}
// exchangeTrxToUsdt()

/**
 * 对转入的trx进行结算
 */
function exchangeUsdtToTrx(){
    console.log("波场兑换trx兑换Usdt方法启动......");
    tbTrxUsdtOrderDetailServiceImpl.exchangeUsdtToTrx()
}
// exchangeUsdtToTrx()

/**
 * 广播交易信息
 */
function broadOrder(trxSimpleBot) {
    console.log("广播交易信息的方法启动......");
    tbTrxUsdtOrderDetailServiceImpl.broadOrder(trxSimpleBot)
}


// var j = schedule.scheduleJob('0-59/3 * * * * ?', function(){
//     console.log('生命，宇宙，一切的答案。。。!');
// });

// 启动兑币调度方法
let queryTrxDetailJob = schedule.scheduleJob('15,45 * * * * ?', queryTrxDetail);
let queryUsdtDetailJob = schedule.scheduleJob('0,30,59 * * * * ?', queryUsdtDetail);
let exchangeTrxToUsdtJob = schedule.scheduleJob('0-59/20 * * * * ?', exchangeTrxToUsdt);
let exchangeUsdtToTrxJob = schedule.scheduleJob('0-59/20 * * * * ?', exchangeUsdtToTrx);

module.exports= {
    queryTrxDetail,
    queryUsdtDetail,
    exchangeTrxToUsdt,
    exchangeUsdtToTrx,
    broadOrder
}