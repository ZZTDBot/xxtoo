package com.tg.base.tb.tg.service;

import com.tg.base.tb.tg.bot.BuBaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * 机器人管理的飞机层
 */
public interface TbBotInstanceManagerTgService {
    /**
     * 添加飞机机器人
     * @param update
     */
    public void addBot(Update update, BuBaseBot buBaseBot) throws TelegramApiException;

    /**
     * 通过BotFather给的API创建机器人
     * @param update
     */
    public void addBotByToken(Update update, BuBaseBot buBaseBot) throws TelegramApiException, ClassNotFoundException;

    /**
     * 机器人管理模块的私聊处理
     * @param buBaseBot
     * @param update
     */
    public void proccessPrivate(BuBaseBot buBaseBot, Update update) throws TelegramApiException, ClassNotFoundException;
}
