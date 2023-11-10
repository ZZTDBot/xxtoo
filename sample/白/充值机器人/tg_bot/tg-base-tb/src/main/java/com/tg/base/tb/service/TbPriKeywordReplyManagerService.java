package com.tg.base.tb.service;

import com.tg.base.tb.entity.TbPriKeywordReplyManager;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @description 针对表【tb_pri_keyword_reply_manager(私聊关键词回复表)】的数据库操作Service
* @createDate 2023-08-17 17:45:56
*/
public interface TbPriKeywordReplyManagerService extends IService<TbPriKeywordReplyManager> {

    /**
     * 保存或更新私聊关键词回复
     *
     * @param tbBotInstanceId 机器人ID
     * @param key             关键词
     * @param replyWord       回复内容
     * @param replyMode       回复模式:(1:普通返回 2:回复模式)
     * @return                是否保存成功
     */
    Boolean saveOrUpdatePriKeyReply(Integer tbBotInstanceId, String key, String replyWord, int replyMode);


    /**
     * 查询私聊关键词回复
     *
     * @param tbBotInstanceId 机器人ID
     * @param key             关键词
     * @return                关键词回复对象
     */
    TbPriKeywordReplyManager selectTbPriKeyObjByBotidAndKey(Integer tbBotInstanceId, String key);

    /**
     * 查看关键词回复列表
     * @param tbBotInstanceId  机器人ID
     * @return                 关键词回复列表
     */
    List<TbPriKeywordReplyManager> listPriKeyReplyByBotId(Integer tbBotInstanceId);

    /**
     * 查看关键词回复列表
     * @param tbBotInstanceId  机器人ID
     * @param limit            关键词回复条数
     * @return                 关键词回复列表
     */
    String listPriKeyReplyStringByBotId(Integer tbBotInstanceId,int limit);

    /**
     * 删除机器人的关键词回复
     * @param tbBotInstanceId       机器人
     * @param key                   关键词
     * @return
     */
    int deletePriKeyReply(Integer tbBotInstanceId, String key);
}
