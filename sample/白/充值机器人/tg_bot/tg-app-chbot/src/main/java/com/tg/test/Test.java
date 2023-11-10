package com.tg.test;

import com.tg.base.tb.utils.RegxUtils;

import java.util.regex.Matcher;

public class Test {
    public static void main(String[] args) {
//        String input = "[按钮11&&按钮11的内容$$按钮12&&按钮12的内容][按钮21&&按钮21的内容$$按钮22&&按钮22的内容]";
        String input = "设置底部按钮\n" +
                "[按钮11&&按钮11的内容$$按钮12&&按钮12的内容]\n" +
                "[按钮21&&按钮21的内容$$按钮22&&按钮22的内容]";

        // 使用正则表达式匹配按钮和按钮内容的组合
//        String regex = "\\[(.*?)\\]";
        String regex = "^设置底部按钮(\r\n|\n)(\\[((.+)&&(.+)(\\$\\$)?)+\\](\r\n|\n)?)+$";
        Matcher matcher = RegxUtils.matcherRegx(input, regex);

        // 遍历匹配到的按钮组合
        while (matcher.find()) {
            String buttonGroup = matcher.group(1); // 获取匹配到的按钮组合内容
            String[] buttons = buttonGroup.split("\\$\\$"); // 使用"$$"分隔按钮

            System.out.println("创建底部按钮:");
            for (String button : buttons) {
                String[] buttonParts = button.split("&&"); // 使用"&&"分隔按钮和按钮内容
                if (buttonParts.length == 2) {
                    String buttonName = buttonParts[0].trim();
                    String buttonContent = buttonParts[1].trim();
                    System.out.println("按钮名称: " + buttonName);
                    System.out.println("按钮内容: " + buttonContent);
                    // 在这里，你可以根据按钮名称和内容创建底部按钮
                } else {
                    System.out.println("无效的按钮组合: " + button);
                }
            }
        }

    }
}
