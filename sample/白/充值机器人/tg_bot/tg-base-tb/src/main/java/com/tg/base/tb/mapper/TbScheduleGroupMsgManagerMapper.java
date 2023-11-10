package com.tg.base.tb.mapper;

import com.tg.base.tb.entity.TbScheduleGroupMsgManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【tb_schedule_group_msg_manager(定时群消息管理)】的数据库操作Mapper
* @createDate 2023-09-07 16:41:34
* @Entity com.tg.base.tb.entity.TbScheduleGroupMsgManager
*/
@Mapper
public interface TbScheduleGroupMsgManagerMapper extends BaseMapper<TbScheduleGroupMsgManager> {

    /**
     * 通过任务状态查询
     * @param jobStatus
     * @return
     */
    List<TbScheduleGroupMsgManager> selectByJobStatus(@Param("jobStatus") int jobStatus);

    /**
     * 更新定时消息的内容
     * @param scheduleId       消息ID
     * @param msg              消息内容
     * @return
     */
    int updateMsgById(@Param("scheduleId") int scheduleId,@Param("msg") String msg);

    /**
     * 查询调度任务信息---根据群ID查询
     * @param qunId        群ID
     * @return
     */
    List<TbScheduleGroupMsgManager> selectByQunId(@Param("qunId") String qunId);
}




