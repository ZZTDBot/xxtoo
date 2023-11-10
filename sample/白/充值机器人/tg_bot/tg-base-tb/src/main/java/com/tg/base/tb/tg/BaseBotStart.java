package com.tg.base.tb.tg;

import com.tg.base.tb.enum1.db.TbBotInstanceManagerBotQyStatus;
import com.tg.base.tb.service.TbBotInstanceManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbBotInstanceManagerTgConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

/**
 * 系统初始化方法
 */
@Component
@Slf4j
public class BaseBotStart {

    @Autowired
    protected TbBotInstanceManagerService tbBotInstanceManagerService;
    @Autowired
    protected TbBotInstanceManagerTgConfig tbBotInstanceManagerTgConfig;

    @PostConstruct
    public void init() throws TelegramApiException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        tbBotInstanceManagerService.startAllBotByQyStatus(TbBotInstanceManagerBotQyStatus.QI_YONG.getId(), (Class<? extends BuBaseBot>) Class.forName(tbBotInstanceManagerTgConfig.getBotFullClassName()));
        logger.info("父--机器人启动成功........");
    }

}
