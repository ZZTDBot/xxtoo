package com.tg.base.tb.controller.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "删除机器人-参数类")
@Data
public class DeleteBotReq {

    @ApiModelProperty(required = true,value = "机器人Id")
    private Integer tbBotTypeId;

}
