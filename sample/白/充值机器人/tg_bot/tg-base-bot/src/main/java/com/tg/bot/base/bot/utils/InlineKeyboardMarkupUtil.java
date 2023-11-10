package com.tg.bot.base.bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

/**
 * 按钮布局工具
 */
public class InlineKeyboardMarkupUtil {

    /**
     * 通过二维集合 创建多行内联按钮布局对象
     * @return
     */
    public static InlineKeyboardMarkup createInlineKeyboardMarkupByListList(List<List<InlineKeyboardButton>> listListInlineKeyBoardButton) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(listListInlineKeyBoardButton);
        return markupInline;
    }
}
