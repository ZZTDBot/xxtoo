package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbScheduleGroupMsgManager;
import com.tg.base.tb.enum1.db.TbScheduleGroupMsgManagerJobStatus;
import com.tg.base.tb.enum1.db.TbScheduleJobManagerJobStatus;
import com.tg.base.tb.schedule.constance.ScheduleConstance;
import com.tg.base.tb.schedule.quartz.QuartzSchedulerTool;
import com.tg.base.tb.service.TbScheduleGroupMsgManagerService;
import com.tg.base.tb.mapper.TbScheduleGroupMsgManagerMapper;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
* @description 针对表【tb_schedule_group_msg_manager(定时群消息管理)】的数据库操作Service实现
* @createDate 2023-09-07 16:41:35
*/
@Service
public class TbScheduleGroupMsgManagerServiceImpl extends ServiceImpl<TbScheduleGroupMsgManagerMapper, TbScheduleGroupMsgManager>
    implements TbScheduleGroupMsgManagerService{

    @Autowired
    QuartzSchedulerTool quartzSchedulerTool;

    @Override
    public List<TbScheduleGroupMsgManager> selectQiYongJob() {

        return baseMapper.selectByJobStatus(TbScheduleGroupMsgManagerJobStatus.RUNNING.getId());
    }

    @Override
    @Transactional
    public int addScheduleJob(String userTgId, String qunId, int botId, String msg, int jobStatus, String cron,String userCron) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        TbScheduleGroupMsgManager tbScheduleGroupMsgManager = new TbScheduleGroupMsgManager();

        tbScheduleGroupMsgManager.setTgUserId(userTgId);
        tbScheduleGroupMsgManager.setTgQunId(qunId);
        tbScheduleGroupMsgManager.setTbBotInstanceId(botId);
        tbScheduleGroupMsgManager.setMsgContext(msg);
        tbScheduleGroupMsgManager.setJobStatus(jobStatus);
        tbScheduleGroupMsgManager.setJobCron(cron);
        tbScheduleGroupMsgManager.setUserCron(userCron);

        tbScheduleGroupMsgManager.setJobGroup(qunId);
        tbScheduleGroupMsgManager.setJobClassPath(ScheduleConstance.scheduleGroupMsgClassFullName);

        int insert = baseMapper.insert(tbScheduleGroupMsgManager);

        quartzSchedulerTool.addJob(
                tbScheduleGroupMsgManager.getJobClassPath(),
                tbScheduleGroupMsgManager.getScheduleJobId().toString(),
                tbScheduleGroupMsgManager.getJobGroup(),
                tbScheduleGroupMsgManager.getJobCron(),
                tbScheduleGroupMsgManager.getScheduleJobId()
        );

        return insert;
    }

    @Override
    public String queryQunScheduleMsgContext(String qunId) {
        List<TbScheduleGroupMsgManager> list = baseMapper.selectByQunId(qunId);

        StringBuilder builder = new StringBuilder();
        builder.append("本群调度消息如下:\n\n");
        if(!CollectionUtils.isEmpty(list)){
            for (int i = 0; i < list.size(); i++){
                TbScheduleGroupMsgManager tbScheduleGroupMsgManager = list.get(i);

                builder.append("第" + i + "条消息\n");
                builder.append("<code>");
                builder.append("消息ID:" + tbScheduleGroupMsgManager.getScheduleJobId() + "\n");
                builder.append("消息内容:" + tbScheduleGroupMsgManager.getMsgContext() + "\n");
                builder.append("启用状态:" + (tbScheduleGroupMsgManager.getJobStatus() == TbScheduleGroupMsgManagerJobStatus.RUNNING.getId() ? TbScheduleGroupMsgManagerJobStatus.RUNNING.getMsg() : TbScheduleGroupMsgManagerJobStatus.STOP.getMsg()) + "\n");
                builder.append("定时表达式:" + tbScheduleGroupMsgManager.getUserCron());
                builder.append("</code>" + "\n\n");

            }
        }

        return builder.toString();
    }

    @Override
    public int updateQunScheduleMsg(int scheduleId, String msg) {
        return baseMapper.updateMsgById(scheduleId,msg);
    }

    @Override
    @Transactional
    public int delQunScheduleMsg(int scheduleId) throws SchedulerException {
        TbScheduleGroupMsgManager tbScheduleGroupMsgManager = baseMapper.selectById(scheduleId);
        int deleteById = baseMapper.deleteById(tbScheduleGroupMsgManager);

        quartzSchedulerTool.deleteJob(
                tbScheduleGroupMsgManager.getScheduleJobId().toString(),
                tbScheduleGroupMsgManager.getJobGroup());

        return deleteById;
    }

    @Override
    public Boolean stopQunScheduleMsg(int scheduleId) throws SchedulerException {
        TbScheduleGroupMsgManager tbScheduleGroupMsgManager = baseMapper.selectById(scheduleId);

        quartzSchedulerTool.deleteJob(
                tbScheduleGroupMsgManager.getScheduleJobId().toString(),
                tbScheduleGroupMsgManager.getJobGroup());

        return Boolean.TRUE;
    }

    @Override
    public Boolean startQunScheduleMsg(int scheduleId) throws SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        TbScheduleGroupMsgManager tbScheduleGroupMsgManager = baseMapper.selectById(scheduleId);

        quartzSchedulerTool.addJob(
                tbScheduleGroupMsgManager.getJobClassPath(),
                tbScheduleGroupMsgManager.getScheduleJobId().toString(),
                tbScheduleGroupMsgManager.getJobGroup(),
                tbScheduleGroupMsgManager.getJobCron(),
                tbScheduleGroupMsgManager.getScheduleJobId()
        );

        return Boolean.TRUE;
    }


}




