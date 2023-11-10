package com.tg.bot.app.chbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.tg.bot.base.bot","com.tg.base.tb","com.tg.bot.app.chbot"})
public class TgAppChBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgAppChBotApplication.class, args);
	}

}
