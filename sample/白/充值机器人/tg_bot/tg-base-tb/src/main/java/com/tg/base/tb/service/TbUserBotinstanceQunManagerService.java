package com.tg.base.tb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.base.tb.entity.TbUserBotinstanceQunManager;
import com.tg.base.tb.service.dto.UserBotInsQunDto;

import java.util.List;

/**
* @description 针对表【tb_user_botinstance_qun_manager(用户_机器人实例_群_关系管理)】的数据库操作Service
* @createDate 2022-12-24 14:35:42
*/
public interface TbUserBotinstanceQunManagerService extends IService<TbUserBotinstanceQunManager> {


    /**
     * 插入或更新 用户-机器人-群 关系记录
     * @param tgUserId    飞机用户ID
     * @param qunId       飞机群ID
     * @param tbBotInstanceId    飞机机器人ID
     * @param botRoleId    飞机机器人在本群角色
     *
     * @param canManageChat         机器人权限-----管理群
     *
     * @param isAnonymous           机器人权限-----保持匿名
     * @param canPromoteMembers     机器人权限-----添加新管理员
     * @param canDeleteMessages     机器人权限-----删除消息
     * @param canRestrictMembers    机器人权限-----封禁该用户
     * @param canChangeInfo         机器人权限-----修改群组信息
     * @param canInviteUsers        机器人权限-----生成邀请链接
     * @param canPinMessages        机器人权限-----置顶消息
     * @param canManageVideoChats   机器人权限-----管理视频通话
     *
     * @return
     */
    int saveOrUpdateByUserQunBotInsId(
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
            Boolean canManageVideoChats
    );

    /**
     * 删除 用户-机器人-群 关系记录
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    int deleteByUserQunBotInsId(Long qunId, Integer tbBotInstanceId);

    /**
     * 查询我的群列表---查询条件 飞机用户ID，机器人ID
     * @param tgUserId
     * @param botInstanceId
     * @return
     */
    List<UserBotInsQunDto> queryGroupListByUserIdAndBotId(Long tgUserId, Integer botInstanceId);

    /**
     * 根据用户ID 群ID 机器人ID  删除关系
     * @param tgUserId
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    int deleteByUserQunBotInsIdQunId(Long tgUserId, Integer tbBotInstanceId, String qunId);

    /**
     * 根据用户ID 群ID 机器人ID  删除关系
     * @param tgUserId
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    int deleteByUserQunBotInsIdQunIdAndExistQun(Long tgUserId, Integer tbBotInstanceId, String qunId);
}
