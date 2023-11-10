package com.tg.base.tb.mapper;

import com.tg.base.tb.entity.TbScheduleJobManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.base.tb.entity.TbScheduleMsgManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【tb_schedule_job_manager(定时调度任务管理)】的数据库操作Mapper
* @createDate 2023-08-26 16:15:01
* @Entity com.tg.base.tb.entity.TbScheduleJobManager
*/
@Mapper
public interface TbScheduleJobManagerMapper extends BaseMapper<TbScheduleJobManager> {
    /**
     * 通过任务的启用状态查询任务
     * @param jobStatus   任务的状态
     * @return
     */
    List<TbScheduleJobManager> selectByJobStatus(@Param("jobStatus") int jobStatus);

    /**
     * 通过群Id查询调度任务
     * @param qunId
     * @return
     */
    TbScheduleJobManager selectScheduleJobByQunId(@Param("qunId") String qunId);

    /**
     * 通过调度消息的ID查询调度任务
     * @param scheduleMsgId   调度消息的ID
     * @return
     */
    TbScheduleJobManager selectByScheduleMsgId(@Param("scheduleMsgId") Integer scheduleMsgId);
}




