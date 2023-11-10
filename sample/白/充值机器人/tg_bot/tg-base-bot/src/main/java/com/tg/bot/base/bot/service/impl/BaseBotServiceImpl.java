package com.tg.bot.base.bot.service.impl;

import com.tg.bot.base.bot.BaseBot;
import com.tg.bot.base.bot.service.BaseBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * 机器人业务类
 */
@Service
@Slf4j
public class BaseBotServiceImpl implements BaseBotService {

    @Override
    public Boolean deleteJqMsg(BaseBot baseBot, Update update) throws TelegramApiException {
        if(update.hasMessage()){

            Message message = update.getMessage();

            Long chatId = message.getChat().getId();
            Integer messageId = message.getMessageId();

            List<User> newChatMembers = message.getNewChatMembers();

            if(!CollectionUtils.isEmpty(newChatMembers)){
                return baseBot.deleteMessageByChatIdAndMsgId(chatId,messageId);
            }

        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean deleteTqMsg(BaseBot baseBot, Update update) throws TelegramApiException {
        if(update.hasMessage()){

            Message message = update.getMessage();

            Long chatId = message.getChat().getId();
            Integer messageId = message.getMessageId();

            User leftChatMember = message.getLeftChatMember();

            if(leftChatMember != null){
                return baseBot.deleteMessageByChatIdAndMsgId(chatId,messageId);
            }
        }
        return Boolean.FALSE;
    }


}
