package com.tg.base.tb.tg.service;

import com.tg.base.tb.tg.bot.BuBaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * 用户-机器人实例-群 的飞机业务方法类
 */
public interface TbUserBotinstanceQunManagerTgService {



    /**
     * 机器人进出群时调用
     * @param buBaseBot
     * @param tbBotInstanceId
     * @param update
     * @return
     */
    int botInsInOutQunByUser(BuBaseBot buBaseBot, Integer tbBotInstanceId, Update update) throws TelegramApiException;

    /**
     * 查询我管理的群组
     * @return
     */
//    List<> queryMyAdminQun();
}
