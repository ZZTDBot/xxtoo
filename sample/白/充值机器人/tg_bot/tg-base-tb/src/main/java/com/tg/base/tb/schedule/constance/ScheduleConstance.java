package com.tg.base.tb.schedule.constance;

/**
 * 调度模块的常量管理类
 */
public interface ScheduleConstance {
    /**
     * 调度任务传递json参数的key
     */
    public static String scheduleJobParamKey = "json_params";


    /**
     * 调度类的全类名
     */
    public static String scheduleJobClassFullName = "com.tg.base.tb.schedule.job.SchedulerTgSendMessageJob";
    public static String scheduleGroupMsgClassFullName = "com.tg.base.tb.schedule.job.SchedulerTgBotSendMessageJob";
}
