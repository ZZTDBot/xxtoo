package com.tg.base.tb.service.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 机器人实例查询条件对象
 */
@Data
@ApiModel(value = "机器人列表查询请求对象",description = "机器人列表查询请求对象")
public class TbBotInstanceManagerReqDto {
    
    @ApiModelProperty(required = false,value = "机器人ID")
    private Integer botTypeId;

    @ApiModelProperty(required = false,value = "机器人用户名")
    private String botUserName;

    @ApiModelProperty(required = false,value = "机器人token")
    private String botToken;

    @ApiModelProperty(required = false,value = "机器人型号")
    private String botNo;

    @ApiModelProperty(required = false,value = "机器人启用状态(1: 启用  0:停用)")
    private Integer botQyStatus;

    @ApiModelProperty(required = false,value = "机器人运行状态(1: 运行中 0:停止)")
    private Integer runStatus;

    @ApiModelProperty(required = false,value = "起始创建时间")
    private Date startCreateTime;

    @ApiModelProperty(required = false,value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(required = true,value = "当前页")
    private int cruPage = 1;

    @ApiModelProperty(required = true,value = "页大小")
    private int pageSize = 10;

}
