package com.tg.bot.base.bot.enum1;

import java.util.HashMap;

/**
 * tg内联按钮的类型
 */
public enum TgInLineKeyBoardTypeEnum {
    LineKeyBoardType_DATA(1,"data"),
    LineKeyBoardType_URL(2,"url")
    ;

    private int id;
    private String type;

    TgInLineKeyBoardTypeEnum(int id, String type) {
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
