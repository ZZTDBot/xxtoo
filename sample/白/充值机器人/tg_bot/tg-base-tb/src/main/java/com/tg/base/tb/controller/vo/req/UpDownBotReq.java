package com.tg.base.tb.controller.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "启动/停止-机器人-参数类")
@Data
public class UpDownBotReq {

    @ApiModelProperty(required = true,value = "机器人Id")
    private Integer tbBotTypeId;

    @ApiModelProperty(required = true,value = "机器人运行状态(1: 运行中 0:停止)")
    private Integer runStatus;

}
