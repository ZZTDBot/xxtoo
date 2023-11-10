package com.tg.base.tb.exception.enum1;

/**
 * 调度模块异常类
 */
public enum TbScheduleJobExceptionEnum {
    NOT_FOUNT_SCHEDULE_JOB("推送时间未设置,请先设置者推送时间。"),
    NOT_FOUNT_SCHEDULE_MSG("群消息内容未创建,请先创建群消息内容。")
    ;
    private String msg;

    TbScheduleJobExceptionEnum(String msg){
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
