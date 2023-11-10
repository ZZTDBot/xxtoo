package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * tg群管理
 * @TableName tb_qun_manager
 */
@TableName(value ="tb_qun_manager")
public class TbQunManager implements Serializable {
    /**
     * tg群ID
     */
    @TableId
    private String tgQunId;

    /**
     * 群名称
     */
    private String qunName;

    /**
     * 群类型
     */
    private Integer qunType;

    /**
     * 群用户名
     */
    private String qunUserName;

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
     * 群名称
     */
    public String getQunName() {
        return qunName;
    }

    /**
     * 群名称
     */
    public void setQunName(String qunName) {
        this.qunName = qunName;
    }

    /**
     * 群类型
     */
    public Integer getQunType() {
        return qunType;
    }

    /**
     * 群类型
     */
    public void setQunType(Integer qunType) {
        this.qunType = qunType;
    }

    /**
     * 群用户名
     */
    public String getQunUserName() {
        return qunUserName;
    }

    /**
     * 群用户名
     */
    public void setQunUserName(String qunUserName) {
        this.qunUserName = qunUserName;
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
        TbQunManager other = (TbQunManager) that;
        return (this.getTgQunId() == null ? other.getTgQunId() == null : this.getTgQunId().equals(other.getTgQunId()))
            && (this.getQunName() == null ? other.getQunName() == null : this.getQunName().equals(other.getQunName()))
            && (this.getQunType() == null ? other.getQunType() == null : this.getQunType().equals(other.getQunType()))
            && (this.getQunUserName() == null ? other.getQunUserName() == null : this.getQunUserName().equals(other.getQunUserName()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTgQunId() == null) ? 0 : getTgQunId().hashCode());
        result = prime * result + ((getQunName() == null) ? 0 : getQunName().hashCode());
        result = prime * result + ((getQunType() == null) ? 0 : getQunType().hashCode());
        result = prime * result + ((getQunUserName() == null) ? 0 : getQunUserName().hashCode());
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
        sb.append(", tgQunId=").append(tgQunId);
        sb.append(", qunName=").append(qunName);
        sb.append(", qunType=").append(qunType);
        sb.append(", qunUserName=").append(qunUserName);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}