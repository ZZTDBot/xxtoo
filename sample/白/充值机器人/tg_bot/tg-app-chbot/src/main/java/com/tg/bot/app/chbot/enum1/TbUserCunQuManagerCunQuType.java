package com.tg.bot.app.chbot.enum1;

public enum TbUserCunQuManagerCunQuType {
    CUN(1,"存"),
    QU(2,"取"),
    ;


    private int code;

    private String msg;

    TbUserCunQuManagerCunQuType(int code,String msg){
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
