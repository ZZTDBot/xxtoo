package com.tg.base.tb.service;

import com.tg.base.tb.controller.BotManagerController;
import com.tg.base.tb.entity.TbScheduleGroupMsgManager;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

import java.util.List;

/**
* @description 针对表【tb_schedule_group_msg_manager(定时群消息管理)】的数据库操作Service
* @createDate 2023-09-07 16:41:35
*/
public interface TbScheduleGroupMsgManagerService extends IService<TbScheduleGroupMsgManager> {

    /**
     * 查询启用的定时任务
     * @return
     */
    List<TbScheduleGroupMsgManager> selectQiYongJob();

    /**
     * 添加任务
     * @param userTgId    用户ID
     * @param qunId       群ID
     * @param botId       机器人ID
     *
     * @param msg         消息内容
     * @param jobStatus   任务状态
     * @param cron        表达式
     * @param userCron    用户输入的表达式
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public int addScheduleJob(
            String userTgId,
            String qunId,
            int botId,
            String msg,
            int jobStatus,
            String cron,
            String userCron
    ) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    /**
     * 批量查询任务
     * @param qunId   群ID
     * @return
     */
    public String queryQunScheduleMsgContext(String qunId);

    /**
     * 修改任务
     */
    public int updateQunScheduleMsg(int scheduleId,String msg);

    /**
     * 删除任务
     */
    public int delQunScheduleMsg(int scheduleId) throws SchedulerException;

    /**
     * 停用任务
     */
    public Boolean stopQunScheduleMsg(int scheduleId) throws SchedulerException;

    /**
     * 启用任务
     */
    public Boolean startQunScheduleMsg(int scheduleId) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException;

}
