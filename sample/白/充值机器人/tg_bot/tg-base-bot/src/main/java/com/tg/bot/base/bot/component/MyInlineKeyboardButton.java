package com.tg.bot.base.bot.component;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Data
@NoArgsConstructor
public class MyInlineKeyboardButton extends InlineKeyboardButton {

    public MyInlineKeyboardButton(String text,String callBackData,String url){
        this.setText(text);
        this.setCallbackData(callBackData);
        this.setUrl(url);
    }
}
