package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.service.TbQunManagerService;
import com.tg.base.tb.tg.service.TbQunManagerTgService;
import com.tg.bot.base.bot.enum1.MessageFromTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TbQunManagerTgServiceImpl implements TbQunManagerTgService {

    @Autowired
    TbQunManagerService tbQunManagerService;


    @Override
    public Boolean saveOrUpdateTgQun(Update update) {

        if(update.getMyChatMember() != null){
            Chat chat = update.getMyChatMember().getChat();
            //添加或更新群
            tbQunManagerService.saveOrUpdateTgQun(
                    chat.getId() + "",
                    chat.getTitle(),
                    MessageFromTypeEnum.hashMap.get(chat.getType()),
                    chat.getUserName()
            );
        }
        return Boolean.TRUE;
    }
}
