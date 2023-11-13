//系统配置对象
const botconfig = require('../config/bot/botconfig');
//操作入账记录表的对象
const tbTrxUsdtOrderDetailDao = require('../dao/tbTrxUsdtOrderDetailDao');
//操作白名单表的对象
const tbTrxBaimingdanDao = require('../dao/tbTrxBaimingdanDao.js');

//引入波场的api
const baseTronApi = require('../../../../common/base-tron-api/base-tron-api');

//引入操作波场账户的对象
const tronTrxAndUsdtOrderDao = require('../dao/TronTrxAndUsdtOrderDao');

//日期操作类
const moment = require('moment');
const yyyymmddhhMmSs = 'YYYY-MM-DD HH:mm:ss';







/**
 * 插入波场的Trx交易数据到数据库
 * @param tronScanTrxEntity
 */
function insertTrxOrderDetail(tronScanTrxEntity) {

    if( tronScanTrxEntity && tronScanTrxEntity.data){

        //查询白名单地址
        const baiMingDanAddresssPromise = tbTrxBaimingdanDao.selectAllAddress();
        baiMingDanAddresssPromise.then((res,err) => {
            if (err){
                console.error(`白名单列表查询失败  err:${err}`)
                return;
            }

            tronScanTrxEntity.data.forEach(tronTrxOrder => {
                if(tronTrxOrder.toAddress == botconfig.reciveAdrress && !res.includes(tronTrxOrder.ownerAddress)){

                    tbTrxUsdtOrderDetailDao.selectByFromHashCode(tronTrxOrder.hash)
                        .then((selectByFromHashCode,selectByFromHashCodeErr) =>{
                        if(selectByFromHashCodeErr){
                            console.error(`通过转入hashCode查询数据库记录失败  selectByFromHashCodeErr:${selectByFromHashCodeErr}`)
                            return;
                        }

                        if(selectByFromHashCode && selectByFromHashCode.length > 0 ){
                            console.log(`hashCode为${tronTrxOrder.hash}的数据已经存在于数据库中........`)
                            return;
                        }

                        tbTrxUsdtOrderDetailDao.insertFromRow(
                            tronTrxOrder.hash,
                            tronTrxOrder.block,
                            new Date(tronTrxOrder.timestamp),
                            tronTrxOrder.confirmed,
                            tronTrxOrder.ownerAddress,
                            tronTrxOrder.toAddress,
                            1,
                            tronTrxOrder.amount/1000000
                        ).then((insertRes,insertErr) => {
                            if(insertErr){
                                console.error(`插入trx交易记录失败  insertErr:${insertErr}`)
                                return;
                            }
                            console.log(`插入trx交易记录成功  insertRes:${insertRes}`)
                        })

                    })

                }else{
                    console.log(`这是一条TRX的转出记录 tronTrxOrder:${JSON.stringify(tronTrxOrder)}` );
                }
            })

        })
    }
}

/**
 * 插入波场的Usdt交易数据到数据库
 * @param tronScanUsdtEntity
 */
function insertUsdtOrderDetail(tronScanUsdtEntity) {
    if( tronScanUsdtEntity && tronScanUsdtEntity.token_transfers ){

        //查询白名单地址
        const baiMingDanAddresssPromise = tbTrxBaimingdanDao.selectAllAddress();
        baiMingDanAddresssPromise.then((res,err) => {
            if (err){
                console.log(`白名单列表查询失败  err:${err}`)
                return;
            }

            tronScanUsdtEntity.token_transfers.forEach(tronUsdtOrder => {
                if(tronUsdtOrder.to_address == botconfig.reciveAdrress && !res.includes(tronUsdtOrder.from_address)){
                    tbTrxUsdtOrderDetailDao.selectByFromHashCode(tronUsdtOrder.transaction_id)
                        .then((selectByFromHashCode,selectByFromHashCodeErr) =>{
                            if(selectByFromHashCodeErr){
                                console.log(`通过转入hashCode查询数据库记录失败  selectByFromHashCodeErr:${selectByFromHashCodeErr}`)
                                return;
                            }

                            if(selectByFromHashCode && selectByFromHashCode.length > 0 ){
                                console.log(`hashCode为${tronUsdtOrder.transaction_id}的数据已经存在于数据库中........`)
                                return;
                            }

                            tbTrxUsdtOrderDetailDao.insertFromRow(
                                tronUsdtOrder.transaction_id,
                                tronUsdtOrder.block,
                                new Date(tronUsdtOrder.block_ts),
                                tronUsdtOrder.confirmed,
                                tronUsdtOrder.from_address,
                                tronUsdtOrder.to_address,
                                2,
                                tronUsdtOrder.quant/1000000
                            ).then((insertRes,insertErr) => {
                                if(insertErr){
                                    console.log(`插入usdt交易记录失败  insertErr:${insertErr}`)
                                    return;
                                }
                                console.log(`插入usdt交易记录成功  insertRes:${insertRes}`)
                            })
                        })
                }else{
                    console.log(`这是一条USDT的转出记录 tronUsdtOrder:${tronUsdtOrder}` );
                }
            })
        })
    }
}

/**
 * trx兑换usdt方法启动
 */
function exchangeTrxToUsdt() {
    //查找未兑换的记录
    tbTrxUsdtOrderDetailDao.selectNotFinishedOrder(
        1,
        botconfig.tronOrderLastMinutes)
        .then((res,err) => {
            if(err){
                console.error(`查询波场trx的转账记录失败 err:${err}`)
                return;
            }

            res.forEach(tbTrxUsdtOrderDetail =>{
                //Trx的交易
                //判断进账trx是否达到起兑线线
                if(tbTrxUsdtOrderDetail.from_amount >= botconfig.trxToUsdtQdx){
                    //折后金额
                    const fromAmountZh = (tbTrxUsdtOrderDetail.from_amount - botconfig.trxToUsdtGDFl) * (1 - botconfig.trxToUsdtFl);

                    //获取汇率
                    baseTronApi
                        .getTronTokenPrice(baseTronApi.TOKEN_TYPE_TRX)
                        .then((tronTrxRes,tronTrxErr) => {
                            if(tronTrxErr){
                                console.error(`请求实时汇率失败...tronTrxErr:${tronTrxErr}`)
                                return;
                            }

                            //准备转出的金额
                            const outUsdt = (fromAmountZh * tronTrxRes.price_in_usd).toFixed(6)
                            console.log(`收到${tbTrxUsdtOrderDetail.from_out_address}账户转入到${tbTrxUsdtOrderDetail.from_in_address}
                            这个账户的${tbTrxUsdtOrderDetail.from_amount}个trx,扣除固定trx${botconfig.trxToUsdtGDFl}个，
                            当前trx转usdt的费率是${botconfig.trxToUsdtFl}，折后应转出${outUsdt}个USDT`);

                            if( outUsdt > 0 ){
                                //转账
                                tronTrxAndUsdtOrderDao.triggerSmartContractTransfer(
                                    botconfig.trc20ContractAddress,
                                    tbTrxUsdtOrderDetail.from_out_address,
                                    outUsdt)
                                    .then((triggerSmartContractTransferRes,triggerSmartContractTransferErr) => {

                                    if(triggerSmartContractTransferErr){
                                        //转账失败
                                        console.error("转出usdt失败......");
                                        return;
                                    }

                                    tbTrxUsdtOrderDetailDao.updateJieSuanByFromHash(
                                        tbTrxUsdtOrderDetail.from_hash_code,
                                        triggerSmartContractTransferRes,
                                        outUsdt,
                                        2,
                                        tronTrxAndUsdtOrderDao.fromPrivateKeyAddress,
                                        tbTrxUsdtOrderDetail.from_in_address,
                                        new Date()
                                    ).then((updateJieSuanByFromHashRes,updateJieSuanByFromHashErr) => {
                                        if(updateJieSuanByFromHashErr){
                                            //转账失败
                                            console.error(`更新结算信息失败 ..... from_hash_code: ${tbTrxUsdtOrderDetail.from_hash_code}`);
                                            return
                                        }
                                        console.log(`更新结算信息成功 ..... from_hash_code: ${tbTrxUsdtOrderDetail.from_hash_code}`);
                                    })
                                })
                            }else {
                                console.log(`准备转出的usdt数量为outUsdt:${outUsdt},小于零，不交易!!!`);
                            }
                        })
                }else {
                    console.log(`当前收到trx的数量为${tbTrxUsdtOrderDetail.from_amount}个，未达到起兑线${botconfig.trxToUsdtQdx}个trx`);
                }
            })
        })
}

/**
 * usdt兑换trx方法启动
 */
function exchangeUsdtToTrx(){
    //查找未兑换的记录
    tbTrxUsdtOrderDetailDao.selectNotFinishedOrder(
        2,
        botconfig.tronOrderLastMinutes)
        .then((res,err) => {
            if(err){
                console.error(`查询波场usdt的转账记录失败 err:${err}`)
                return;
            }

            res.forEach(tbTrxUsdtOrderDetail =>{
                //Usdt的交易
                //判断进账Usdt是否达到起兑线线
                if(tbTrxUsdtOrderDetail.from_amount >= botconfig.usdtToTrxQdx){

                    //折后金额
                    const fromAmountZh = (tbTrxUsdtOrderDetail.from_amount) * (1 - botconfig.usdtToTrxFl);

                    //获取汇率
                    baseTronApi
                        .getTronTokenPrice(baseTronApi.TOKEN_TYPE_TRX)
                        .then((tronTrxRes,tronTrxErr) => {
                            if(tronTrxErr){
                                console.error(`请求实时汇率失败...tronTrxErr:${tronTrxErr}`)
                                return;
                            }

                            //准备转出的金额
                            const outTrx = (fromAmountZh / tronTrxRes.price_in_usd).toFixed(6)
                            console.log(`收到${tbTrxUsdtOrderDetail.from_out_address}账户转入到${tbTrxUsdtOrderDetail.from_in_address}
                            这个账户的${tbTrxUsdtOrderDetail.from_amount}个usdt,
                            当前usdt转trx的费率是${botconfig.usdtToTrxFl}，折后应转出${outTrx}个trx`);

                            if( outTrx > 0 ){
                                //转账
                                tronTrxAndUsdtOrderDao.sendTrxTransaction(
                                    tbTrxUsdtOrderDetail.from_out_address,
                                    outTrx,
                                    botconfig.privateKey
                                    ).then((sendTrxTransactionRes,sendTrxTransactionErr) => {
                                        if(sendTrxTransactionErr){
                                            //转账失败
                                            console.error("转出trx失败......");
                                            return;
                                        }
                                        tbTrxUsdtOrderDetailDao.updateJieSuanByFromHash(
                                            tbTrxUsdtOrderDetail.from_hash_code,
                                            sendTrxTransactionRes.txid,
                                            outTrx,
                                            1,
                                            tronTrxAndUsdtOrderDao.fromPrivateKeyAddress,
                                            tbTrxUsdtOrderDetail.from_in_address,
                                            new Date()
                                        ).then((updateJieSuanByFromHashRes,updateJieSuanByFromHashErr) => {
                                            if(updateJieSuanByFromHashErr){
                                                //转账失败
                                                console.error(`更新结算信息失败 ..... from_hash_code: ${tbTrxUsdtOrderDetail.from_hash_code}`);
                                                return
                                            }
                                            console.log(`更新结算信息成功 ..... from_hash_code: ${tbTrxUsdtOrderDetail.from_hash_code}`);
                                        })

                                    })
                            }else {
                                console.log(`准备转出的trx数量为outTrx:${outTrx},小于零，不交易!!!`);
                            }
                        })
                }else {
                    console.log(`当前收到Usdt的数量为${tbTrxUsdtOrderDetail.from_amount}个，未达到起兑线${botconfig.usdtToTrxQdx}个usdt`);
                }
            })
        })
}

/**
 * 广播交易信息
 */
function broadOrder(trxSimpleBot) {
    if(botconfig.orderGbQunId){

        tbTrxUsdtOrderDetailDao.selectNotBroadOrder(botconfig.lastMinutesNotGbOrder)
            .then((selectNotBroadOrderRes,selectNotBroadOrderErr) => {
                if(selectNotBroadOrderErr){
                    console.error(`查询未广播订单数据失败....`,selectNotBroadOrderErr)
                    return;
                }

                selectNotBroadOrderRes.forEach(tbTrxUsdtOrderDetail => {

                    // 新交易！ 下发 -121.89TRX
                    // 兑换金额：10.00USDT=121.89TRX
                    // 交易时间：2023-05-05 10:46:23
                    // 详细过程：TGxFrhHfwZre7YiW3T7AkojBA4V3fFHHHH 向 TXMgr93UJoh1nQyF89ucbELK4GLWaHJpy5 转账121.89 TRX

                    let gbMessage;

                    if(tbTrxUsdtOrderDetail.to_coin_type == 1){
                        //转出TRX交易
                        gbMessage = `新交易！ 下发 -${tbTrxUsdtOrderDetail.to_amount}TRX\n兑换金额：${tbTrxUsdtOrderDetail.from_amount}USDT=${tbTrxUsdtOrderDetail.to_amount}TRX\n交易时间：${moment(tbTrxUsdtOrderDetail.to_timestamp).format(yyyymmddhhMmSs)}\n详细过程：<code>${tbTrxUsdtOrderDetail.to_out_address}</code> 向 <code>${tbTrxUsdtOrderDetail.to_in_address}</code> 转账${tbTrxUsdtOrderDetail.to_amount} TRX`

                    }
                    if(tbTrxUsdtOrderDetail.to_coin_type == 2){
                        //转出USDT交易
                        gbMessage = `新交易！ 下发 -${tbTrxUsdtOrderDetail.to_amount}USDT\n兑换金额：${tbTrxUsdtOrderDetail.from_amount}TRX=${tbTrxUsdtOrderDetail.to_amount}USDT\n交易时间：${moment(tbTrxUsdtOrderDetail.to_timestamp).format(yyyymmddhhMmSs)}\n详细过程：<code>${tbTrxUsdtOrderDetail.to_out_address}</code> 向 <code>${tbTrxUsdtOrderDetail.to_in_address}</code> 转账${tbTrxUsdtOrderDetail.to_amount} USDT`
                    }

                    if(gbMessage){

                        console.log(`准备广播的交易信息: ${gbMessage}`);
                        trxSimpleBot.sendMessage(botconfig.orderGbQunId,gbMessage,{parse_mode:"HTML"});
                        const orderGbTime = new Date();
                        tbTrxUsdtOrderDetailDao.updateOrderGbInfo(
                            tbTrxUsdtOrderDetail.from_hash_code,
                            orderGbTime,
                            botconfig.orderGbQunId
                            ).then((updateOrderGbInfoRes,updateOrderGbInfoErr) => {
                            if(updateOrderGbInfoErr){
                                console.error(`更新广播消息失败.....`,selectNotBroadOrderErr)
                                return;
                            }
                            console.log(`更新广播信息成功,from_hash_code:${tbTrxUsdtOrderDetail.from_hash_code}  orderGbQunId:${botconfig.orderGbQunId}   orderGbTime:${orderGbTime}`)
                        })

                    }
                })
            })
    }
}


module.exports ={
    insertTrxOrderDetail,
    insertUsdtOrderDetail,
    exchangeTrxToUsdt,
    exchangeUsdtToTrx,
    broadOrder
}