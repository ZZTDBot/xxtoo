package com.tg.base.tb.service;

import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @description 针对表【tb_schedule_msg_manager(定时消息管理)】的数据库操作Service
* @createDate 2023-08-26 14:37:46
*/
public interface TbScheduleMsgManagerService extends IService<TbScheduleMsgManager> {


    /**
     * 保存待发送的消息
     * @param userTgId             创建消息的人ID
     * @param tbBotInstanceId      机器人ID
     * @param targChatId           会话ID
     * @param targMsg              消息内容
     * @return
     */
    int saveMsg(Long userTgId,Integer tbBotInstanceId, String targChatId, String targMsg);

    /**
     * 保存待发送的消息
     * @param userTgId             创建消息的人ID
     * @param tbBotInstanceId      机器人ID
     * @param targChatId           会话ID
     * @param targMsg              消息内容
     * @return
     */
    int saveOrUpdateMsgByQun(Long userTgId,Integer tbBotInstanceId, String targChatId, String targMsg);

    /**
     * 查询消息列表
     * @param userTgId             创建消息的人ID
     * @return
     */
    String queryListContextByUserTgId(Long userTgId);

    /**
     * 通过群ID查询消息
     * @param qunId   群ID
     * @return
     */
    String queryMsgContextByQunId(String qunId);

    /**
     * 获取修改消息的模板
     * @param qunId
     * @return
     */
    String queryUpdateMsgContextByQunId(String qunId);

    /**
     * 通过群Id删除肖
     * @param qunId   群ID
     * @return
     */
    int delByQunId(String qunId);

    /**
     * 通过群ID查询消息
     * @param qunId
     * @return
     */
    TbScheduleMsgManager queryMsgByQunId(String qunId);
}
