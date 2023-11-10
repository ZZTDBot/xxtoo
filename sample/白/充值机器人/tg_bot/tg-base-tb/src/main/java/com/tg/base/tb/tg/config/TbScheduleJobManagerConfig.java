package com.tg.base.tb.tg.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 定时调度任务配置文件
 */
@Component
@Data
public class TbScheduleJobManagerConfig {

    /**
     * 最小的调度分钟周期
     */
    private Integer minJobMinute = 10;
    /**
     * 分钟间隔任务正则匹配器
     */
    private String jobSetTimeRegx = "^创建定时任务\n消息ID:([0-9]+)\n定时表达式:([0-9]{0,2})$";

    /**
     * 时间段-分钟间隔任务正则匹配器
     */
    private String jobSetHourTimeRegx = "^创建定时任务\n消息ID:([0-9]+)\n定时表达式:([0-9]{0,2})\\ ([0-9]{0,2})\\-([0-9]{0,2})$";

}
