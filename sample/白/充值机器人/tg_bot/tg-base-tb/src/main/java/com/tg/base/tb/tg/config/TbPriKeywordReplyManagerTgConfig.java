package com.tg.base.tb.tg.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 关键词回复的相关飞机配置类
 */
@Data
@Component
public class TbPriKeywordReplyManagerTgConfig {

    /**
     * 关键词回复消息创建的正则表达式
     * group(1) = 关键词
     * group(2) = 回复内容
     *
     * 创建关键词回复
     * 关键词:哈哈哈
     * 回复模式:(1:普通返回 2:回复模式)
     * 回复:呵呵
     */
    private String createPriKeyReplyRegx = "^创建关键词回复\\n关键词:(.+)\\n回复模式:([1-9])\\n回复:((.|\\r\\n|\\n|\\r)+)";


    /**
     * 关键词回复消息--查看关键词列表--正则表达式
     * group(1) = 查看关键词列表
     *
     * 查看关键词列表
     */
    private String listPriKeyReplyRegx = "^查看关键词列表";

    /**
     * 关键词回复消息--查看关键词列表--正则表达式
     * group(1) = 查看关键词列表
     *
     * 查看关键词列表
     */
    private String deletePriKeyReplyRegx = "^删除关键词回复\\n关键词:(.+)";

    //展示最近创建的私聊关键词回复个数
    private int listPriKeyReplyLimit = 10;

}
