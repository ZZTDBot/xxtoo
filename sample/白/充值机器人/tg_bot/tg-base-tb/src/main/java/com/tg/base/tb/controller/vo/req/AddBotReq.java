package com.tg.base.tb.controller.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "添加机器人-参数类")
@Data
public class AddBotReq {

    @ApiModelProperty(required = true,value = "机器人机器人所属飞机用户ID")
    private String userTgId;

    @ApiModelProperty(required = true,value = "机器人用户名")
    private String botUserName;

    @ApiModelProperty(required = true,value = "机器人token")
    private String botToken;

    @ApiModelProperty(required = true,value = "机器人型号")
    private String botNo;

}
