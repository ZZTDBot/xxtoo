const tbScheduleRangeInterverJobService = require('../service/TbScheduleRangeInterverJobService');
const TbChatWelContextSqlConfig = require("../../../prod/qg/xbqg008_bot/config/sql/TbChatWelContextSqlConfig");

class TbScheduleRangeInterverJobTgBot {

    constructor(dbPool,telegramBot) {
        this.tbScheduleRangeInterverJobServiceImpl = new tbScheduleRangeInterverJobService.TbScheduleRangeInterverJobService(dbPool);
        this.bot = telegramBot;
        this.init();
    }

    init(){
        console.log('定时任务的构造方法执行了......')

        this.bot.on('message', (msg) => {
            console.info(new Date() + " msg:" + JSON.stringify(msg))
        })

        //1.添加任务
        this.bot.onText(/^会话ID:((\-|[0-9])+)\n任务名称:((.|\r\n|\n|\r)+)\n开始时间:([0-9]{4}\-[0-9]{2}\-[0-9]{2}\ [0-9]{2}\:[0-9]{2}\:[0-9]{2})\n结束时间:([0-9]{4}\-[0-9]{2}\-[0-9]{2}\ [0-9]{2}\:[0-9]{2}\:[0-9]{2})\n周期类型:(秒|分|时)\n周期数值:([0-9]{1,2})\n调度内容:((.|\r\n|\n|\r)+)/
            ,(msg, match) => {

            //1.处理群消息
            if(msg.chat.type === 'supergroup' || msg.chat.type === 'group' || msg.chat.type === 'private'){
                console.log(JSON.stringify(match))

                const targetChatId = match[1];
                const tgUserId = msg.from.id;
                const jobName = match[3];
                const startTime = match[5];
                const endTime = match[6];
                const scheduleType = match[7];
                const scheduleValue = match[8];
                const context = match[9];
                this.tbScheduleRangeInterverJobServiceImpl.insertJob(
                    targetChatId,
                    tgUserId,
                    jobName,
                    startTime,
                    endTime,
                    scheduleType,
                    scheduleValue,
                    context
                    ).then((insertJobRes,insertJobErr) => {

                        if(insertJobErr){
                            console.error('数据库异常!!!')
                            return
                        }

                    if(insertJobRes && insertJobRes.affectedRows > 0){
                        this.bot.sendMessage(msg.chat.id,`任务设置成功,请用任务后才会生效.`)
                    }

                })

            }
        })

        //2.查询所有任务
        this.bot.onText(/^查询所有任务/,(msg, match) => {

            this.tbScheduleRangeInterverJobServiceImpl.selectListReplyContext().then((selectListReplyContextRes,selectListReplyContextErr) => {
                if(selectListReplyContextErr){
                    console.error('数据库异常!!!')
                    return
                }
                this.bot.sendMessage(msg.chat.id,selectListReplyContextRes)
            })
        })


    }
}

module.exports = {
    TbScheduleRangeInterverJobTgBot
}