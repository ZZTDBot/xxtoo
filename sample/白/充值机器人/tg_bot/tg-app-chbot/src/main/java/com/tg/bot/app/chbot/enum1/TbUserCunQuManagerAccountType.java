package com.tg.bot.app.chbot.enum1;

/**
 * 账户类型
 */
public enum TbUserCunQuManagerAccountType {
    USDT(1,"USDT"),
    HW(2,"汇旺"),
    ;


    private int code;

    private String msg;

    TbUserCunQuManagerAccountType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "TbUserCunQuManagerCunQuType{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
