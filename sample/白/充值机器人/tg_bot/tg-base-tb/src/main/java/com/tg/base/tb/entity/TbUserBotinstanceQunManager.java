package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户_机器人实例_群_关系管理
 * @TableName tb_user_botinstance_qun_manager
 */
@TableName(value ="tb_user_botinstance_qun_manager")
public class TbUserBotinstanceQunManager implements Serializable {
    /**
     * 用户_机器人实例_群关系主键
     */
    @TableId(type = IdType.AUTO)
    private Integer tbUserBotinstanceQunId;

    /**
     * 用户tg账号
     */
    private String tgUserId;

    /**
     * 机器人实例ID
     */
    private Integer tbBotInstanceId;

    /**
     * tg群ID
     */
    private String tgQunId;

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

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户_机器人实例_群关系主键
     */
    public Integer getTbUserBotinstanceQunId() {
        return tbUserBotinstanceQunId;
    }

    /**
     * 用户_机器人实例_群关系主键
     */
    public void setTbUserBotinstanceQunId(Integer tbUserBotinstanceQunId) {
        this.tbUserBotinstanceQunId = tbUserBotinstanceQunId;
    }

    /**
     * 用户tg账号
     */
    public String getTgUserId() {
        return tgUserId;
    }

    /**
     * 用户tg账号
     */
    public void setTgUserId(String tgUserId) {
        this.tgUserId = tgUserId;
    }

    /**
     * 机器人实例ID
     */
    public Integer getTbBotInstanceId() {
        return tbBotInstanceId;
    }

    /**
     * 机器人实例ID
     */
    public void setTbBotInstanceId(Integer tbBotInstanceId) {
        this.tbBotInstanceId = tbBotInstanceId;
    }

    /**
     * tg群ID
     */
    public String getTgQunId() {
        return tgQunId;
    }

    /**
     * tg群ID
     */
    public void setTgQunId(String tgQunId) {
        this.tgQunId = tgQunId;
    }

    /**
     * 机器人角色(1:群主 2:管理员 3:成员)
     */
    public Integer getBotRole() {
        return botRole;
    }

    /**
     * 机器人角色(1:群主 2:管理员 3:成员)
     */
    public void setBotRole(Integer botRole) {
        this.botRole = botRole;
    }

    /**
     * 保持匿名
     */
    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    /**
     * 保持匿名
     */
    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    /**
     * 管理群
     */
    public Integer getCanManageChat() {
        return canManageChat;
    }

    /**
     * 管理群
     */
    public void setCanManageChat(Integer canManageChat) {
        this.canManageChat = canManageChat;
    }

    /**
     * 删除消息
     */
    public Integer getCanDeleteMessages() {
        return canDeleteMessages;
    }

    /**
     * 删除消息
     */
    public void setCanDeleteMessages(Integer canDeleteMessages) {
        this.canDeleteMessages = canDeleteMessages;
    }

    /**
     * 封禁该用户
     */
    public Integer getCanRestrictMembers() {
        return canRestrictMembers;
    }

    /**
     * 封禁该用户
     */
    public void setCanRestrictMembers(Integer canRestrictMembers) {
        this.canRestrictMembers = canRestrictMembers;
    }

    /**
     * 添加新管理员
     */
    public Integer getCanPromoteMembers() {
        return canPromoteMembers;
    }

    /**
     * 添加新管理员
     */
    public void setCanPromoteMembers(Integer canPromoteMembers) {
        this.canPromoteMembers = canPromoteMembers;
    }

    /**
     * 修改群组信息
     */
    public Integer getCanChangeInfo() {
        return canChangeInfo;
    }

    /**
     * 修改群组信息
     */
    public void setCanChangeInfo(Integer canChangeInfo) {
        this.canChangeInfo = canChangeInfo;
    }

    /**
     * 生成邀请链接
     */
    public Integer getCanInviteUsers() {
        return canInviteUsers;
    }

    /**
     * 生成邀请链接
     */
    public void setCanInviteUsers(Integer canInviteUsers) {
        this.canInviteUsers = canInviteUsers;
    }

    /**
     * 置顶消息
     */
    public Integer getCanPinMessages() {
        return canPinMessages;
    }

    /**
     * 置顶消息
     */
    public void setCanPinMessages(Integer canPinMessages) {
        this.canPinMessages = canPinMessages;
    }

    /**
     * 管理视频通话
     */
    public Integer getCanManageVideoChats() {
        return canManageVideoChats;
    }

    /**
     * 管理视频通话
     */
    public void setCanManageVideoChats(Integer canManageVideoChats) {
        this.canManageVideoChats = canManageVideoChats;
    }

    /**
     * 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbUserBotinstanceQunManager other = (TbUserBotinstanceQunManager) that;
        return (this.getTbUserBotinstanceQunId() == null ? other.getTbUserBotinstanceQunId() == null : this.getTbUserBotinstanceQunId().equals(other.getTbUserBotinstanceQunId()))
            && (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
            && (this.getTbBotInstanceId() == null ? other.getTbBotInstanceId() == null : this.getTbBotInstanceId().equals(other.getTbBotInstanceId()))
            && (this.getTgQunId() == null ? other.getTgQunId() == null : this.getTgQunId().equals(other.getTgQunId()))
            && (this.getBotRole() == null ? other.getBotRole() == null : this.getBotRole().equals(other.getBotRole()))
            && (this.getIsAnonymous() == null ? other.getIsAnonymous() == null : this.getIsAnonymous().equals(other.getIsAnonymous()))
            && (this.getCanManageChat() == null ? other.getCanManageChat() == null : this.getCanManageChat().equals(other.getCanManageChat()))
            && (this.getCanDeleteMessages() == null ? other.getCanDeleteMessages() == null : this.getCanDeleteMessages().equals(other.getCanDeleteMessages()))
            && (this.getCanRestrictMembers() == null ? other.getCanRestrictMembers() == null : this.getCanRestrictMembers().equals(other.getCanRestrictMembers()))
            && (this.getCanPromoteMembers() == null ? other.getCanPromoteMembers() == null : this.getCanPromoteMembers().equals(other.getCanPromoteMembers()))
            && (this.getCanChangeInfo() == null ? other.getCanChangeInfo() == null : this.getCanChangeInfo().equals(other.getCanChangeInfo()))
            && (this.getCanInviteUsers() == null ? other.getCanInviteUsers() == null : this.getCanInviteUsers().equals(other.getCanInviteUsers()))
            && (this.getCanPinMessages() == null ? other.getCanPinMessages() == null : this.getCanPinMessages().equals(other.getCanPinMessages()))
            && (this.getCanManageVideoChats() == null ? other.getCanManageVideoChats() == null : this.getCanManageVideoChats().equals(other.getCanManageVideoChats()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTbUserBotinstanceQunId() == null) ? 0 : getTbUserBotinstanceQunId().hashCode());
        result = prime * result + ((getTgUserId() == null) ? 0 : getTgUserId().hashCode());
        result = prime * result + ((getTbBotInstanceId() == null) ? 0 : getTbBotInstanceId().hashCode());
        result = prime * result + ((getTgQunId() == null) ? 0 : getTgQunId().hashCode());
        result = prime * result + ((getBotRole() == null) ? 0 : getBotRole().hashCode());
        result = prime * result + ((getIsAnonymous() == null) ? 0 : getIsAnonymous().hashCode());
        result = prime * result + ((getCanManageChat() == null) ? 0 : getCanManageChat().hashCode());
        result = prime * result + ((getCanDeleteMessages() == null) ? 0 : getCanDeleteMessages().hashCode());
        result = prime * result + ((getCanRestrictMembers() == null) ? 0 : getCanRestrictMembers().hashCode());
        result = prime * result + ((getCanPromoteMembers() == null) ? 0 : getCanPromoteMembers().hashCode());
        result = prime * result + ((getCanChangeInfo() == null) ? 0 : getCanChangeInfo().hashCode());
        result = prime * result + ((getCanInviteUsers() == null) ? 0 : getCanInviteUsers().hashCode());
        result = prime * result + ((getCanPinMessages() == null) ? 0 : getCanPinMessages().hashCode());
        result = prime * result + ((getCanManageVideoChats() == null) ? 0 : getCanManageVideoChats().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tbUserBotinstanceQunId=").append(tbUserBotinstanceQunId);
        sb.append(", tgUserId=").append(tgUserId);
        sb.append(", tbBotInstanceId=").append(tbBotInstanceId);
        sb.append(", tgQunId=").append(tgQunId);
        sb.append(", botRole=").append(botRole);
        sb.append(", isAnonymous=").append(isAnonymous);
        sb.append(", canManageChat=").append(canManageChat);
        sb.append(", canDeleteMessages=").append(canDeleteMessages);
        sb.append(", canRestrictMembers=").append(canRestrictMembers);
        sb.append(", canPromoteMembers=").append(canPromoteMembers);
        sb.append(", canChangeInfo=").append(canChangeInfo);
        sb.append(", canInviteUsers=").append(canInviteUsers);
        sb.append(", canPinMessages=").append(canPinMessages);
        sb.append(", canManageVideoChats=").append(canManageVideoChats);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}