package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.enum1.db.TbUserBotinstanceQunManagerBotRole;
import com.tg.base.tb.service.TbUserBotinstanceQunManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.service.TbUserBotinstanceQunManagerTgService;
import com.tg.bot.base.bot.bean.MyChatMemberPermissions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
public class TbUserBotinstanceQunManagerTgServiceImpl implements TbUserBotinstanceQunManagerTgService {

    @Autowired
    TbUserBotinstanceQunManagerService tbUserBotinstanceQunManagerService;

    @Override
    public int botInsInOutQunByUser(BuBaseBot buBaseBot, Integer tbBotInstanceId, Update update) throws TelegramApiException {


            ChatMemberUpdated myChatMember = update.getMyChatMember();

            if(myChatMember != null ){

                String status = myChatMember.getNewChatMember().getStatus();

                //保存用户
                User user = myChatMember.getFrom();
                Long userId = user.getId();

                //获取目标聊天会话对象
                Chat chat = myChatMember.getChat();
                Long chatId = chat.getId();

                if(TbUserBotinstanceQunManagerBotRole.member.getMsg().equals(status) || TbUserBotinstanceQunManagerBotRole.administrator.getMsg().equals(status)){
                    logger.info("正在添加机器人到聊天群,机器人ID：{},群ID：{},用户ID：{},进群权限status:{}", tbBotInstanceId,chatId,userId,status);
                    ChatMember chatMember = buBaseBot.getChatMember(chatId, myChatMember.getNewChatMember().getUser().getId());

                    MyChatMemberPermissions myChatMemberPermissions = new MyChatMemberPermissions();
                    BeanUtils.copyProperties(chatMember,myChatMemberPermissions);

                    if (TbUserBotinstanceQunManagerBotRole.member.getMsg().equals(chatMember.getStatus())) {

                        return tbUserBotinstanceQunManagerService.saveOrUpdateByUserQunBotInsId(
                                userId,
                                chatId,
                                tbBotInstanceId,
                                TbUserBotinstanceQunManagerBotRole.member.getId(),

                                myChatMemberPermissions.getCanManageChat(),
                                myChatMemberPermissions.getIsAnonymous(),
                                myChatMemberPermissions.getCanPromoteMembers(),
                                myChatMemberPermissions.getCanDeleteMessages(),
                                myChatMemberPermissions.getCanRestrictMembers(),
                                myChatMemberPermissions.getCanChangeInfo(),
                                myChatMemberPermissions.getCanInviteUsers(),
                                myChatMemberPermissions.getCanPinMessages(),
                                myChatMemberPermissions.getCanManageVideoChats()
                        );

                    } else if (TbUserBotinstanceQunManagerBotRole.administrator.getMsg().equals(chatMember.getStatus())) {

                        return tbUserBotinstanceQunManagerService.saveOrUpdateByUserQunBotInsId(
                                userId,
                                chatId,
                                tbBotInstanceId,
                                TbUserBotinstanceQunManagerBotRole.administrator.getId(),

                                myChatMemberPermissions.getCanManageChat(),

                                myChatMemberPermissions.getIsAnonymous(),
                                myChatMemberPermissions.getCanPromoteMembers(),
                                myChatMemberPermissions.getCanDeleteMessages(),
                                myChatMemberPermissions.getCanRestrictMembers(),
                                myChatMemberPermissions.getCanChangeInfo(),
                                myChatMemberPermissions.getCanInviteUsers(),
                                myChatMemberPermissions.getCanPinMessages(),
                                myChatMemberPermissions.getCanManageVideoChats()
                        );
                    }

                }else if (TbUserBotinstanceQunManagerBotRole.LEFT.getMsg().equals(status)) {
                    return tbUserBotinstanceQunManagerService.deleteByUserQunBotInsId(chatId,tbBotInstanceId);
                }

            }
        return 0;
    }

}
