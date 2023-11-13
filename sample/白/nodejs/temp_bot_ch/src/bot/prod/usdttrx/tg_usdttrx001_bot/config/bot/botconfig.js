//usdt转trx的费率
const usdtToTrxFl = 0.1
//trx转usdt的费率
const trxToUsdtFl = 0.1
//trx转usdt的固定矿工费
const trxToUsdtGDFl = 20;
//trx转usdt的起兑线
const trxToUsdtQdx = 100;
//usdt转trx的费率
const usdtToTrxQdx=1;
//订单广播群ID
let orderGbQunId=-915312952;

//一个单位时间最多支持的兑换订单的数量
const tronOrderMinuteCount = 100;
//查询波场最近几分钟的数据
const tronOrderMinute = 10;
// const tronOrderMinute = 1440 * 9;
//对波场最近几分钟的数据进行结算
const tronOrderLastMinutes = 15;
//对最近N分钟未广播的订单进行广播
const lastMinutesNotGbOrder = 30;

//收笔地址
const reciveAdrress = 'TCh1G6aSTzYBMZGE1PxY8YoDfvxvoz8RnL';
//设置私钥地址
const privateKey = "FF107E4371FA0F378EC4F2F57CEE9E8A1D79DB3A50726AC56519FFE7D294EBF4";
//trc20合约地址
const trc20ContractAddress = "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t";

//命令列表
const cmds = [
    {
        "command" : '/start',
        "description" : "光速兑换"
    },
    {
        "command" : '/session_id',
        "description" : "查看当前会话ID"
    }
]

// 底部按钮的名称
const dowKeyButtonSsUPrice = '⚡️实时U价'
const dowKeyButtonSsHl = '📈实时汇率'
const dowKeyButtonDhAddress = '♻️兑换地址'
//设置机器人底部键盘按钮
const downKeyButton = {
    "reply_markup":{
        "keyboard":[
            [
                {
                    "text":dowKeyButtonSsUPrice
                },
                {
                    "text":dowKeyButtonSsHl
                }
            ],
            [
                {
                    "text":dowKeyButtonDhAddress
                }
            ]
        ],
        "resize_keyboard":true
    }
}
const selected = "✅"
//设置查U内联按钮
const buttonAllPay = {
    text: selected + "所有",
    callback_data:'all'
}
const buttonAliPay = {
    text:"支付宝",
    callback_data:'aliPay'
}
const buttonWxPay = {
    text:"微信",
    callback_data:'wxPay'
}
const buttonBank = {
    text:"银行卡",
    callback_data:'bank'
}
// 查U的按钮选项
const queryUsdtReplyMarkupInlineButton = {
    inline_keyboard:[
        [
            buttonAllPay,
            buttonAliPay,
            buttonWxPay,
            buttonBank
        ]
    ]
}
// 查U的按钮选项
const queryUsdtRows = 10

module.exports={
    cmds,
    downKeyButton,
    queryUsdtReplyMarkupInlineButton,
    selected,
    buttonAllPay,
    buttonAliPay,
    buttonWxPay,
    buttonBank,
    queryUsdtRows,
    usdtToTrxFl,
    trxToUsdtFl,
    trxToUsdtGDFl ,
    trxToUsdtQdx,
    usdtToTrxQdx,
    reciveAdrress,
    tronOrderMinuteCount,
    tronOrderMinute,
    dowKeyButtonSsUPrice,
    dowKeyButtonSsHl,
    dowKeyButtonDhAddress,
    tronOrderLastMinutes,
    privateKey,
    trc20ContractAddress,
    orderGbQunId,
    lastMinutesNotGbOrder
}

