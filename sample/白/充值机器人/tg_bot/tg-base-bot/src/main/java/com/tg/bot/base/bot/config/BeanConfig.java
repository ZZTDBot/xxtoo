package com.tg.bot.base.bot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//通过该配置类的编写实现创建bean并注入到spring容器中
@Configuration
@Slf4j
public class BeanConfig {

    //Bean注解若不带name参数，则默认以方法名getComputer为bean的id，用于后续获取bean；若带参数则以name参数名用于后续获取bean

    @Bean(name="telegramBotsApi")
    public TelegramBotsApi getComputer() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        return telegramBotsApi;
    }

}
