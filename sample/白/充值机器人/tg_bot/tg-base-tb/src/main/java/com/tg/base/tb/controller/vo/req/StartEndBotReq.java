package com.tg.base.tb.controller.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "启用/停用-机器人-参数类")
@Data
public class StartEndBotReq {

    @ApiModelProperty(required = true,value = "机器人Id")
    private Integer tbBotTypeId;

    @ApiModelProperty(required = true,value = "机器人启用状态(1: 启用 0:停用)")
    private Integer botQyStatus;

}
