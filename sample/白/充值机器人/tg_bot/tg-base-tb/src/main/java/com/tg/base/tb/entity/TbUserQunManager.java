package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户_群_关系管理
 * @TableName tb_user_qun_manager
 */
@TableName(value ="tb_user_qun_manager")
public class TbUserQunManager implements Serializable {
    /**
     * 用户与群关系主键
     */
    @TableId(type = IdType.AUTO)
    private Integer userQunId;

    /**
     * tg群ID
     */
    private String tgQunId;

    /**
     * 用户tg账号
     */
    private String tgUserId;


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
     * 用户与群关系主键
     */
    public Integer getUserQunId() {
        return userQunId;
    }

    /**
     * 用户与群关系主键
     */
    public void setUserQunId(Integer userQunId) {
        this.userQunId = userQunId;
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
    public String toString() {
        return "TbUserQunManager{" +
                "userQunId=" + userQunId +
                ", tgQunId='" + tgQunId + '\'' +
                ", tgUserId='" + tgUserId + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}