package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbUserBotinstanceQunManager;
import com.tg.base.tb.mapper.TbUserBotinstanceQunManagerMapper;
import com.tg.base.tb.service.TbUserBotinstanceQunManagerService;
import com.tg.base.tb.service.dto.UserBotInsQunDto;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.session.TgBotRunSession;
import com.tg.bot.base.bot.enum1.TgQunRoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @description 针对表【tb_user_botinstance_qun_manager(用户_机器人实例_群_关系管理)】的数据库操作Service实现
* @createDate 2022-12-24 14:35:42
*/
@Service
@Slf4j
public class TbUserBotinstanceQunManagerServiceImpl extends ServiceImpl<TbUserBotinstanceQunManagerMapper, TbUserBotinstanceQunManager>
    implements TbUserBotinstanceQunManagerService {

    @Override
    public int saveOrUpdateByUserQunBotInsId(
            Long tgUserId,
            Long qunId,
            Integer tbBotInstanceId,
            Integer botRoleId,

            Boolean canManageChat,

            Boolean isAnonymous,
            Boolean canPromoteMembers,
            Boolean canDeleteMessages,
            Boolean canRestrictMembers,
            Boolean canChangeInfo,
            Boolean canInviteUsers,
            Boolean canPinMessages,
            Boolean canManageVideoChats) {

        //1.查询记录是否存在
//        TbUserBotinstanceQunManager tbUserBotinstanceQunManager = baseMapper.selectOneByUserQunBotId(tgUserId,qunId,tbBotInstanceId);
        TbUserBotinstanceQunManager tbUserBotinstanceQunManager = baseMapper.selectOneByQunBotId(qunId,tbBotInstanceId);

        //2.不存在，直接插入
        if(tbUserBotinstanceQunManager == null){

            TbUserBotinstanceQunManager tbUserBotinstanceQunManagerNew = new TbUserBotinstanceQunManager();

            tbUserBotinstanceQunManagerNew.setTgUserId(tgUserId + "");
            tbUserBotinstanceQunManagerNew.setTgQunId(qunId + "");
            tbUserBotinstanceQunManagerNew.setTbBotInstanceId(tbBotInstanceId);
            tbUserBotinstanceQunManagerNew.setBotRole(botRoleId);

            tbUserBotinstanceQunManagerNew.setCanManageChat(canManageChat ? 1 : 0);

            tbUserBotinstanceQunManagerNew.setIsAnonymous(isAnonymous ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanPromoteMembers(canPromoteMembers ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanDeleteMessages(canDeleteMessages ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanRestrictMembers(canRestrictMembers ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanChangeInfo(canChangeInfo ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanInviteUsers(canInviteUsers ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanPinMessages(canPinMessages ? 1 : 0);
            tbUserBotinstanceQunManagerNew.setCanManageVideoChats(canManageVideoChats ? 1 : 0);



            return baseMapper.insert(tbUserBotinstanceQunManagerNew);

        }else{

            //3.存在,更新数据
            tbUserBotinstanceQunManager.setBotRole(botRoleId);

            tbUserBotinstanceQunManager.setCanManageChat(canManageChat ? 1 : 0);

            tbUserBotinstanceQunManager.setIsAnonymous(isAnonymous ? 1 : 0);
            tbUserBotinstanceQunManager.setCanPromoteMembers(canPromoteMembers ? 1 : 0);

            tbUserBotinstanceQunManager.setCanDeleteMessages(canDeleteMessages ? 1 : 0);
            tbUserBotinstanceQunManager.setCanRestrictMembers(canRestrictMembers ? 1 : 0);
            tbUserBotinstanceQunManager.setCanChangeInfo(canChangeInfo ? 1 : 0);
            tbUserBotinstanceQunManager.setCanInviteUsers(canInviteUsers ? 1 : 0);
            tbUserBotinstanceQunManager.setCanPinMessages(canPinMessages ? 1 : 0);
            tbUserBotinstanceQunManager.setCanManageVideoChats(canManageVideoChats ? 1 : 0);


            return baseMapper.updateById(tbUserBotinstanceQunManager);
        }
    }

    @Override
    public int deleteByUserQunBotInsId(Long qunId, Integer tbBotInstanceId) {
        return baseMapper.deleteByQunBotInsId( qunId, tbBotInstanceId);
    }

    @Override
    public List<UserBotInsQunDto> queryGroupListByUserIdAndBotId(Long tgUserId, Integer botInstanceId) {
        TgBotRunSession tgBotRunSession = TgBotRunSession.startSuccessJzBaseBotMap.get(botInstanceId);
        BuBaseBot buBaseBot = tgBotRunSession.getBuBaseBot();

        List<UserBotInsQunDto> userBotInsQunDtos = baseMapper.selectListByTgUserIdAndBotId(tgUserId, botInstanceId);

        List<UserBotInsQunDto> collectNumber = new ArrayList<>();
        //获取群成员数量
        if(!CollectionUtils.isEmpty(userBotInsQunDtos)){
            collectNumber = userBotInsQunDtos.stream().map(x -> {
                int chatMemberCount = 0;
                try {
                    chatMemberCount = buBaseBot.getChatMemberCount(x.getTgQunId());
                } catch (TelegramApiException e) {
                    logger.info("获取群成员数量失败,tgUserId:"+tgUserId + " botInstanceId:" + botInstanceId,e);
                }
                x.setChatMemberCount(chatMemberCount);
                return x;
            }).collect(Collectors.toList());
        }

        List<UserBotInsQunDto> collectShenFen = new ArrayList<>();
        //获取用户在本群的身份
        if(!CollectionUtils.isEmpty(userBotInsQunDtos)){
            collectShenFen = collectNumber.stream().map(x -> {

                try {
                    ChatMember chatMember = buBaseBot.getChatMember(Long.valueOf(x.getTgQunId()), tgUserId);
                    String status = chatMember.getStatus();
                    String statusRemarkByStatus = TgQunRoleEnum.getStatusRemarkByStatus(status);
                    x.setTgUserRoleInQun(statusRemarkByStatus);
                } catch (TelegramApiException e) {
                    logger.info("获取群成员身份失败,tgUserId:" + tgUserId + " botInstanceId:" + botInstanceId, e);
                }
                return x;
            }).collect(Collectors.toList());
        }
        return collectShenFen;
    }

    @Override
    public int deleteByUserQunBotInsIdQunId(Long tgUserId, Integer tbBotInstanceId, String qunId) {
        return baseMapper.deleteByUserQunBotId(tgUserId.toString(), qunId, tbBotInstanceId);
    }

    @Override
    public int deleteByUserQunBotInsIdQunIdAndExistQun(Long tgUserId, Integer tbBotInstanceId, String qunId) {
        int deleteByUserQunBotId = baseMapper.deleteByUserQunBotId(tgUserId.toString(), qunId, tbBotInstanceId);

        TgBotRunSession tgBotRunSession = TgBotRunSession.startSuccessJzBaseBotMap.get(tbBotInstanceId);
        try {
            tgBotRunSession.getBuBaseBot().existQun(qunId);
        } catch (TelegramApiException e) {
            logger.error("退出群的API访问失败!!!",e);
        }

        return deleteByUserQunBotId;
    }
}




