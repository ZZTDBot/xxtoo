package com.tg.base.tb.mapper;

import com.tg.base.tb.entity.TbPriKeywordReplyManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【tb_pri_keyword_reply_manager(私聊关键词回复表)】的数据库操作Mapper
* @createDate 2023-08-17 17:45:56
* @Entity com.tg.test.entity.TbPriKeywordReplyManager
*/
@Mapper
public interface TbPriKeywordReplyManagerMapper extends BaseMapper<TbPriKeywordReplyManager> {

    /**
     * 通过机器人ID和关键词查询
     * @param tbBotInstanceId
     * @param key
     * @return
     */
    TbPriKeywordReplyManager selectByBotIdKeyReplyWord(@Param("botId") Integer tbBotInstanceId,@Param("key") String key);

    /**
     * 查看关键词回复列表
     * @param tbBotInstanceId  机器人ID
     * @return                 关键词回复列表
     */
    List<TbPriKeywordReplyManager> selectPriKeyReplyByBotId(@Param("botId") Integer tbBotInstanceId);

    /**
     * 删除机器人的关键词回复
     * @param tbBotInstanceId       机器人
     * @param key                   关键词
     * @return
     */
    int deleteTbPriKeyObjByBotIdAndKey(@Param("botId") Integer tbBotInstanceId,@Param("key") String key);
}




