package com.tg.base.tb.schedule.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tg.base.tb.compoent.GetBean;
import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.tg.base.tb.schedule.constance.ScheduleConstance;
import com.tg.base.tb.schedule.params.SchedulerTgSendMessageParam;
import com.tg.base.tb.service.TbScheduleMsgManagerService;
import com.tg.base.tb.tg.session.TgBotRunSession;
import com.tg.base.tb.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import springfox.documentation.spring.web.json.Json;

/**
 * @ClassName: SchedulerTgSendMessageJob
 * @Description: 定时发送消息的工作类
 * @version: V1.0
 */
@Slf4j
public class SchedulerTgSendMessageJob implements Job {

    TbScheduleMsgManagerService tbScheduleMsgManagerService;

    public SchedulerTgSendMessageJob(){
        tbScheduleMsgManagerService = GetBean.getBean(TbScheduleMsgManagerService.class);
    }

    private void beford() {
        logger.info(String.valueOf(System.currentTimeMillis()));
        logger.info("任务执行开始----定时发送消息的工作类");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        beford();

        /** 执行业务 */
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        logger.info("执行业务逻辑   map:" + jobDataMap);

        Integer scheduleMsgId = (Integer) jobDataMap.get(ScheduleConstance.scheduleJobParamKey);
        try {
            TbScheduleMsgManager scheduleMsgManagerServiceById = tbScheduleMsgManagerService.getById(scheduleMsgId);
            TgBotRunSession tgBotRunSession =
                    TgBotRunSession.startSuccessJzBaseBotMap.get(Integer.valueOf(scheduleMsgManagerServiceById.getTbBotInstanceId()));
            if(tgBotRunSession != null){
                tgBotRunSession.getBuBaseBot().sendMessage(scheduleMsgManagerServiceById.getQunChatId(),scheduleMsgManagerServiceById.getMsgContext());
            }
        }  catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        after();
    }


    private void after() {
        logger.info("任务执行完毕----定时发送消息的工作类");
        logger.info(String.valueOf(System.currentTimeMillis()));
    }
}