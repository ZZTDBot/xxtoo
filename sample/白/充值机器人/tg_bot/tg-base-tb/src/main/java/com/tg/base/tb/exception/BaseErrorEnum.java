package com.tg.base.tb.exception;

import lombok.Getter;

/**
 * 基本的异常码
 */
@Getter
public enum BaseErrorEnum {


    BOTMANAGER_TBBOTTYPEID_NOT_NULL("botmanager_10000000", "机器人ID不能为空"),
    BOTMANAGER_USERNAME_NOT_NULL("botmanager_10000000", "机器人用户名不能为空"),
    BOTMANAGER_TOKEN_NOT_NULL("botmanager_10000001", "机器人TOKEN不能为空"),
    BOTMANAGER_BOTNO_NOT_NULL("botmanager_10000002", "机器人型号不能为空"),
    BOTMANAGER_BOTQYSTATUS_NOT_NULL("botmanager_10000003", "机器人启用停用状态不能为空"),
    BOTMANAGER_BOTRUNNING_NOT_STOP("botmanager_10000004", "机器人状态不是未运行状态"),

    BOTMANAGER_USERTGID_NOT_NULL("botmanager_10000005", "机器人所属的用户飞机ID不能为空"),

    BOTMANAGER_CREATE_NOT_NULL("botmanager_10000006", "机器人创建人未找到"),
    BOTMANAGER_GS_NOT_NULL("botmanager_10000006", "机器人归属人未找到"),

    BOTMANAGER_BOT_TOKEN_NOT_CF("botmanager_10000007", "机器人的token已经存在"),
    BOTMANAGER_BOT_USERNAME_NOT_CF("botmanager_10000008", "机器人的用户名已经存在"),

    BOTMANAGER_BOT_USERNAME_NOT_EXIST("botmanager_10000009", "机器人的用户名不存在"),

    NOT_BOT_ADMIN_ERROR("tb_bot_admin_manager-10000006", "不是机器人管理员，不能操作"),
    EXIST_BOT_ADMIN_ERROR("tb_bot_admin_manager-10000007", "当前添加的用户已经是管理员!!!"),
    ;

    private String code;

    private String message;

    BaseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
