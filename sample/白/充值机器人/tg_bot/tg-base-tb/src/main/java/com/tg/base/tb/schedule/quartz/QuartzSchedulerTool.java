package com.tg.base.tb.schedule.quartz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tg.base.tb.schedule.constance.ScheduleConstance;
import com.tg.base.tb.schedule.job.SchedulerQuartzJob;
import com.tg.base.tb.schedule.job.SchedulerTgSendMessageJob;
import com.tg.base.tb.schedule.params.SchedulerTgSendMessageParam;
import com.tg.base.tb.utils.JsonUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @ClassName: QuartzScheduler
 * @Description: 任务调度处理
 */
@Configuration
@Component
public class QuartzSchedulerTool {

    @Autowired
    private Scheduler scheduler;


    @PostConstruct
    public void startJob() throws SchedulerException, JsonProcessingException {
//        startJobOne(scheduler);
//        startJobTwo(scheduler);
//        startJobThree(scheduler,2);
        scheduler.start();
    }


    /**
     * 获取job信息
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }


    /**
     * 修改某个任务的执行时间(暂时不能理解)
     * @param name
     * @param group
     * @param time
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }


    /**
     * 暂停某个任务
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }


    /**
     * 恢复所有任务
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }


    /**
     * 恢复某个任务
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.resumeJob(jobKey);
    }


    /**
     * 删除某个任务
     * @param name
     * @param group
     */
    public void deleteJob(String name, String group) throws SchedulerException{
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

    /**
     * 添加一个调度任务
     * @param jobClassName       任务全类名称
     * @param jobName            任务名称
     * @param jobGroupName       任务组名称
     * @param cronExpression     调度表达式
     * @param scheduleMsgId      调度消息的ID
     */
    public void addJob(String jobClassName,String jobName,
                       String jobGroupName, String cronExpression,Integer scheduleMsgId) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        /** 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例 */
        Class<Job> aClass = (Class<Job>) Class.forName(jobClassName);
        JobDetail jobDetail = JobBuilder.newJob(aClass).withIdentity(jobName, jobGroupName).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(ScheduleConstance.scheduleJobParamKey,scheduleMsgId);
        /** 基于CronTrigger表达式构建触发器 */
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        /** CronTrigger表达式触发器 继承于Trigger、TriggerBuilder 用于构建触发器实例 */
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(cronScheduleBuilder).build();
        /** 调度 */
        scheduler.scheduleJob(jobDetail ,cronTrigger);
    }


    /**
     * 使用CronTrigger  通过 cron表达式 定义时间规则的调度方案
     * @param scheduler
     * @throws SchedulerException
     */
    private void startJobOne(Scheduler scheduler) throws SchedulerException {
        /** 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例 */
        JobDetail jobDetail = JobBuilder.newJob(SchedulerQuartzJob.class).withIdentity("jobOne", "groupOne").build();
        /** 基于CronTrigger表达式构建触发器 */
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 */3 * * * ?");
        /** CronTrigger表达式触发器 继承于Trigger、TriggerBuilder 用于构建触发器实例 */
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("jobOne", "groupOne").withSchedule(cronScheduleBuilder).build();
        /** 调度 */
        scheduler.scheduleJob(jobDetail ,cronTrigger);
    }


    /**
     * SimpleTrigger 当仅需要触发一次，或者以固定时间间隔周期执行时，最合适
     * @param scheduler
     * @throws SchedulerException
     */
    private void startJobTwo(Scheduler scheduler) throws SchedulerException {
        /** 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例 */
        JobDetail jobDetail = JobBuilder.newJob(SchedulerQuartzJob.class).withIdentity("jobTwo", "groupTwo").build();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        jobDataMap.put("chatId", "6009607949");
        jobDataMap.put("botId","2");
        jobDataMap.put("context","测试消息");

        /** 基于SimpleTrigger表达式构建触发器 */
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10) /** 2分钟执行一次 */
                .repeatForever()
//                .withRepeatCount(1) /** 执行一次   repeatForever() 不限次数 */
                ;
        /** SimpleTrigger表达式触发器 继承于Trigger、TriggerBuilder 用于构建触发器实例 */
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("jobTwo", "groupTwo")
                .withSchedule(simpleScheduleBuilder).build();
        /** 调度 */
        scheduler.scheduleJob(jobDetail ,simpleTrigger);
    }


    /**
     * SimpleTrigger 当仅需要触发一次，或者以固定时间间隔周期执行时，最合适
     * @param scheduler
     * @throws SchedulerException
     */
    private void startJobThree(Scheduler scheduler,int scheduleMsgId) throws SchedulerException, JsonProcessingException {

        /** 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例 */
        JobDetail jobDetail = JobBuilder.newJob(SchedulerTgSendMessageJob.class).withIdentity("jobThree", "groupThree").build();

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        SchedulerTgSendMessageParam schedulerTgSendMessageParam = new SchedulerTgSendMessageParam();
        schedulerTgSendMessageParam.setScheduleMsgId(scheduleMsgId);
        String writeValueAsString = JsonUtil.objectMapper.writeValueAsString(schedulerTgSendMessageParam);
        jobDataMap.put(ScheduleConstance.scheduleJobParamKey, writeValueAsString);

//        jobDataMap.put("chatId", "6009607949");
//        jobDataMap.put("botId","2");
//        jobDataMap.put("context","测试消息");

        /** 基于SimpleTrigger表达式构建触发器 */
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10) /** 2分钟执行一次 */
                .repeatForever()
//                .withRepeatCount(1) /** 执行一次   repeatForever() 不限次数 */
                ;
        /** SimpleTrigger表达式触发器 继承于Trigger、TriggerBuilder 用于构建触发器实例 */
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("jobThree", "groupThree")
                .withSchedule(simpleScheduleBuilder).build();
        /** 调度 */
        scheduler.scheduleJob(jobDetail ,simpleTrigger);
    }


}
