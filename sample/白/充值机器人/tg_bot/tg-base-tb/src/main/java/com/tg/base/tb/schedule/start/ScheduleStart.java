package com.tg.base.tb.schedule.start;

import com.tg.base.tb.entity.TbScheduleGroupMsgManager;
import com.tg.base.tb.entity.TbScheduleJobManager;
import com.tg.base.tb.schedule.quartz.QuartzSchedulerTool;
import com.tg.base.tb.service.TbScheduleGroupMsgManagerService;
import com.tg.base.tb.service.TbScheduleJobManagerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.core.QuartzScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 调度任务的入口
 */
@Slf4j
@Component
@Configuration
public class ScheduleStart {

    @Autowired
    TbScheduleJobManagerService tbScheduleJobManagerService;

    @Autowired
    TbScheduleGroupMsgManagerService tbScheduleGroupMsgManagerService;

    @Autowired
    QuartzSchedulerTool quartzSchedulerTool;


    @PostConstruct
    public void init(){
        try {
            List<TbScheduleJobManager> list = tbScheduleJobManagerService.selectQiYongJob();
            for (TbScheduleJobManager tbScheduleJobManager : list){
                    quartzSchedulerTool.addJob(
                            tbScheduleJobManager.getJobClassPath(),
                            tbScheduleJobManager.getScheduleMsgId().toString(),
                            tbScheduleJobManager.getScheduleMsgId().toString(),
                            tbScheduleJobManager.getJobCron(),
                            tbScheduleJobManager.getScheduleMsgId()
                            );
            }

            List<TbScheduleGroupMsgManager> tbScheduleGroupMsgManagers = tbScheduleGroupMsgManagerService.selectQiYongJob();
            for (TbScheduleGroupMsgManager tbScheduleGroupMsgManager : tbScheduleGroupMsgManagers){
                quartzSchedulerTool.addJob(
                        tbScheduleGroupMsgManager.getJobClassPath(),
                        tbScheduleGroupMsgManager.getScheduleJobId().toString(),
                        tbScheduleGroupMsgManager.getJobGroup(),
                        tbScheduleGroupMsgManager.getJobCron(),
                        tbScheduleGroupMsgManager.getScheduleJobId()
                );
            }

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
