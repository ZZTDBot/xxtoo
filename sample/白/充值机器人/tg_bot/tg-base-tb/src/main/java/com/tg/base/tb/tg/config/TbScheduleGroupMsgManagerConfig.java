package com.tg.base.tb.tg.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 定时调度任务配置文件
 */
@Component
@Data
public class TbScheduleGroupMsgManagerConfig {

    /**
     * 最小的调度分钟周期
     */
    private Integer minJobMinute = 10;


    /**
     * 消息内容正则匹配器
     */
    private String msgZqRegx = "^创建群消息\n群ID:([\\-0-9]+)\n定时表达式:([0-9]{0,2})\n消息内容:((.|\\r\\n|\\n|\\r)+)";

    /**
     * 消息内容正则匹配器
     */
    private String msgZqRegx2 = "^创建群消息\n群ID:([\\-0-9]+)\n定时表达式:([0-9]{0,2})\\ ([0-9]{0,2})\\-([0-9]{0,2})\n消息内容:((.|\\r\\n|\\n|\\r)+)";

    /**
     * 查看群定时消息
     */
    private String queryMsgRegx = "^查看群定时消息\n群ID:([\\-0-9]+)$";

    /**
     * 删除群定时消息
     */
    private String delMsgRegx = "^删除群定时消息\n消息ID:([\\-0-9]+)$";

}
