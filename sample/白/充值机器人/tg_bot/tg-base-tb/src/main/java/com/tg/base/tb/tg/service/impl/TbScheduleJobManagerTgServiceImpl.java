package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.service.TbScheduleJobManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbScheduleJobManagerConfig;
import com.tg.base.tb.tg.service.TbScheduleJobManagerTgService;
import com.tg.base.tb.utils.RegxUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;

@Component
@Slf4j
public class TbScheduleJobManagerTgServiceImpl implements TbScheduleJobManagerTgService {

    @Autowired
    TbScheduleJobManagerConfig tbScheduleJobManagerConfig;

    @Autowired
    TbScheduleJobManagerService tbScheduleJobManagerService;
    @Override
    public void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException, SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        logger.info("进入配置调度消息的方法类....");

        if(buBaseBot.isPrivateMsg(update)) {

            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            //有文本消息
            if (StringUtils.hasText(text)) {
                //1.捕获创建周期调度消息任务
                Matcher jobSetTimeRegxMatcher = RegxUtils.matcherRegx(text, tbScheduleJobManagerConfig.getJobSetTimeRegx());
                if(jobSetTimeRegxMatcher.find()){
                    String scheduleMsgId = jobSetTimeRegxMatcher.group(1);
                    String cronNum = jobSetTimeRegxMatcher.group(2);
                    if(Integer.valueOf(cronNum) < tbScheduleJobManagerConfig.getMinJobMinute()){
                        buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"最小间隔时间不能小于"+tbScheduleJobManagerConfig.getMinJobMinute()+"分钟");
                        return;
                    }
                    String cron = "1 0-59/" + cronNum + " * * * ? *";
                    Boolean flag =
                            tbScheduleJobManagerService.saveOrUpdateScheduleJob(update.getMessage().getFrom().getId(),scheduleMsgId,cron);
                    buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"设置定时任务成功!!!");
                }

                //2.捕获创建周期调度消息任务-时间段
                Matcher jobSetHourTimeRegxMatcher = RegxUtils.matcherRegx(text, tbScheduleJobManagerConfig.getJobSetHourTimeRegx());
                if(jobSetHourTimeRegxMatcher.find()){
                    String scheduleMsgId = jobSetHourTimeRegxMatcher.group(1);
                    String minute = jobSetHourTimeRegxMatcher.group(2);
                    String minHour = jobSetHourTimeRegxMatcher.group(3);
                    String maxHour = jobSetHourTimeRegxMatcher.group(4);

                    if(Integer.valueOf(minute) < tbScheduleJobManagerConfig.getMinJobMinute()){
                        buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"最小间隔时间不能小于"+tbScheduleJobManagerConfig.getMinJobMinute()+"分钟");
                        return;
                    }

                    String cron = "1 0-59/" + minute + " "+ minHour + "-" + maxHour +" * * ? *";
                    Boolean flag =
                            tbScheduleJobManagerService.saveOrUpdateScheduleJob(update.getMessage().getFrom().getId(),scheduleMsgId,cron);
                    buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),"设置定时任务成功!!!");
                }
            }

        }


    }
}
