package com.tg.bot.base.bot.service.dto;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量发送消息的响应对象
 */
@Data
public class BatchSendMsgDto {

    private List<SendMsgDto> sendMsgDtoList = new ArrayList<>();

    /**
     * 发送消息的响应对象
     */
    @Data
    public static class SendMsgDto{
        //消息返回对象
        private Message message;
        //是否发送成功
        private Boolean isSendSuccess = Boolean.FALSE;
    }

}
