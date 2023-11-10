package com.tg.base.tb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName tb_schedule_job_manager
 */
@TableName(value ="tb_schedule_job_manager")
public class TbScheduleJobManager implements Serializable {
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
     * 定时消息ID
     */
    @TableField(value = "schedule_msg_id")
    private Integer scheduleMsgId;

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
     * 任务时间表达式
     */
    @TableField(value = "job_cron")
    private String jobCron;

    /**
     * 任务全类名
     */
    @TableField(value = "job_class_path")
    private String jobClassPath;

    /**
     * 任务状态(1启用 0停用)
     */
    @TableField(value = "job_status")
    private Integer jobStatus;

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
        TbScheduleJobManager other = (TbScheduleJobManager) that;
        return (this.getScheduleJobId() == null ? other.getScheduleJobId() == null : this.getScheduleJobId().equals(other.getScheduleJobId()))
            && (this.getTgUserId() == null ? other.getTgUserId() == null : this.getTgUserId().equals(other.getTgUserId()))
            && (this.getScheduleMsgId() == null ? other.getScheduleMsgId() == null : this.getScheduleMsgId().equals(other.getScheduleMsgId()))
            && (this.getJobGroup() == null ? other.getJobGroup() == null : this.getJobGroup().equals(other.getJobGroup()))
            && (this.getJobName() == null ? other.getJobName() == null : this.getJobName().equals(other.getJobName()))
            && (this.getJobCron() == null ? other.getJobCron() == null : this.getJobCron().equals(other.getJobCron()))
            && (this.getJobClassPath() == null ? other.getJobClassPath() == null : this.getJobClassPath().equals(other.getJobClassPath()))
            && (this.getJobStatus() == null ? other.getJobStatus() == null : this.getJobStatus().equals(other.getJobStatus()))
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
        result = prime * result + ((getScheduleMsgId() == null) ? 0 : getScheduleMsgId().hashCode());
        result = prime * result + ((getJobGroup() == null) ? 0 : getJobGroup().hashCode());
        result = prime * result + ((getJobName() == null) ? 0 : getJobName().hashCode());
        result = prime * result + ((getJobCron() == null) ? 0 : getJobCron().hashCode());
        result = prime * result + ((getJobClassPath() == null) ? 0 : getJobClassPath().hashCode());
        result = prime * result + ((getJobStatus() == null) ? 0 : getJobStatus().hashCode());
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
        sb.append(", scheduleMsgId=").append(scheduleMsgId);
        sb.append(", jobGroup=").append(jobGroup);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobCron=").append(jobCron);
        sb.append(", jobClassPath=").append(jobClassPath);
        sb.append(", jobStatus=").append(jobStatus);
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