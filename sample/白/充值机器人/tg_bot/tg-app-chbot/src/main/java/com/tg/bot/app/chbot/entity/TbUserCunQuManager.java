package com.tg.bot.app.chbot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户存取记录表管理
 * @TableName tb_user_cun_qu_manager
 */
@TableName(value ="tb_user_cun_qu_manager")
public class TbUserCunQuManager implements Serializable {
    /**
     * 用户存取记录UUID
     */
    @TableId(value = "tg_user_cun_qu_uuid")
    private String tgUserCunQuUuid;

    /**
     * tg用户账号
     */
    @TableField(value = "tg_user_id")
    private String tgUserId;

    /**
     * tg用户消息Id
     */
    @TableField(value = "tg_user_msg_id")
    private Integer tgUserMsgId;

    /**
     * 客服群ID
     */
    @TableField(value = "kefu_qun_id")
    private String kefuQunId;

    /**
     * 群消息Id
     */
    @TableField(value = "qun_msg_id")
    private Integer qunMsgId;

    /**
     * 绑定ID
     */
    @TableField(value = "bind_id")
    private String bindId;

    /**
     * 存取类型(1:存  2:取)
     */
    @TableField(value = "cun_qu_type")
    private Integer cunQuType;

    /**
     * 存入的账户类型(1:USDT 2:汇旺)
     */
    @TableField(value = "cun_account_type")
    private Integer cunAccountType;

    /**
     * 存入时的账户信息
     */
    @TableField(value = "cun_cru_usdt_account_info")
    private String cunCruUsdtAccountInfo;

    /**
     * 存入时的汇旺账户信息
     */
    @TableField(value = "cun_cru_hw_account_info")
    private String cunCruHwAccountInfo;

    /**
     * 存入时的fileID
     */
    @TableField(value = "cun_file_id")
    private String cunFileId;

    /**
     * 取出的账户类型(1:USDT 2:汇旺)
     */
    @TableField(value = "qu_account_type")
    private Integer quAccountType;

    /**
     * 取金额
     */
    @TableField(value = "qu_amount")
    private Double quAmount;

    /**
     * 取出时的USDT账户信息
     */
    @TableField(value = "qu_cru_usdt_account_info")
    private String quCruUsdtAccountInfo;

    /**
     * 取出时的汇旺账户信息
     */
    @TableField(value = "qu_cru_hw_account_info")
    private String quCruHwAccountInfo;

    /**
     * 订单状态(1:已提交 2:正在处理 3:已完成)
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

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
     * 用户存取记录UUID
     */
    public String getTgUserCunQuUuid() {
        return tgUserCunQuUuid;
    }

    /**
     * 用户存取记录UUID
     */
    public void setTgUserCunQuUuid(String tgUserCunQuUuid) {
        this.tgUserCunQuUuid = tgUserCunQuUuid;
    }

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
     * tg用户消息Id
     */
    public Integer getTgUserMsgId() {
        return tgUserMsgId;
    }

    /**
     * tg用户消息Id
     */
    public void setTgUserMsgId(Integer tgUserMsgId) {
        this.tgUserMsgId = tgUserMsgId;
    }

    /**
     * 客服群ID
     */
    public String getKefuQunId() {
        return kefuQunId;
    }

    /**
     * 客服群ID
     */
    public void setKefuQunId(String kefuQunId) {
        this.kefuQunId = kefuQunId;
    }

    /**
     * 群消息Id
     */
    public Integer getQunMsgId() {
        return qunMsgId;
    }

    /**
     * 群消息Id
     */
    public void setQunMsgId(Integer qunMsgId) {
        this.qunMsgId = qunMsgId;
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
     * 存取类型(1:存  2:取)
     */
    public Integer getCunQuType() {
        return cunQuType;
    }

    /**
     * 存取类型(1:存  2:取)
     */
    public void setCunQuType(Integer cunQuType) {
        this.cunQuType = cunQuType;
    }

    /**
     * 存入的账户类型(1:USDT 2:汇旺)
     */
    public Integer getCunAccountType() {
        return cunAccountType;
    }

    /**
     * 存入的账户类型(1:USDT 2:汇旺)
     */
    public void setCunAccountType(Integer cunAccountType) {
        this.cunAccountType = cunAccountType;
    }

    /**
     * 存入时的账户信息
     */
    public String getCunCruUsdtAccountInfo() {
        return cunCruUsdtAccountInfo;
    }

    /**
     * 存入时的账户信息
     */
    public void setCunCruUsdtAccountInfo(String cunCruUsdtAccountInfo) {
        this.cunCruUsdtAccountInfo = cunCruUsdtAccountInfo;
    }

    /**
     * 存入时的汇旺账户信息
     */
    public String getCunCruHwAccountInfo() {
        return cunCruHwAccountInfo;
    }

    /**
     * 存入时的汇旺账户信息
     */
    public void setCunCruHwAccountInfo(String cunCruHwAccountInfo) {
        this.cunCruHwAccountInfo = cunCruHwAccountInfo;
    }

    /**
     * 存入时的fileID
     */
    public String getCunFileId() {
        return cunFileId;
    }

    /**
     * 存入时的fileID
     */
    public void setCunFileId(String cunFileId) {
        this.cunFileId = cunFileId;
    }

    /**
     * 取出的账户类型(1:USDT 2:汇旺)
     */
    public Integer getQuAccountType() {
        return quAccountType;
    }

    /**
     * 取出的账户类型(1:USDT 2:汇旺)
     */
    public void setQuAccountType(Integer quAccountType) {
        this.quAccountType = quAccountType;
    }

    /**
     * 取金额
     */
    public Double getQuAmount() {
        return quAmount;
    }

    /**
     * 取金额
     */
    public void setQuAmount(Double quAmount) {
        this.quAmount = quAmount;
    }

    /**
     * 取出时的USDT账户信息
     */
    public String getQuCruUsdtAccountInfo() {
        return quCruUsdtAccountInfo;
    }

    /**
     * 取出时的USDT账户信息
     */
    public void setQuCruUsdtAccountInfo(String quCruUsdtAccountInfo) {
        this.quCruUsdtAccountInfo = quCruUsdtAccountInfo;
    }

    /**
     * 取出时的汇旺账户信息
     */
    public String getQuCruHwAccountInfo() {
        return quCruHwAccountInfo;
    }

    /**
     * 取出时的汇旺账户信息
     */
    public void setQuCruHwAccountInfo(String quCruHwAccountInfo) {
        this.quCruHwAccountInfo = quCruHwAccountInfo;
    }

    /**
     * 订单状态(1:已提交 2:正在处理 3:已完成)
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态(1:已提交 2:正在处理 3:已完成)
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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
        TbUserCunQuManager other = (TbUserCunQuManager) that;
        return (this.getTgUserCunQuUuid() == null ? other.getTgUserCunQuUuid() == null : this.getTgUserCunQuUuid().equals(other.getTgUserCunQuUuid()))
            && (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
            && (this.getTgUserMsgId() == null ? other.getTgUserMsgId() == null : this.getTgUserMsgId().equals(other.getTgUserMsgId()))
            && (this.getKefuQunId() == null ? other.getKefuQunId() == null : this.getKefuQunId().equals(other.getKefuQunId()))
            && (this.getQunMsgId() == null ? other.getQunMsgId() == null : this.getQunMsgId().equals(other.getQunMsgId()))
            && (this.getBindId() == null ? other.getBindId() == null : this.getBindId().equals(other.getBindId()))
            && (this.getCunQuType() == null ? other.getCunQuType() == null : this.getCunQuType().equals(other.getCunQuType()))
            && (this.getCunAccountType() == null ? other.getCunAccountType() == null : this.getCunAccountType().equals(other.getCunAccountType()))
            && (this.getCunCruUsdtAccountInfo() == null ? other.getCunCruUsdtAccountInfo() == null : this.getCunCruUsdtAccountInfo().equals(other.getCunCruUsdtAccountInfo()))
            && (this.getCunCruHwAccountInfo() == null ? other.getCunCruHwAccountInfo() == null : this.getCunCruHwAccountInfo().equals(other.getCunCruHwAccountInfo()))
            && (this.getCunFileId() == null ? other.getCunFileId() == null : this.getCunFileId().equals(other.getCunFileId()))
            && (this.getQuAccountType() == null ? other.getQuAccountType() == null : this.getQuAccountType().equals(other.getQuAccountType()))
            && (this.getQuAmount() == null ? other.getQuAmount() == null : this.getQuAmount().equals(other.getQuAmount()))
            && (this.getQuCruUsdtAccountInfo() == null ? other.getQuCruUsdtAccountInfo() == null : this.getQuCruUsdtAccountInfo().equals(other.getQuCruUsdtAccountInfo()))
            && (this.getQuCruHwAccountInfo() == null ? other.getQuCruHwAccountInfo() == null : this.getQuCruHwAccountInfo().equals(other.getQuCruHwAccountInfo()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTgUserCunQuUuid() == null) ? 0 : getTgUserCunQuUuid().hashCode());
        result = prime * result + ((getTgUserId() == null) ? 0 : getTgUserId().hashCode());
        result = prime * result + ((getTgUserMsgId() == null) ? 0 : getTgUserMsgId().hashCode());
        result = prime * result + ((getKefuQunId() == null) ? 0 : getKefuQunId().hashCode());
        result = prime * result + ((getQunMsgId() == null) ? 0 : getQunMsgId().hashCode());
        result = prime * result + ((getBindId() == null) ? 0 : getBindId().hashCode());
        result = prime * result + ((getCunQuType() == null) ? 0 : getCunQuType().hashCode());
        result = prime * result + ((getCunAccountType() == null) ? 0 : getCunAccountType().hashCode());
        result = prime * result + ((getCunCruUsdtAccountInfo() == null) ? 0 : getCunCruUsdtAccountInfo().hashCode());
        result = prime * result + ((getCunCruHwAccountInfo() == null) ? 0 : getCunCruHwAccountInfo().hashCode());
        result = prime * result + ((getCunFileId() == null) ? 0 : getCunFileId().hashCode());
        result = prime * result + ((getQuAccountType() == null) ? 0 : getQuAccountType().hashCode());
        result = prime * result + ((getQuAmount() == null) ? 0 : getQuAmount().hashCode());
        result = prime * result + ((getQuCruUsdtAccountInfo() == null) ? 0 : getQuCruUsdtAccountInfo().hashCode());
        result = prime * result + ((getQuCruHwAccountInfo() == null) ? 0 : getQuCruHwAccountInfo().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
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
        sb.append(", tgUserCunQuUuid=").append(tgUserCunQuUuid);
        sb.append(", tgUserId=").append(tgUserId);
        sb.append(", tgUserMsgId=").append(tgUserMsgId);
        sb.append(", kefuQunId=").append(kefuQunId);
        sb.append(", qunMsgId=").append(qunMsgId);
        sb.append(", bindId=").append(bindId);
        sb.append(", cunQuType=").append(cunQuType);
        sb.append(", cunAccountType=").append(cunAccountType);
        sb.append(", cunCruUsdtAccountInfo=").append(cunCruUsdtAccountInfo);
        sb.append(", cunCruHwAccountInfo=").append(cunCruHwAccountInfo);
        sb.append(", cunFileId=").append(cunFileId);
        sb.append(", quAccountType=").append(quAccountType);
        sb.append(", quAmount=").append(quAmount);
        sb.append(", quCruUsdtAccountInfo=").append(quCruUsdtAccountInfo);
        sb.append(", quCruHwAccountInfo=").append(quCruHwAccountInfo);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}