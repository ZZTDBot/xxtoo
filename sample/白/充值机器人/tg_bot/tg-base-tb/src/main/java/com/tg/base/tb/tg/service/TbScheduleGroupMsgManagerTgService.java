package com.tg.base.tb.tg.service;


import com.tg.base.tb.tg.bot.BuBaseBot;
import org.quartz.SchedulerException;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
* @description 创建多调度任务服务类
* @createDate 2023-08-17 17:45:56
*/
public interface TbScheduleGroupMsgManagerTgService {
    /**
     * 私有消息处理
     * @param update
     * @param buBaseBot
     */
   void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException, SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException;
}
