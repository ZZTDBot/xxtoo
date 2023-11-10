package com.tg.base.tb.exception.enum1;

import lombok.Getter;

/**
 * 基本的异常码
 */
@Getter
public enum BaseErrorEnum {


    DATABASE_ERROR("10000000", "数据库异常"),
    REQUEST_PARAM_ERROR("10000001", "请求参数错误"),
    TYPE_CONVERSION_EXCEPTION("10000002", "类型转换异常"),
    PRODUCE_TOKEN_ERROR("10000003", "生成token异常"),
    TOKEN_ERROR_NOT_NULL("10000004", "未登录,请登录!!!"),

    ;

    private String code;

    private String message;

    BaseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
