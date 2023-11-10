package com.tg.base.tb.schedule.job;

import com.tg.base.tb.tg.session.TgBotRunSession;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @ClassName: SchedulerQuartzJob
 * @Description:
 * @version: V1.0
 */
@Slf4j
public class SchedulerQuartzJob implements Job {

    private void beford() {
        logger.info("任务执行开始");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        beford();
        logger.info(String.valueOf(System.currentTimeMillis()));
        /** 执行业务 */
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        logger.info("执行业务逻辑   map:" + jobDataMap);

        String chatId = (String) jobDataMap.get("chatId");
        String botId = jobDataMap.get("botId").toString();
        String context = (String) jobDataMap.get("context");

        TgBotRunSession tgBotRunSession = TgBotRunSession.startSuccessJzBaseBotMap.get(Integer.valueOf(botId));
        try {
            tgBotRunSession.getBuBaseBot().sendMessage(chatId,context);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        logger.info(String.valueOf(System.currentTimeMillis()));
    }


    private void after() {
        logger.info("任务执行完毕");
    }
}