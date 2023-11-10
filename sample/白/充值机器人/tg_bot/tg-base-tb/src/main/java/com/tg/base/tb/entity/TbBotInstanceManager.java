package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 机器人实例管理
 * @TableName tb_bot_instance_manager
 */
@TableName(value ="tb_bot_instance_manager")
public class TbBotInstanceManager implements Serializable {
    /**
     * 机器人实例ID
     */
    @TableId(value = "tb_bot_instance_id", type = IdType.AUTO)
    private Integer tbBotInstanceId;

    /**
     * 机器人的添加人
     */
    @TableField(value = "tg_bot_create_user_id")
    private String tgBotCreateUserId;

    /**
     * 机器人的归属人
     */
    @TableField(value = "tg_bot_gs_user_id")
    private String tgBotGsUserId;

    /**
     * 机器人用户名
     */
    @TableField(value = "bot_user_name")
    private String botUserName;

    /**
     * 机器token
     */
    @TableField(value = "bot_token")
    private String botToken;

    /**
     * 机器人型号
     */
    @TableField(value = "bot_no")
    private String botNo;

    /**
     * 机器人描述
     */
    @TableField(value = "bot_remark")
    private String botRemark;

    /**
     * 机器人启用状态(1: 启用  0:停用)
     */
    @TableField(value = "bot_qy_status")
    private Integer botQyStatus;

    /**
     * 机器人运行状态(1: 运行中 0:停止)
     */
    @TableField(value = "run_status")
    private Integer runStatus;

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
     * 机器人的添加人
     */
    public String getTgBotCreateUserId() {
        return tgBotCreateUserId;
    }

    /**
     * 机器人的添加人
     */
    public void setTgBotCreateUserId(String tgBotCreateUserId) {
        this.tgBotCreateUserId = tgBotCreateUserId;
    }

    /**
     * 机器人的归属人
     */
    public String getTgBotGsUserId() {
        return tgBotGsUserId;
    }

    /**
     * 机器人的归属人
     */
    public void setTgBotGsUserId(String tgBotGsUserId) {
        this.tgBotGsUserId = tgBotGsUserId;
    }

    /**
     * 机器人用户名
     */
    public String getBotUserName() {
        return botUserName;
    }

    /**
     * 机器人用户名
     */
    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    /**
     * 机器token
     */
    public String getBotToken() {
        return botToken;
    }

    /**
     * 机器token
     */
    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    /**
     * 机器人型号
     */
    public String getBotNo() {
        return botNo;
    }

    /**
     * 机器人型号
     */
    public void setBotNo(String botNo) {
        this.botNo = botNo;
    }

    /**
     * 机器人描述
     */
    public String getBotRemark() {
        return botRemark;
    }

    /**
     * 机器人描述
     */
    public void setBotRemark(String botRemark) {
        this.botRemark = botRemark;
    }

    /**
     * 机器人启用状态(1: 启用  0:停用)
     */
    public Integer getBotQyStatus() {
        return botQyStatus;
    }

    /**
     * 机器人启用状态(1: 启用  0:停用)
     */
    public void setBotQyStatus(Integer botQyStatus) {
        this.botQyStatus = botQyStatus;
    }

    /**
     * 机器人运行状态(1: 运行中 0:停止)
     */
    public Integer getRunStatus() {
        return runStatus;
    }

    /**
     * 机器人运行状态(1: 运行中 0:停止)
     */
    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
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
        TbBotInstanceManager other = (TbBotInstanceManager) that;
        return (this.getTbBotInstanceId() == null ? other.getTbBotInstanceId() == null : this.getTbBotInstanceId().equals(other.getTbBotInstanceId()))
                && (this.getTgBotCreateUserId() == null ? other.getTgBotCreateUserId() == null : this.getTgBotCreateUserId().equals(other.getTgBotCreateUserId()))
                && (this.getTgBotGsUserId() == null ? other.getTgBotGsUserId() == null : this.getTgBotGsUserId().equals(other.getTgBotGsUserId()))
                && (this.getBotUserName() == null ? other.getBotUserName() == null : this.getBotUserName().equals(other.getBotUserName()))
                && (this.getBotToken() == null ? other.getBotToken() == null : this.getBotToken().equals(other.getBotToken()))
                && (this.getBotNo() == null ? other.getBotNo() == null : this.getBotNo().equals(other.getBotNo()))
                && (this.getBotRemark() == null ? other.getBotRemark() == null : this.getBotRemark().equals(other.getBotRemark()))
                && (this.getBotQyStatus() == null ? other.getBotQyStatus() == null : this.getBotQyStatus().equals(other.getBotQyStatus()))
                && (this.getRunStatus() == null ? other.getRunStatus() == null : this.getRunStatus().equals(other.getRunStatus()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTbBotInstanceId() == null) ? 0 : getTbBotInstanceId().hashCode());
        result = prime * result + ((getTgBotCreateUserId() == null) ? 0 : getTgBotCreateUserId().hashCode());
        result = prime * result + ((getTgBotGsUserId() == null) ? 0 : getTgBotGsUserId().hashCode());
        result = prime * result + ((getBotUserName() == null) ? 0 : getBotUserName().hashCode());
        result = prime * result + ((getBotToken() == null) ? 0 : getBotToken().hashCode());
        result = prime * result + ((getBotNo() == null) ? 0 : getBotNo().hashCode());
        result = prime * result + ((getBotRemark() == null) ? 0 : getBotRemark().hashCode());
        result = prime * result + ((getBotQyStatus() == null) ? 0 : getBotQyStatus().hashCode());
        result = prime * result + ((getRunStatus() == null) ? 0 : getRunStatus().hashCode());
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
        sb.append(", tbBotInstanceId=").append(tbBotInstanceId);
        sb.append(", tgBotCreateUserId=").append(tgBotCreateUserId);
        sb.append(", tgBotGsUserId=").append(tgBotGsUserId);
        sb.append(", botUserName=").append(botUserName);
        sb.append(", botToken=").append(botToken);
        sb.append(", botNo=").append(botNo);
        sb.append(", botRemark=").append(botRemark);
        sb.append(", botQyStatus=").append(botQyStatus);
        sb.append(", runStatus=").append(runStatus);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}