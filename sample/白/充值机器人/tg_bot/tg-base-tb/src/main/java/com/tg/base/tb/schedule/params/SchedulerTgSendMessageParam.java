package com.tg.base.tb.schedule.params;

import lombok.Data;

/**
 * 定时调度参数类----定时发消息的参数类
 */
@Data
public class SchedulerTgSendMessageParam {
    /**
     * 机器人ID
     */
    private Integer scheduleMsgId;
}
