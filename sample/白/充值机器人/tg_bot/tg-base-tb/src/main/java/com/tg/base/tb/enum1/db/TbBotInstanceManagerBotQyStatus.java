package com.tg.base.tb.enum1.db;


/**
 * 机器人实例是否启用枚举类
 */
public enum TbBotInstanceManagerBotQyStatus {

    QI_YONG(1,"启用"),
    TING_YONG(0,"停用"),
    ;

    private int id;
    private String msg;

    TbBotInstanceManagerBotQyStatus(int id, String msg) {
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
