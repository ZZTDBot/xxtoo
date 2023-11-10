package com.tg.bot.base.bot.bean;

import lombok.Data;

/**
 * 用户在群里的权限集合
 */
@Data
public class MyChatMemberPermissions {

    /**
     * 保持匿名
     */
    private Boolean isAnonymous = Boolean.FALSE;

    /**
     * 管理群
     */
    private Boolean canManageChat = Boolean.FALSE;

    /**
     * 删除消息
     */
    private Boolean canDeleteMessages = Boolean.FALSE;

    /**
     * 封禁该用户
     */
    private Boolean canRestrictMembers = Boolean.FALSE;

    /**
     * 添加新管理员
     */
    private Boolean canPromoteMembers = Boolean.FALSE;

    /**
     * 修改群组信息
     */
    private Boolean canChangeInfo = Boolean.FALSE;

    /**
     * 生成邀请链接
     */
    private Boolean canInviteUsers = Boolean.FALSE;

    /**
     * 置顶消息
     */
    private Boolean canPinMessages = Boolean.FALSE;

    /**
     * 管理视频通话
     */
    private Boolean canManageVideoChats = Boolean.FALSE;

}
