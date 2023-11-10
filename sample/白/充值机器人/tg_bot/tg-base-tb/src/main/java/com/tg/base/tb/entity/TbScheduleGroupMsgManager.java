package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时群消息管理
 * @TableName tb_schedule_group_msg_manager
 */
@TableName(value ="tb_schedule_group_msg_manager")
public class TbScheduleGroupMsgManager implements Serializable {
    /**
     * 定时调度任务ID
     */
    @TableId(value = "schedule_job_id", type = IdType.AUTO)
    private Integer scheduleJobId;

    /**
     * 用户tg账号
     */
    @TableField(value = "tg_user_id")
    private String tgUserId;

    /**
     * tg群ID
     */
    @TableField(value = "tg_qun_id")
    private String tgQunId;

    /**
     * 机器人实例ID
     */
    @TableField(value = "tb_bot_instance_id")
    private Integer tbBotInstanceId;

    /**
     * 消息类容
     */
    @TableField(value = "msg_context")
    private String msgContext;

    /**
     * 任务状态(1启用 0停用)
     */
    @TableField(value = "job_status")
    private Integer jobStatus;

    /**
     * 任务时间表达式
     */
    @TableField(value = "job_cron")
    private String jobCron;

    /**
     * 用户定时表达式
     */
    @TableField(value = "user_cron")
    private String userCron;

    /**
     * 任务组
     */
    @TableField(value = "job_group")
    private String jobGroup;

    /**
     * 任务名称
     */
    @TableField(value = "job_name")
    private String jobName;

    /**
     * 任务全类名
     */
    @TableField(value = "job_class_path")
    private String jobClassPath;

    /**
     * 任务功能描述
     */
    @TableField(value = "job_describe")
    private String jobDescribe;

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
     * 定时调度任务ID
     */
    public Integer getScheduleJobId() {
        return scheduleJobId;
    }

    /**
     * 定时调度任务ID
     */
    public void setScheduleJobId(Integer scheduleJobId) {
        this.scheduleJobId = scheduleJobId;
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
     * 消息类容
     */
    public String getMsgContext() {
        return msgContext;
    }

    /**
     * 消息类容
     */
    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext;
    }

    /**
     * 任务状态(1启用 0停用)
     */
    public Integer getJobStatus() {
        return jobStatus;
    }

    /**
     * 任务状态(1启用 0停用)
     */
    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 任务时间表达式
     */
    public String getJobCron() {
        return jobCron;
    }

    /**
     * 任务时间表达式
     */
    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    /**
     * 用户定时表达式
     */
    public String getUserCron() {
        return userCron;
    }

    /**
     * 用户定时表达式
     */
    public void setUserCron(String userCron) {
        this.userCron = userCron;
    }

    /**
     * 任务组
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 任务组
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 任务全类名
     */
    public String getJobClassPath() {
        return jobClassPath;
    }

    /**
     * 任务全类名
     */
    public void setJobClassPath(String jobClassPath) {
        this.jobClassPath = jobClassPath;
    }

    /**
     * 任务功能描述
     */
    public String getJobDescribe() {
        return jobDescribe;
    }

    /**
     * 任务功能描述
     */
    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
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
        TbScheduleGroupMsgManager other = (TbScheduleGroupMsgManager) that;
        return (this.getScheduleJobId() == null ? other.getScheduleJobId() == null : this.getScheduleJobId().equals(other.getScheduleJobId()))
                && (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
                && (this.getTgQunId() == null ? other.getTgQunId() == null : this.getTgQunId().equals(other.getTgQunId()))
                && (this.getTbBotInstanceId() == null ? other.getTbBotInstanceId() == null : this.getTbBotInstanceId().equals(other.getTbBotInstanceId()))
                && (this.getMsgContext() == null ? other.getMsgContext() == null : this.getMsgContext().equals(other.getMsgContext()))
                && (this.getJobStatus() == null ? other.getJobStatus() == null : this.getJobStatus().equals(other.getJobStatus()))
                && (this.getJobCron() == null ? other.getJobCron() == null : this.getJobCron().equals(other.getJobCron()))
                && (this.getUserCron() == null ? other.getUserCron() == null : this.getUserCron().equals(other.getUserCron()))
                && (this.getJobGroup() == null ? other.getJobGroup() == null : this.getJobGroup().equals(other.getJobGroup()))
                && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
                && (this.getJobClassPath() == null ? other.getJobClassPath() == null : this.getJobClassPath().equals(other.getJobClassPath()))
                && (this.getJobDescribe() == null ? other.getJobDescribe() == null : this.getJobDescribe().equals(other.getJobDescribe()))
                && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getScheduleJobId() == null) ? 0 : getScheduleJobId().hashCode());
        result = prime * result + ((getTgUserId() == null) ? 0 : getTgUserId().hashCode());
        result = prime * result + ((getTgQunId() == null) ? 0 : getTgQunId().hashCode());
        result = prime * result + ((getTbBotInstanceId() == null) ? 0 : getTbBotInstanceId().hashCode());
        result = prime * result + ((getMsgContext() == null) ? 0 : getMsgContext().hashCode());
        result = prime * result + ((getJobStatus() == null) ? 0 : getJobStatus().hashCode());
        result = prime * result + ((getJobCron() == null) ? 0 : getJobCron().hashCode());
        result = prime * result + ((getUserCron() == null) ? 0 : getUserCron().hashCode());
        result = prime * result + ((getJobGroup() == null) ? 0 : getJobGroup().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getJobClassPath() == null) ? 0 : getJobClassPath().hashCode());
        result = prime * result + ((getJobDescribe() == null) ? 0 : getJobDescribe().hashCode());
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
        sb.append(", scheduleJobId=").append(scheduleJobId);
        sb.append(", tgUserId=").append(tgUserId);
        sb.append(", tgQunId=").append(tgQunId);
        sb.append(", tbBotInstanceId=").append(tbBotInstanceId);
        sb.append(", msgContext=").append(msgContext);
        sb.append(", jobStatus=").append(jobStatus);
        sb.append(", jobCron=").append(jobCron);
        sb.append(", userCron=").append(userCron);
        sb.append(", jobGroup=").append(jobGroup);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobClassPath=").append(jobClassPath);
        sb.append(", jobDescribe=").append(jobDescribe);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}