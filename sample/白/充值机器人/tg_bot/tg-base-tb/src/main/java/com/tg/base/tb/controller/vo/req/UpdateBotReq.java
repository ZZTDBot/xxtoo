package com.tg.base.tb.controller.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "更新机器人-参数类")
@Data
public class UpdateBotReq {

    @ApiModelProperty(required = true,value = "机器人Id")
    private Integer tbBotTypeId;

    @ApiModelProperty(required = true,value = "机器人用户名")
    private String botUserName;

    @ApiModelProperty(required = true,value = "机器人token")
    private String botToken;

    @ApiModelProperty(required = true,value = "机器人型号")
    private String botNo;

    @ApiModelProperty(required = false,value = "机器人描述")
    private String botRemark;

}
