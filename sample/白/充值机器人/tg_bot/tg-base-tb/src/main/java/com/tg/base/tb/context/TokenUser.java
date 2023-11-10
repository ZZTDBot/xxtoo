package com.tg.base.tb.context;

import lombok.Data;

@Data
public class TokenUser {

    //用户的飞机ID
    private Long tgUserId;
    //机器人ID
    private Integer botInstanceId;


}
