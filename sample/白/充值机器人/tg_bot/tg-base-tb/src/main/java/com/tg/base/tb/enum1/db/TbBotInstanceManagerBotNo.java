package com.tg.base.tb.enum1.db;


/**
 * 机器人实例型号枚举类
 */
public enum TbBotInstanceManagerBotNo {

    BOT_NO_A_01("A01","公共版机器人"),
    BOT_NO_A_02("A02","有记账人名称的机器人"),

//    BOT_NO_C_03("C01","传话机器人"),

    ;

    private String no;
    private String msg;

    TbBotInstanceManagerBotNo(String no, String msg) {
        this.no = no;
        this.msg = msg;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
