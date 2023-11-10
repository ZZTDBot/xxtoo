package com.tg.bot.base.bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 内联按钮工具类
 */
public class InlineKeyboardButtonUtil {

    /**
     * 创建一个内联按钮
     * @param buttonName   按钮的名称
     * @param callData     回调数据
     * @return
     */
    public static InlineKeyboardButton createCallDataInlineKeyboardButton(String buttonName , String callData){
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setCallbackData(callData);
        return inlineKeyboardButton;
    }

    /**
     * 创建一个内联按钮
     * @param buttonName   按钮的名称
     * @param callData     回调数据
     * @return
     */
    public static List<InlineKeyboardButton> createCallDataInlineKeyboardButtonList(String buttonName , String callData){
        List<InlineKeyboardButton> list = new ArrayList<>();
        list.add(createCallDataInlineKeyboardButton(buttonName,callData));
        return list;
    }

    /**
     * 创建一个内联网址按钮
     * @param buttonName   按钮的名称
     * @param url          回调数据
     * @return
     */
    public static InlineKeyboardButton createCallDataInlineKeyboardButtonUrl(String buttonName , String url){
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setUrl(url);
        return inlineKeyboardButton;
    }

    /**
     * 创建一个内联按钮数组
     * @param inlineKeyboardButtons
     * @return
     */
    public static List<InlineKeyboardButton> createInlineKeyboardButtons(InlineKeyboardButton... inlineKeyboardButtons) {
        List<InlineKeyboardButton> list = new ArrayList<>();
        for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtons){
            list.add(inlineKeyboardButton);
        }
        return list;
    }

    /**
     * 合并多个内联按钮集合
     * @param buttonsOne      内联按钮集合1
     * @param buttonsTwo      内联按钮集合2
     * @return
     */
    public static List<List<InlineKeyboardButton>> mergeInlineKeyboardButtonList(List<InlineKeyboardButton> buttonsOne, List<InlineKeyboardButton> buttonsTwo) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        list.add(buttonsOne);
        list.add(buttonsTwo);
        return list;
    }

    /**
     * 合并多个内联按钮集合
     * @param buttonsLists      内联按钮集合
     * @return
     */
    public static List<List<InlineKeyboardButton>> mergeInlineKeyboardButtonList2(List<InlineKeyboardButton>... buttonsLists) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        for (List<InlineKeyboardButton> listSub : buttonsLists){
            list.add(listSub);
        }
        return list;
    }


    /**
     * 创建一个内联按钮布局
     * @param inlineKeyboardButtons      内联按钮数组
     * @return
     */
    public static InlineKeyboardMarkup createInlineKeyboardMarkup(List<List<InlineKeyboardButton>> inlineKeyboardButtons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
        return inlineKeyboardMarkup;
    }

    /**
     * 创建一个内联按钮布局
     * @param inlineKeyboardButtons      内联按钮数组
     * @return
     */
    public static InlineKeyboardMarkup createInlineKeyboardMarkup2(List<InlineKeyboardButton> inlineKeyboardButtons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        list.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(list);

        return inlineKeyboardMarkup;
    }


}
