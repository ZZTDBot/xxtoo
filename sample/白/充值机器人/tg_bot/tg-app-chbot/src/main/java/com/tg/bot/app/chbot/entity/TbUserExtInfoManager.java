package com.tg.bot.app.chbot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户拓展信息表管理
 * @TableName tb_user_ext_info_manager
 */
@TableName(value ="tb_user_ext_info_manager")
public class TbUserExtInfoManager implements Serializable {
    /**
     * tg用户账号
     */
    @TableId(value = "tg_user_id")
    private String tgUserId;

    /**
     * 绑定ID
     */
    @TableField(value = "bind_id")
    private String bindId;

    /**
     * 取汇旺信息
     */
    @TableField(value = "qu_hw_info")
    private String quHwInfo;

    /**
     * 取USDT信息
     */
    @TableField(value = "qu_usdt_info")
    private String quUsdtInfo;

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
     * tg用户账号
     */
    public String getTgUserId() {
        return tgUserId;
    }

    /**
     * tg用户账号
     */
    public void setTgUserId(String tgUserId) {
        this.tgUserId = tgUserId;
    }

    /**
     * 绑定ID
     */
    public String getBindId() {
        return bindId;
    }

    /**
     * 绑定ID
     */
    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    /**
     * 取汇旺信息
     */
    public String getQuHwInfo() {
        return quHwInfo;
    }

    /**
     * 取汇旺信息
     */
    public void setQuHwInfo(String quHwInfo) {
        this.quHwInfo = quHwInfo;
    }

    /**
     * 取USDT信息
     */
    public String getQuUsdtInfo() {
        return quUsdtInfo;
    }

    /**
     * 取USDT信息
     */
    public void setQuUsdtInfo(String quUsdtInfo) {
        this.quUsdtInfo = quUsdtInfo;
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
        TbUserExtInfoManager other = (TbUserExtInfoManager) that;
        return (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
            && (this.getBindId() == null ? other.getBindId() == null : this.getBindId().equals(other.getBindId()))
            && (this.getQuHwInfo() == null ? other.getQuHwInfo() == null : this.getQuHwInfo().equals(other.getQuHwInfo()))
            && (this.getQuUsdtInfo() == null ? other.getQuUsdtInfo() == null : this.getQuUsdtInfo().equals(other.getQuUsdtInfo()))
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
        result = prime * result + ((getBindId() == null) ? 0 : getBindId().hashCode());
        result = prime * result + ((getQuHwInfo() == null) ? 0 : getQuHwInfo().hashCode());
        result = prime * result + ((getQuUsdtInfo() == null) ? 0 : getQuUsdtInfo().hashCode());
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
        sb.append(", bindId=").append(bindId);
        sb.append(", quHwInfo=").append(quHwInfo);
        sb.append(", quUsdtInfo=").append(quUsdtInfo);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}