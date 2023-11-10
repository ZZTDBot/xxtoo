package com.tg.base.tb.tg.service;


import com.tg.base.tb.tg.bot.BuBaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
* @description 调度消息服务类
* @createDate 2023-08-17 17:45:56
*/
public interface TbScheduleMsgManagerTgService {
    /**
     * 私有消息处理
     * @param update
     * @param buBaseBot
     */
   void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException;
}
