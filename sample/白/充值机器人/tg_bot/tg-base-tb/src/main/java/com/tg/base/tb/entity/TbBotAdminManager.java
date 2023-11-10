package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 机器人的管理员表
 * @TableName tb_bot_admin_manager
 */
@TableName(value ="tb_bot_admin_manager")
public class TbBotAdminManager implements Serializable {
    /**
     * 机器人管理员主键ID
     */
    @TableId(value = "tg__bot_admin_id", type = IdType.AUTO)
    private Integer tgBotAdminId;

    /**
     * 机器人实例ID
     */
    @TableField(value = "tb_bot_instance_id")
    private Integer tbBotInstanceId;

    /**
     * 用户tg账号
     */
    @TableField(value = "tg_user_id")
    private String tgUserId;

    /**
     * 
     */
    @TableField(value = "grant_end_date_time")
    private Date grantEndDateTime;

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
     * 机器人管理员主键ID
     */
    public Integer getTgBotAdminId() {
        return tgBotAdminId;
    }

    /**
     * 机器人管理员主键ID
     */
    public void setTgBotAdminId(Integer tgBotAdminId) {
        this.tgBotAdminId = tgBotAdminId;
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
     * 
     */
    public Date getGrantEndDateTime() {
        return grantEndDateTime;
    }

    /**
     * 
     */
    public void setGrantEndDateTime(Date grantEndDateTime) {
        this.grantEndDateTime = grantEndDateTime;
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
        TbBotAdminManager other = (TbBotAdminManager) that;
        return (this.getTgBotAdminId() == null ? other.getTgBotAdminId() == null : this.getTgBotAdminId().equals(other.getTgBotAdminId()))
            && (this.getTbBotInstanceId() == null ? other.getTbBotInstanceId() == null : this.getTbBotInstanceId().equals(other.getTbBotInstanceId()))
            && (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
            && (this.getGrantEndDateTime() == null ? other.getGrantEndDateTime() == null : this.getGrantEndDateTime().equals(other.getGrantEndDateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTgBotAdminId() == null) ? 0 : getTgBotAdminId().hashCode());
        result = prime * result + ((getTbBotInstanceId() == null) ? 0 : getTbBotInstanceId().hashCode());
        result = prime * result + ((getTgUserId() == null) ? 0 : getTgUserId().hashCode());
        result = prime * result + ((getGrantEndDateTime() == null) ? 0 : getGrantEndDateTime().hashCode());
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
        sb.append(", tgBotAdminId=").append(tgBotAdminId);
        sb.append(", tbBotInstanceId=").append(tbBotInstanceId);
        sb.append(", tgUserId=").append(tgUserId);
        sb.append(", grantEndDateTime=").append(grantEndDateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}