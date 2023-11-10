package com.tg.base.tb.enum1.db;


/**
 * 机器人实例型号枚举类
 */
public enum TbUserBotinstanceQunManagerBotRole {

    QUN_ZHU(1,"qunzhu","群主"),
    administrator(2,"administrator","管理员"),
    member(3,"member","成员"),
    LEFT(4,"left","离开群"),


    ;

    private Integer id;

    private String msg;

    private String remark;


    TbUserBotinstanceQunManagerBotRole(Integer id, String msg, String remark) {
        this.id = id;
        this.msg = msg;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
