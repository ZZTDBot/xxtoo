package com.tg.base.tb.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前后端响应数据结构
 * @param <T>
 */
@ApiModel(value = "前后端响应数据结构")
public class JSONResult<T> {

    // 响应状态码
    @ApiModelProperty(value = "响应状态码")
    private String statusCode;

    //响应消息
    @ApiModelProperty(value = "响应消息")
    private String message;

    // 响应中的数据
    @ApiModelProperty(value = "响应中的数据")
    private T data;

    public JSONResult() {}

    public JSONResult(T data) {
        this.statusCode = "200";
        this.message = "OK";
        this.data = data;
    }

    public JSONResult(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> JSONResult build(String statusCode, String message, T data) {
        return new JSONResult(statusCode, message, data);
    }

    public static JSONResult ok() {
        return new JSONResult(null);
    }

    public static <T> JSONResult ok(T data) {
        return new JSONResult(data);
    }

    public static JSONResult error(String statusCode, String message) {
        return new JSONResult(statusCode, message, null);
    }

    public static JSONResult error(String message) {
        return new JSONResult("500", message, null);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
