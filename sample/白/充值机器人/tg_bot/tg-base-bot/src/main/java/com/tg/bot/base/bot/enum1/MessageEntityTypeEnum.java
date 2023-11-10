package com.tg.bot.base.bot.enum1;

/**
 * 飞机艾特用户的账号类型
 */
public enum MessageEntityTypeEnum {

    TEXT_MENTION(1,"text_mention"), //无userName类型
    MENTION(2,"mention"), //有userName类型
    ;

    private int id;
    private String type;

    MessageEntityTypeEnum(int id,String type) {
        this.id = id;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
