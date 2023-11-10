package com.tg.base.tb.tg.service;


import com.tg.base.tb.tg.bot.BuBaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
* @description 针对表【tb_pri_keyword_reply_manager(私聊关键词回复表)】的数据库操作Service
* @createDate 2023-08-17 17:45:56
*/
public interface TbPriKeywordReplyManagerTgService {
    /**
     * 私有消息处理
     * @param update
     * @param buBaseBot
     */
   void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException;
}
