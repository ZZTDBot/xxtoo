package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 私聊关键词回复表
 * @TableName tb_pri_keyword_reply_manager
 */
@TableName(value ="tb_pri_keyword_reply_manager")
public class TbPriKeywordReplyManager implements Serializable {
    /**
     * 群内关键词主键
     */
    @TableId(value = "tb_pri_keyword_id", type = IdType.AUTO)
    private Integer tbPriKeywordId;

    /**
     * 机器人实例ID
     */
    @TableField(value = "tb_bot_instance_id")
    private Integer tbBotInstanceId;

    /**
     * 关键词
     */
    @TableField(value = "key_word")
    private String keyWord;

    /**
     * 回复内容
     */
    @TableField(value = "reply_context")
    private String replyContext;

    /**
     * 回复模式(1:普通返回 2:回复模式)
     */
    @TableField(value = "reply_mode")
    private Integer replyMode;

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
     * 群内关键词主键
     */
    public Integer getTbPriKeywordId() {
        return tbPriKeywordId;
    }

    /**
     * 群内关键词主键
     */
    public void setTbPriKeywordId(Integer tbPriKeywordId) {
        this.tbPriKeywordId = tbPriKeywordId;
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
     * 关键词
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 关键词
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * 回复内容
     */
    public String getReplyContext() {
        return replyContext;
    }

    /**
     * 回复内容
     */
    public void setReplyContext(String replyContext) {
        this.replyContext = replyContext;
    }

    /**
     * 回复模式(1:普通返回 2:回复模式)
     */
    public Integer getReplyMode() {
        return replyMode;
    }

    /**
     * 回复模式(1:普通返回 2:回复模式)
     */
    public void setReplyMode(Integer replyMode) {
        this.replyMode = replyMode;
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
        TbPriKeywordReplyManager other = (TbPriKeywordReplyManager) that;
        return (this.getTbPriKeywordId() == null ? other.getTbPriKeywordId() == null : this.getTbPriKeywordId().equals(other.getTbPriKeywordId()))
            && (this.getTbBotInstanceId() == null ? other.getTbBotInstanceId() == null : this.getTbBotInstanceId().equals(other.getTbBotInstanceId()))
            && (this.getKeyWord() == null ? other.getKeyWord() == null : this.getKeyWord().equals(other.getKeyWord()))
            && (this.getReplyContext() == null ? other.getReplyContext() == null : this.getReplyContext().equals(other.getReplyContext()))
            && (this.getReplyMode() == null ? other.getReplyMode() == null : this.getReplyMode().equals(other.getReplyMode()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTbPriKeywordId() == null) ? 0 : getTbPriKeywordId().hashCode());
        result = prime * result + ((getTbBotInstanceId() == null) ? 0 : getTbBotInstanceId().hashCode());
        result = prime * result + ((getKeyWord() == null) ? 0 : getKeyWord().hashCode());
        result = prime * result + ((getReplyContext() == null) ? 0 : getReplyContext().hashCode());
        result = prime * result + ((getReplyMode() == null) ? 0 : getReplyMode().hashCode());
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
        sb.append(", tbPriKeywordId=").append(tbPriKeywordId);
        sb.append(", tbBotInstanceId=").append(tbBotInstanceId);
        sb.append(", keyWord=").append(keyWord);
        sb.append(", replyContext=").append(replyContext);
        sb.append(", replyMode=").append(replyMode);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}