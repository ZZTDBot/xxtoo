package com.tg.base.tb.tg.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 定时调度消息配置文件
 */
@Component
@Data
public class TbScheduleMsgManagerConfig {

    /**
     * 创建消息模板的命令
     */
    private String createMsgRegx = "^创建群消息$";

    /**
     * 消息内容正则匹配器
     */
    private String msgZqRegx = "^创建群消息\n群ID:([\\-0-9]+)\\n消息内容:(.+)";

    /**
     * 查询消息
     */
    private String queryMsgRegx = "^查看消息列表";

    /**
     * 删除消息
     */
    private String delMsgRegx = "^删除消息\n" +
            "消息ID:([0-9]+)";

    /**
     * 修改消息
     */
    private String upMsgRegx = "^修改群消息\n" +
            "消息ID:([0-9]+)\n" +
            "群ID:([\\-0-9]+)\n" +
            "消息内容:(.+)";

}
