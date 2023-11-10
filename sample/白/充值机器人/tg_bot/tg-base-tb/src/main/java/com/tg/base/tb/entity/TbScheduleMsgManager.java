package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时消息管理
 * @TableName tb_schedule_msg_manager
 */
@TableName(value ="tb_schedule_msg_manager")
public class TbScheduleMsgManager implements Serializable {
    /**
     * 定时消息ID
     */
    @TableId(value = "schedule_msg_id", type = IdType.AUTO)
    private Integer scheduleMsgId;

    /**
     * 用户tg账号
     */
    @TableField(value = "tg_user_id")
    private String tgUserId;

    /**
     * 机器人实例ID
     */
    @TableField(value = "tb_bot_instance_id")
    private Integer tbBotInstanceId;

    /**
     * 会话ID
     */
    @TableField(value = "qun_chat_id")
    private String qunChatId;

    /**
     * 消息内容
     */
    @TableField(value = "msg_context")
    private String msgContext;

    /**
     * 消息类型(1:文本  2:图片)
     */
    @TableField(value = "msg_type")
    private Integer msgType;

    /**
     * 附加按钮
     */
    @TableField(value = "buttons")
    private String buttons;

    /**
     * 多长时间自动删除消息(秒)
     */
    @TableField(value = "delete_seconds")
    private Integer deleteSeconds;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 定时消息ID
     */
    public Integer getScheduleMsgId() {
        return scheduleMsgId;
    }

    /**
     * 定时消息ID
     */
    public void setScheduleMsgId(Integer scheduleMsgId) {
        this.scheduleMsgId = scheduleMsgId;
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
     * 会话ID
     */
    public String getQunChatId() {
        return qunChatId;
    }

    /**
     * 会话ID
     */
    public void setChatId(String qunChatId) {
        this.qunChatId = qunChatId;
    }

    /**
     * 消息内容
     */
    public String getMsgContext() {
        return msgContext;
    }

    /**
     * 消息内容
     */
    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext;
    }

    /**
     * 消息类型(1:文本  2:图片)
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * 消息类型(1:文本  2:图片)
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    /**
     * 附加按钮
     */
    public String getButtons() {
        return buttons;
    }

    /**
     * 附加按钮
     */
    public void setButtons(String buttons) {
        this.buttons = buttons;
    }

    /**
     * 多长时间自动删除消息(秒)
     */
    public Integer getDeleteSeconds() {
        return deleteSeconds;
    }

    /**
     * 多长时间自动删除消息(秒)
     */
    public void setDeleteSeconds(Integer deleteSeconds) {
        this.deleteSeconds = deleteSeconds;
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
        TbScheduleMsgManager other = (TbScheduleMsgManager) that;
        return (this.getScheduleMsgId() == null ? other.getScheduleMsgId() == null : this.getScheduleMsgId().equals(other.getScheduleMsgId()))
                && (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
                && (this.getTbBotInstanceId() == null ? other.getTbBotInstanceId() == null : this.getTbBotInstanceId().equals(other.getTbBotInstanceId()))
                && (this.getQunChatId() == null ? other.getQunChatId() == null : this.getQunChatId().equals(other.getQunChatId()))
                && (this.getMsgContext() == null ? other.getMsgContext() == null : this.getMsgContext().equals(other.getMsgContext()))
                && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
                && (this.getButtons() == null ? other.getButtons() == null : this.getButtons().equals(other.getButtons()))
                && (this.getDeleteSeconds() == null ? other.getDeleteSeconds() == null : this.getDeleteSeconds().equals(other.getDeleteSeconds()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getScheduleMsgId() == null) ? 0 : getScheduleMsgId().hashCode());
        result = prime * result + ((getTgUserId() == null) ? 0 : getTgUserId().hashCode());
        result = prime * result + ((getTbBotInstanceId() == null) ? 0 : getTbBotInstanceId().hashCode());
        result = prime * result + ((getQunChatId() == null) ? 0 : getQunChatId().hashCode());
        result = prime * result + ((getMsgContext() == null) ? 0 : getMsgContext().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getButtons() == null) ? 0 : getButtons().hashCode());
        result = prime * result + ((getDeleteSeconds() == null) ? 0 : getDeleteSeconds().hashCode());
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
        sb.append(", scheduleMsgId=").append(scheduleMsgId);
        sb.append(", tgUserId=").append(tgUserId);
        sb.append(", tbBotInstanceId=").append(tbBotInstanceId);
        sb.append(", qunChatId=").append(qunChatId);
        sb.append(", msgContext=").append(msgContext);
        sb.append(", msgType=").append(msgType);
        sb.append(", buttons=").append(buttons);
        sb.append(", deleteSeconds=").append(deleteSeconds);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}