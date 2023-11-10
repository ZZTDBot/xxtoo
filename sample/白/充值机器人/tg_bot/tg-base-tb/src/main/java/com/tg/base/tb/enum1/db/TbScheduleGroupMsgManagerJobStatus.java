package com.tg.base.tb.enum1.db;


/**
 * 机器人实例运行状态枚举类
 */
public enum TbScheduleGroupMsgManagerJobStatus {

    RUNNING(1,"启用"),
    STOP(0,"停用"),
    ;

    private int id;
    private String msg;

    TbScheduleGroupMsgManagerJobStatus(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
