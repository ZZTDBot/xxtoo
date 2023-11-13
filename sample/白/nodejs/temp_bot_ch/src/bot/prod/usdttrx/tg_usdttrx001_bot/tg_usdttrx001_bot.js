//引入飞机相关包组价
const TelegramBot = require('node-telegram-bot-api');
//引入调度框架
const schedule = require('node-schedule');

//引入欧易的操作API
const baseOuyiApi = require('../../../common/base-ouyi-api');
//引入波场的api
const baseTronApi = require('../../../common/base-tron-api');
//引入sql配置文件
const botconfig = require('./config/bot/botconfig');

//业务任务方法
const taskImpl = require('./schedule/TaskImpl');

//生产token
// const token = 'xxx:xxx-xxx';
// 测试token
const token = 'xxx:xxx';//xxx


// 创建一个机器人实例
const bot = new TelegramBot(token, {polling: true});

//设置命令列表
bot.setMyCommands(
    botconfig.cmds
)

//转发消息处理逻辑
bot.on('message', (msg) => {
    console.info(new Date() + " msg:" + JSON.stringify(msg))
})

//查询会话命令
bot.onText(/^\/session_id/,(msg, match) => {
    bot.sendMessage(msg.chat.id,msg.chat.id)
})

//start命令
bot.onText(/^\/start/,(msg, match) => {
    if(msg.chat.type == 'private'){
        // bot.sendMessage(msg.chat.id,"开始",botconfig.downKeyButton)
        ssHlReply(msg)
    }
})

//实时U价
bot.onText(/^⚡️实时U价/,(msg, match) => {
    if(msg.chat.type == 'private'){
        baseOuyiApi.queryZfbUsdtReltimePriceProccess(baseOuyiApi.ALL_PAY_TYPE,botconfig.queryUsdtRows).then((res,err) => {
            const replayContext = baseOuyiApi.getReplayContext(res,baseOuyiApi.ALL_PAY_TYPE);
            bot.sendMessage(msg.chat.id,replayContext, {
                parse_mode:"HTML",
                reply_markup: botconfig.queryUsdtReplyMarkupInlineButton
            })
        })
    }
})



//实时汇率
bot.onText(/^📈实时汇率/,(msg, match) => {
    if(msg.chat.type == 'private'){
        ssHlReply(msg);
    }
})

//兑换地址
bot.onText(/^♻️兑换地址/,(msg, match) => {
    if(msg.chat.type == 'private'){
        const msgReply =
            '自动兑换地址：\n' +
            '<code>' + botconfig.reciveAdrress + '</code> \n(点击地址自动复制)️'
        bot.sendMessage(msg.chat.id,msgReply, {parse_mode:"html"});
    }
})

// 内联按钮回调处理方法
bot.on('callback_query', (msg) => {
    console.info(new Date() + " msg:" + JSON.stringify(msg))
    if (msg.data && (
        msg.data == botconfig.buttonAllPay.callback_data
        || msg.data == botconfig.buttonAliPay.callback_data
        || msg.data == botconfig.buttonWxPay.callback_data
        || msg.data == botconfig.buttonBank.callback_data)){

        baseOuyiApi.queryZfbUsdtReltimePriceProccess(msg.data,botconfig.queryUsdtRows).then((res,err) => {
            const replayContext = baseOuyiApi.getReplayContext(res,msg.data);
            //去除之前打勾的选项
            const map = botconfig.queryUsdtReplyMarkupInlineButton.inline_keyboard[0].map(x => {
                x.text = x.text.replaceAll(botconfig.selected,'')
                if(msg.data == x.callback_data){
                    x.text = botconfig.selected + x.text
                }
                return x;
            });

            bot.sendMessage(msg.from.id,replayContext, {
                parse_mode:"HTML",
                reply_markup: {
                    inline_keyboard : [
                        map
                    ]
                }
            })

        })


    }
})

// 启动兑币调度方法
const broadOrderJob = schedule.scheduleJob('0-59/20 * * * * ?', function(){
    taskImpl.broadOrder(bot)
});



/**
 * 试试汇率的回复
 * @param msg
 */
function ssHlReply(msg) {
    baseTronApi
        .getTronTokenPrice(baseTronApi.TOKEN_TYPE_TRX)
        .then((res, err) => {
            if (err) {
                console.log('请求实时汇率失败...')
            }
            let price_in_usd = res.price_in_usd;

            let usdtToTrxHl = (100 / price_in_usd) * (1 - botconfig.usdtToTrxFl);
            let trxToUsdtHl = (100 - botconfig.trxToUsdtGDFl) * (1 - botconfig.trxToUsdtFl) * price_in_usd;

            const usdtToTrxHlStr = new Number(usdtToTrxHl).toFixed(2);
            const trxToUsdtHlStr = new Number(trxToUsdtHl).toFixed(2);

            const msgReply = '实时汇率：\n' +
                '100 USDT = ' + usdtToTrxHlStr + ' TRX (' + botconfig.usdtToTrxQdx + 'U 起兑)  \n' +
                '100 TRX = ' + trxToUsdtHlStr + ' USDT (' + botconfig.trxToUsdtQdx + 'TRX 起兑) \n' +
                '(TRX兑换USDT每笔需收20TRX矿工费) \n' +

                '自动兑换地址：\n' +
                '<code>' + botconfig.reciveAdrress + '</code> (点击地址自动复制)️'

            bot.sendMessage(msg.chat.id, msgReply, {parse_mode: "html",reply_markup:botconfig.downKeyButton.reply_markup});
        })
}