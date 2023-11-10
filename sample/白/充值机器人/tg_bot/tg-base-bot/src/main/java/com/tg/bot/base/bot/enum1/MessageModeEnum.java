package com.tg.bot.base.bot.enum1;

import java.util.HashMap;

/**
 * 消息类型
 */
public enum MessageModeEnum {
    HTML(1,"HTML"),
    ;

    private int id;
    private String type;

    MessageModeEnum(int id, String type) {
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
