package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.service.TbUserQunManagerService;
import com.tg.base.tb.tg.service.TbUserQunManagerTgService;
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
public class TbUserQunManagerTgServiceImpl implements TbUserQunManagerTgService {

    @Autowired
    TbUserQunManagerService tbUserQunManagerService;


    @Override
    public int addUserQunRalationByUserInQun(Update update) {

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
                    //添加用户和群关系
                    tbUserQunManagerService.savaOrUpdateUserQunManager(
                            update.getMessage().getChatId() + "",
                            user.getId() + "");
                }

                return newChatMembers.size();
            }
        }

        return 0;
    }

    @Override
    public int delUserQunRalationByUserOutQun(Update update) {
        if(update.hasMessage()
                && (
                MessageFromTypeEnum.SUPERGROUP.getType().equals(update.getMessage().getChat().getType())
                        || MessageFromTypeEnum.GROUP.getType().equals(update.getMessage().getChat().getType()))
        ) {

            User leftChatMember = update.getMessage().getLeftChatMember();

            //5.删除用户
            if (leftChatMember != null) {

                logger.info("有用户离开群......,用户信息:{}", leftChatMember);

                return tbUserQunManagerService.deleteUserQunManager(
                        update.getMessage().getChatId() + "",
                        update.getMessage().getLeftChatMember().getId() + ""
                );
            }
        }

        return 0;
    }
}
