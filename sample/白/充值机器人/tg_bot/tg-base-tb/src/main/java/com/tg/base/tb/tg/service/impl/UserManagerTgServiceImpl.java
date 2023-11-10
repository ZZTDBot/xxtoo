package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.service.TbUserManagerService;
import com.tg.base.tb.tg.service.UserManagerTgService;
import com.tg.bot.base.bot.enum1.MessageFromTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Service
@Slf4j
public class UserManagerTgServiceImpl implements UserManagerTgService {

    @Autowired
    TbUserManagerService tbUserManagerService;

    @Override
    public Boolean addTgUserToDbBySendMesToBot(Update update) {

        if(
                update.hasMessage()
//                        && (MessageFromTypeEnum.SUPERGROUP.getType().equals(update.getMessage().getChat().getType())
//                || MessageFromTypeEnum.GROUP.getType().equals(update.getMessage().getChat().getType()))
        ) {
            tbUserManagerService.saveOrUpdateTgUser(
                    update.getMessage().getFrom().getId() + "",
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getLastName(),
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getFrom().getIsBot() ? 1 : 0,
                    update.getMessage().getFrom().getLanguageCode()
            );
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean addTgUserToDbByUserInQun(Update update) {


        if(update.hasMessage()
                && (
                MessageFromTypeEnum.SUPERGROUP.getType().equals(update.getMessage().getChat().getType())
                        || MessageFromTypeEnum.GROUP.getType().equals(update.getMessage().getChat().getType()))
        ) {

            List<User> newChatMembers = update.getMessage().getNewChatMembers();

            //4.添加新用户
            if (!CollectionUtils.isEmpty(newChatMembers) && newChatMembers.size() > 0 ) {
                logger.info("有新用户进入群......,用户列表:{}", newChatMembers);
                for (User user : newChatMembers) {

                    //保存用户
                    tbUserManagerService.saveOrUpdateTgUser(
                            user.getId() + "",
                            user.getFirstName(),
                            user.getLastName(),
                            user.getUserName(),
                            user.getIsBot() ? 1 : 0,
                            user.getLanguageCode()
                    );

                }
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean privateAddUser(Update update) {
        if(update.hasMessage()
                && MessageFromTypeEnum.PRIVATE.getType().equals(update.getMessage().getChat().getType())) {

            User user = update.getMessage().getFrom();
            //保存用户
            return tbUserManagerService.saveOrUpdateTgUser(
                            user.getId() + "",
                            user.getFirstName(),
                            user.getLastName(),
                            user.getUserName(),
                            user.getIsBot() ? 1 : 0,
                            user.getLanguageCode()
                    );

        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean qunComEventAddUser(Update update) {
        if(update.hasMessage()
                && (
                MessageFromTypeEnum.SUPERGROUP.getType().equals(update.getMessage().getChat().getType())
                        || MessageFromTypeEnum.GROUP.getType().equals(update.getMessage().getChat().getType()))
        ) {

            List<User> newChatMembers = update.getMessage().getNewChatMembers();

            //4.添加新用户
            if (!CollectionUtils.isEmpty(newChatMembers) && newChatMembers.size() > 0 ) {
                logger.info("有新用户进入群......,用户列表:{}", newChatMembers);
                for (User user : newChatMembers) {

                    //保存用户
                    tbUserManagerService.saveOrUpdateTgUser(
                            user.getId() + "",
                            user.getFirstName(),
                            user.getLastName(),
                            user.getUserName(),
                            user.getIsBot() ? 1 : 0,
                            user.getLanguageCode()
                    );

                }
            }
        }
        return Boolean.TRUE;
    }


}
