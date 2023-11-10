package com.tg.base.tb.tg.bot;

import com.tg.base.tb.entity.TbBotInstanceManager;
import com.tg.base.tb.service.TbUserBotinstanceQunManagerService;
import com.tg.base.tb.tg.service.*;
import com.tg.base.tb.tg.service.common.TbCommonDownButtonTgService;
import com.tg.bot.base.bot.BaseBot;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

/**
 * 业务机器人基础类
 */
@Data
@Slf4j
@NoArgsConstructor
@Component
@Scope("prototype")
public class BuBaseBot extends BaseBot {

    protected TbBotInstanceManager tbBotInstanceManager;

    /**
     * 有参构造方法
     * @param tbBotInstanceManager
     */
    public BuBaseBot(TbBotInstanceManager tbBotInstanceManager) {
        this.tbBotInstanceManager = tbBotInstanceManager;
    }

    @Override
    public String getBotUsername() {
        return tbBotInstanceManager.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return tbBotInstanceManager.getBotToken();
    }

    @Autowired
    protected UserManagerTgService userManagerTgService;

    @Autowired
    protected TbQunManagerTgService tbQunManagerTgService;

    @Autowired
    protected TbUserQunManagerTgService tbUserQunManagerTgService;

    @Autowired
    protected TbUserBotinstanceQunManagerTgService tbUserBotinstanceQunManagerTgService;

    @Autowired
    protected TbBotInstanceManagerTgService tbBotInstanceManagerTgService;

    @Autowired
    protected TbPriKeywordReplyManagerTgService tbPriKeywordReplyManagerTgService;

    @Autowired
    protected TbScheduleMsgManagerTgService tbScheduleMsgManagerTgService;

    @Autowired
    TbScheduleJobManagerTgService tbScheduleJobManagerTgService;

    @Autowired
    TbScheduleGroupMsgManagerTgService tbScheduleGroupMsgManagerTgService;

    @Autowired
    TbCommonDownButtonTgService tbCommonDownButtonTgService;



    @Override
    public void onUpdateReceived(Update update) {
        logger.info("父 消息方法 update:{}",update);

        try {

            //1 公共处理方法
            //1.私聊机器人添加用户
            userManagerTgService.privateAddUser(update);
            //2.群机器人添加用户
            userManagerTgService.qunComEventAddUser(update);

            //1.3 保存群信息
            tbQunManagerTgService.saveOrUpdateTgQun(update);

            //1.4 保存用户与群关系
            tbUserQunManagerTgService.addUserQunRalationByUserInQun(update);
            //1.5 删除用户与群关系在有人退群时
            tbUserQunManagerTgService.delUserQunRalationByUserOutQun(update);

            //1.6 添加或删除 用户-机器人-群 关系记录
            tbUserBotinstanceQunManagerTgService.botInsInOutQunByUser(this,tbBotInstanceManager.getTbBotInstanceId(),update);

            //1.7 机器人管理模块
            tbBotInstanceManagerTgService.addBotByToken(update,this);
            tbBotInstanceManagerTgService.proccessPrivate(this,update);

            //1.8 私聊-关键词回复
            tbPriKeywordReplyManagerTgService.priMsgProccess(update,this);

            //1.9 私聊-创建消息
            tbScheduleMsgManagerTgService.priMsgProccess(update,this);

            //2.0 私聊-调度任务模块
            tbScheduleJobManagerTgService.priMsgProccess(update,this);

            //2.1 私聊-群消息调度任务模块
            tbScheduleGroupMsgManagerTgService.priMsgProccess(update,this);

            //2.2 私聊-公共底部按钮处理
            tbCommonDownButtonTgService.priMsgProccess(update,this);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
