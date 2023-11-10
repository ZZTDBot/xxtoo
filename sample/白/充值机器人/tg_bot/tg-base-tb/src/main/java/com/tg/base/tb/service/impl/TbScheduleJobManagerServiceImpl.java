package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbScheduleJobManager;
import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.tg.base.tb.enum1.db.TbScheduleJobManagerJobStatus;
import com.tg.base.tb.exception.BaseException;
import com.tg.base.tb.exception.enum1.TbScheduleJobExceptionEnum;
import com.tg.base.tb.mapper.TbScheduleMsgManagerMapper;
import com.tg.base.tb.schedule.constance.ScheduleConstance;
import com.tg.base.tb.schedule.quartz.QuartzSchedulerTool;
import com.tg.base.tb.service.TbScheduleJobManagerService;
import com.tg.base.tb.mapper.TbScheduleJobManagerMapper;
import com.tg.base.tb.service.TbScheduleMsgManagerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
* @description 针对表【tb_schedule_job_manager(定时调度任务管理)】的数据库操作Service实现
* @createDate 2023-08-26 16:15:01
*/
@Service
public class TbScheduleJobManagerServiceImpl extends ServiceImpl<TbScheduleJobManagerMapper, TbScheduleJobManager>
    implements TbScheduleJobManagerService{

    @Autowired
    QuartzSchedulerTool quartzSchedulerTool;

    @Autowired
    TbScheduleMsgManagerMapper tbScheduleMsgManagerMapper;



    @Override
    public List<TbScheduleJobManager> selectQiYongJob() {

        return baseMapper.selectByJobStatus(TbScheduleJobManagerJobStatus.RUNNING.getId());

    }

    @Override
    public Boolean haveQyScheduleJobByQunId(Long chatId) {
        TbScheduleJobManager tbScheduleJobManager = queryTbScheduleJobManagerByQunId(chatId.toString());
        if(tbScheduleJobManager != null && tbScheduleJobManager.getJobStatus() == TbScheduleJobManagerJobStatus.RUNNING.getId()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean haveScheduleJobByQunId(String chatId) {
        TbScheduleJobManager tbScheduleJobManager = queryTbScheduleJobManagerByQunId(chatId);
        if(tbScheduleJobManager == null){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }

    /**
     * 通过群Id查询调度任务
     * @param chatId   群Id
     * @return
     */
    private TbScheduleJobManager queryTbScheduleJobManagerByQunId(String chatId) {
        TbScheduleJobManager tbScheduleJobManager = baseMapper.selectScheduleJobByQunId(chatId);
        return tbScheduleJobManager;
    }

    @Override
    public Boolean revertQyStatus(String qunId) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        TbScheduleJobManager tbScheduleJobManager = queryTbScheduleJobManagerByQunId(qunId);

        if(tbScheduleJobManager.getJobStatus() == TbScheduleJobManagerJobStatus.STOP.getId()){
            quartzSchedulerTool.addJob(
                    ScheduleConstance.scheduleJobClassFullName,
                    tbScheduleJobManager.getScheduleMsgId().toString(),
                    tbScheduleJobManager.getScheduleMsgId().toString(),
                    tbScheduleJobManager.getJobCron(),
                    tbScheduleJobManager.getScheduleMsgId()
                    );
            tbScheduleJobManager.setJobStatus(TbScheduleJobManagerJobStatus.RUNNING.getId());
        }else if(tbScheduleJobManager.getJobStatus() == TbScheduleJobManagerJobStatus.RUNNING.getId()){

            quartzSchedulerTool.deleteJob(tbScheduleJobManager.getScheduleMsgId().toString(),tbScheduleJobManager.getScheduleMsgId().toString());
            tbScheduleJobManager.setJobStatus(TbScheduleJobManagerJobStatus.STOP.getId());
        }

        baseMapper.updateById(tbScheduleJobManager);

        return tbScheduleJobManager.getJobStatus() == TbScheduleJobManagerJobStatus.RUNNING.getId() ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean saveOrUpdateScheduleJob(Long userTgId, String scheduleMsgId,String cron) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        TbScheduleJobManager tbScheduleJobManager = baseMapper.selectByScheduleMsgId(Integer.valueOf(scheduleMsgId));

        if(tbScheduleJobManager != null){
            tbScheduleJobManager.setTgUserId(String.valueOf(userTgId));
            tbScheduleJobManager.setJobCron(cron);
            baseMapper.updateById(tbScheduleJobManager);

            if(tbScheduleJobManager.getJobStatus() == TbScheduleJobManagerJobStatus.RUNNING.getId()){
                quartzSchedulerTool.modifyJob(scheduleMsgId,scheduleMsgId,cron);
            }

        }else{
            TbScheduleJobManager tbScheduleJobManager1 = new TbScheduleJobManager();
            tbScheduleJobManager1.setTgUserId(String.valueOf(userTgId));
            tbScheduleJobManager1.setScheduleMsgId(Integer.valueOf(scheduleMsgId));
            tbScheduleJobManager1.setJobGroup(scheduleMsgId);
            tbScheduleJobManager1.setJobName(scheduleMsgId);
            tbScheduleJobManager1.setJobCron(cron);
            tbScheduleJobManager1.setJobClassPath(ScheduleConstance.scheduleJobClassFullName);
            baseMapper.insert(tbScheduleJobManager1);
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public int closeScheduleJobByQunId(String qunId) throws SchedulerException {

        TbScheduleMsgManager tbScheduleMsgManager = tbScheduleMsgManagerMapper.selectMsgByQunId(qunId);


        TbScheduleJobManager tbScheduleJobManager = baseMapper.selectScheduleJobByQunId(qunId);
        if(tbScheduleJobManager != null && tbScheduleJobManager.getJobStatus() == TbScheduleJobManagerJobStatus.RUNNING.getId()){
            tbScheduleJobManager.setJobStatus(TbScheduleJobManagerJobStatus.STOP.getId());
            int i = baseMapper.updateById(tbScheduleJobManager);
            quartzSchedulerTool.deleteJob(
                    tbScheduleMsgManager.getScheduleMsgId().toString(),
                    tbScheduleMsgManager.getScheduleMsgId().toString()
            );
            return i;
        }

        return 0;
    }

}




