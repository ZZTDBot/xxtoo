package com.tg.base.tb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tg.bot.base.bot","com.tg.base.tb"})
@EnableScheduling
public class TgBaseTbAppcation {
    public static void main(String[] args) {
        SpringApplication.run(TgBaseTbAppcation.class, args);
    }
}