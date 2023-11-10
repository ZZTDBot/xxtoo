package com.tg.base.tb.mapper;

import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【tb_schedule_msg_manager(定时消息管理)】的数据库操作Mapper
* @createDate 2023-08-26 14:37:46
* @Entity com.tg.base.tb.entity.TbScheduleMsgManager
*/
@Mapper
public interface TbScheduleMsgManagerMapper extends BaseMapper<TbScheduleMsgManager> {

    /**
     * 通过创建人查询消息列表
     * @param userTgId      创建人ID
     * @return
     */
    List<TbScheduleMsgManager> selectListByUserTgId(@Param("userTgId") String userTgId);

    /**
     * 通过群Id查询消息对象
     * @param qunId
     * @return
     */
    TbScheduleMsgManager selectMsgByQunId(@Param("qunId") String qunId);

    /**
     * 通过群Id删除肖
     * @param qunId
     * @return
     */
    int deleteByQunId(@Param("qunId") String qunId);
}




