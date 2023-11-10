package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户管理
 * @TableName tb_user_manager
 */
@TableName(value ="tb_user_manager")
public class TbUserManager implements Serializable {
    /**
     * 用户tg账号
     */
    @TableId(value = "tg_user_id")
    private String tgUserId;

    /**
     * 用户姓
     */
    @TableField(value = "user_first_name")
    private String userFirstName;

    /**
     * 是否机器人
     */
    @TableField(value = "is_bot")
    private Integer isBot;

    /**
     * 用户名称
     */
    @TableField(value = "user_last_name")
    private String userLastName;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 语言环境
     */
    @TableField(value = "language_code")
    private String languageCode;

    /**
     * 用户角色(1.超级管理员 2:普通用户)
     */
    @TableField(value = "user_role")
    private Integer userRole;

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
     * 用户姓
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * 用户姓
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * 是否机器人
     */
    public Integer getIsBot() {
        return isBot;
    }

    /**
     * 是否机器人
     */
    public void setIsBot(Integer isBot) {
        this.isBot = isBot;
    }

    /**
     * 用户名称
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * 用户名称
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 语言环境
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * 语言环境
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * 用户角色(1.超级管理员 2:普通用户)
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 用户角色(1.超级管理员 2:普通用户)
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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
        TbUserManager other = (TbUserManager) that;
        return (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
                && (this.getUserFirstName() == null ? other.getUserFirstName() == null : this.getUserFirstName().equals(other.getUserFirstName()))
                && (this.getIsBot() == null ? other.getIsBot() == null : this.getIsBot().equals(other.getIsBot()))
                && (this.getUserLastName() == null ? other.getUserLastName() == null : this.getUserLastName().equals(other.getUserLastName()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getLanguageCode() == null ? other.getLanguageCode() == null : this.getLanguageCode().equals(other.getLanguageCode()))
                && (this.getUserRole() == null ? other.getUserRole() == null : this.getUserRole().equals(other.getUserRole()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTgUserId() == null) ? 0 : getTgUserId().hashCode());
        result = prime * result + ((getUserFirstName() == null) ? 0 : getUserFirstName().hashCode());
        result = prime * result + ((getIsBot() == null) ? 0 : getIsBot().hashCode());
        result = prime * result + ((getUserLastName() == null) ? 0 : getUserLastName().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getLanguageCode() == null) ? 0 : getLanguageCode().hashCode());
        result = prime * result + ((getUserRole() == null) ? 0 : getUserRole().hashCode());
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
        sb.append(", tgUserId=").append(tgUserId);
        sb.append(", userFirstName=").append(userFirstName);
        sb.append(", isBot=").append(isBot);
        sb.append(", userLastName=").append(userLastName);
        sb.append(", userName=").append(userName);
        sb.append(", languageCode=").append(languageCode);
        sb.append(", userRole=").append(userRole);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}