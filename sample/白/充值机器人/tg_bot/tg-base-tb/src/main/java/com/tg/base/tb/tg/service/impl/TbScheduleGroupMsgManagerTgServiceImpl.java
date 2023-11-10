package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.enum1.db.TbScheduleGroupMsgManagerJobStatus;
import com.tg.base.tb.service.TbScheduleGroupMsgManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbScheduleGroupMsgManagerConfig;
import com.tg.base.tb.tg.service.TbScheduleGroupMsgManagerTgService;
import com.tg.base.tb.utils.RegxUtils;
import com.tg.bot.base.bot.enum1.MessageModeEnum;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;

@Service
@Slf4j
public class TbScheduleGroupMsgManagerTgServiceImpl implements TbScheduleGroupMsgManagerTgService {

    @Autowired
    TbScheduleGroupMsgManagerConfig tbScheduleGroupMsgManagerConfig;

    @Autowired
    TbScheduleGroupMsgManagerService tbScheduleGroupMsgManagerService;

    @Override
    public void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException, SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        logger.info("进入配置多个调度消息的方法类....");

        if(buBaseBot.isPrivateMsg(update)) {

            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            //有文本消息
            if (StringUtils.hasText(text)) {
                //1.捕获创建周期调度消息任务
                Matcher msgZqRegxMatcher = RegxUtils.matcherRegx(text, tbScheduleGroupMsgManagerConfig.getMsgZqRegx());
                if(msgZqRegxMatcher.find()){
                    String qunId = msgZqRegxMatcher.group(1);
                    String cronNum = msgZqRegxMatcher.group(2);
                    String msg = msgZqRegxMatcher.group(3);
                    if(Integer.valueOf(cronNum) < tbScheduleGroupMsgManagerConfig.getMinJobMinute()){
                        buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"最小间隔时间不能小于"+tbScheduleGroupMsgManagerConfig.getMinJobMinute()+"分钟");
                        return;
                    }
                    String cron = "1 0-59/" + cronNum + " * * * ? *";
//                    String cron = "0-59/10 * * * * ? *";
                    int addScheduleJob = tbScheduleGroupMsgManagerService.addScheduleJob(
                            update.getMessage().getFrom().getId().toString(),
                            qunId,
                            buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),
                            msg,
                            TbScheduleGroupMsgManagerJobStatus.RUNNING.getId(),
                            cron,
                            cronNum
                    );
                    buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"设置定时任务成功!!!");
                }

                Matcher msgZqRegx2Matcher = RegxUtils.matcherRegx(text, tbScheduleGroupMsgManagerConfig.getMsgZqRegx2());
                if(msgZqRegx2Matcher.find()){
                    String qunId = msgZqRegx2Matcher.group(1);

                    String minute = msgZqRegx2Matcher.group(2);
                    String minHour = msgZqRegx2Matcher.group(3);
                    String maxHour = msgZqRegx2Matcher.group(4);

                    String msg = msgZqRegx2Matcher.group(5);

                    if(Integer.valueOf(minute) < tbScheduleGroupMsgManagerConfig.getMinJobMinute()){
                        buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"最小间隔时间不能小于"+tbScheduleGroupMsgManagerConfig.getMinJobMinute()+"分钟");
                        return;
                    }

                    String cron = "1 0-59/" + minute + " " + minHour + "-" + maxHour + " * * ? *";
//                    String cron = "0-59/10 * * * * ? *";

                    int addScheduleJob = tbScheduleGroupMsgManagerService.addScheduleJob(
                            update.getMessage().getFrom().getId().toString(),
                            qunId,
                            buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),
                            msg,
                            TbScheduleGroupMsgManagerJobStatus.RUNNING.getId(),
                            cron,
                            minute + " " + minHour + "-" + maxHour
                    );
                    buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"设置定时任务成功!!!");
                }

                //2.查看群调度消息列表
                Matcher queryMsgRegxMatcher = RegxUtils.matcherRegx(text, tbScheduleGroupMsgManagerConfig.getQueryMsgRegx());
                if(queryMsgRegxMatcher.find()){
                    String qunId = queryMsgRegxMatcher.group(1);
                    String queryQunScheduleMsgContext = tbScheduleGroupMsgManagerService.queryQunScheduleMsgContext(qunId);
                    buBaseBot.sendMessage(chatId,queryQunScheduleMsgContext, MessageModeEnum.HTML.getType());
                }

                //3.删除群定时消息
                Matcher delMsgRegxMatcher = RegxUtils.matcherRegx(text, tbScheduleGroupMsgManagerConfig.getDelMsgRegx());
                if(delMsgRegxMatcher.find()){
                    String msgId = delMsgRegxMatcher.group(1);
                    int delQunScheduleMsg = tbScheduleGroupMsgManagerService.delQunScheduleMsg(Integer.valueOf(msgId));

                    buBaseBot.sendMessage(chatId,"删除定时消息成功!!!", update.getMessage().getMessageId());
                }

            }

        }
    }
}
