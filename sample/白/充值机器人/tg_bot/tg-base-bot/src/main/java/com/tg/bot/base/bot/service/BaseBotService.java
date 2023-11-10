package com.tg.bot.base.bot.service;

import com.tg.bot.base.bot.BaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


/**
 * 机器人业务类
 */
public interface BaseBotService {

    /**
     * 删除进群消息
     * @param update
     * @return
     */
    public Boolean deleteJqMsg(BaseBot baseBot, Update update) throws TelegramApiException;

    /**
     * 删除退群消息
     * @param update
     * @return
     */
    public Boolean deleteTqMsg(BaseBot baseBot,Update update) throws TelegramApiException;





}
