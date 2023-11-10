package com.game.qs.bot.tgresponse;

import lombok.Data;

/**
 * 获取群成员个数
 */
@Data
public class GetChatMemberCount {

    private Boolean ok;
    private Long result;

}
