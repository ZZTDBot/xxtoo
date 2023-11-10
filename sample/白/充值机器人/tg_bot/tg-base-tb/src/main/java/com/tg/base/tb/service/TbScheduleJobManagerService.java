package com.tg.base.tb.service;

import com.tg.base.tb.entity.TbScheduleJobManager;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.tg.base.tb.exception.BaseException;
import org.quartz.SchedulerException;

import java.util.List;

/**
* @description 针对表【tb_schedule_job_manager(定时调度任务管理)】的数据库操作Service
* @createDate 2023-08-26 16:15:01
*/
public interface TbScheduleJobManagerService extends IService<TbScheduleJobManager> {
    /**
     * 查询启用的所有任务
     * @return
     */
    List<TbScheduleJobManager> selectQiYongJob();

    /**
     * 查询指定群是否有启用的调度任务
     * @param chatId   群ID
     * @return
     */
    Boolean haveQyScheduleJobByQunId(Long chatId);

    /**
     * 查询指定群是否有调度任务
     * @param chatId   群ID
     * @return
     */
    Boolean haveScheduleJobByQunId(String chatId);

    /**
     * 反转指定群的调度状态
     * @param qunId      群Id
     * @return
     */
    Boolean revertQyStatus(String qunId) throws BaseException, SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    /**
     * 保存或更新调度任务
     * @param userTgId         用户ID
     * @param scheduleMsgId    调度肖
     * @param cron             调度表达式
     * @return
     */
    Boolean saveOrUpdateScheduleJob(Long userTgId, String scheduleMsgId,String cron) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    /**
     * 关闭指定群的调度任务
     * @param qunId
     * @return
     */
    int closeScheduleJobByQunId(String qunId) throws SchedulerException;
}
