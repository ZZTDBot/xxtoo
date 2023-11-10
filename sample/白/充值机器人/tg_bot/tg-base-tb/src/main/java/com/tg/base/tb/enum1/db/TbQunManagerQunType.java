package com.tg.base.tb.enum1.db;

/**
 * 群类型枚举类
 */
public enum TbQunManagerQunType {

    QUN_GROUP(1,"group","私有群"),
    QUN_SUPER_GROUP(2,"supergroup","公开群"),
    QUN_PRIVATE(3,"private","私聊对话框"),
    QUN_CHANNEL(4,"channel","频道");

    private Integer id;

    private String tgQunType;

    private String remark;


    TbQunManagerQunType(Integer id, String tgQunType, String remark) {
        this.id = id;
        this.tgQunType = tgQunType;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTgQunType() {
        return tgQunType;
    }

    public void setTgQunType(String tgQunType) {
        this.tgQunType = tgQunType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public String toString() {
        return "TbQunManagerQunType{" +
                "id=" + id +
                ", tgQunType='" + tgQunType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
