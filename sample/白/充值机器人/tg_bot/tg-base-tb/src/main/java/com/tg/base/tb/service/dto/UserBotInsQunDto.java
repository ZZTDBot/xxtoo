package com.tg.base.tb.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 主页群列表实体对象
 */
@Data
@ApiModel(value = "主页群列表实体对象",description = "主页群列表实体对象")
public class UserBotInsQunDto {
    /**
     * 用户_机器人实例_群关系主键
     */
    @ApiModelProperty(value = "用户_机器人实例_群关系主键")
    private Integer tbUserBotinstanceQunId;

    /**
     * 用户tg账号
     */
    private String tgUserId;

    /**
     * 我在本群的身份
     */
    private String tgUserRoleInQun;

    /**
     * 机器人实例ID
     */
    private Integer tbBotInstanceId;

    /**
     * tg群ID
     */
    private String tgQunId;

    /**
     * tg群名称
     */
    private String qunName;

    /**
     * tg群类型
     */
    private int qunType;

    /**
     * tg群类型说明
     */
    private String qunTypeRemark;

    /**
     * 群人数
     */
    private int chatMemberCount;

    /**
     * 机器人角色(1:群主 2:管理员 3:成员)
     */
    private Integer botRole;

    /**
     * 保持匿名
     */
    private Integer isAnonymous;

    /**
     * 管理群
     */
    private Integer canManageChat;

    /**
     * 删除消息
     */
    private Integer canDeleteMessages;

    /**
     * 封禁该用户
     */
    private Integer canRestrictMembers;

    /**
     * 添加新管理员
     */
    private Integer canPromoteMembers;

    /**
     * 修改群组信息
     */
    private Integer canChangeInfo;

    /**
     * 生成邀请链接
     */
    private Integer canInviteUsers;

    /**
     * 置顶消息
     */
    private Integer canPinMessages;

    /**
     * 管理视频通话
     */
    private Integer canManageVideoChats;
}
