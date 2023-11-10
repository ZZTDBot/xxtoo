package com.tg.bot.base.bot.test;

import com.tg.bot.base.bot.BaseBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Test2 extends BaseBot {
    @Override
    public String getBotUsername() {
        return "test921_bot";
    }

    @Override
    public String getBotToken() {
        return "6357618621:AAGT9lVGHD8Q6cBu3lL-zQZxKTbdFCCiB_c";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("监听到的消息Test2:" + update);

        try {

            while (Boolean.TRUE){
                Thread.sleep(5000);
                sendMessage(-1001900748091l,"1111");
            }

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
