package com.tg.bot.base.bot.enum1;

import lombok.Getter;

/**
 * tg基本的异常码
 */
@Getter
public enum TgBaseErrorEnum {

    TG_API_SEND_MSG_FAILD("fj_10000000", "飞机发送消息api调用失败"),

    ;

    private String code;

    private String message;

    TgBaseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
