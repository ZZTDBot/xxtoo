package com.tg.bot.base.bot.test;

import com.tg.bot.base.bot.BaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Test1 extends BaseBot {
    @Override
    public String getBotUsername() {
        return "test920_bot";
    }

    @Override
    public String getBotToken() {
        return "6238456707:AAGxO2LrFpe-Fhke8HHCwVRS2LQFSvuNDOw";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("监听到的消息Test1:" + update);
    }

}
