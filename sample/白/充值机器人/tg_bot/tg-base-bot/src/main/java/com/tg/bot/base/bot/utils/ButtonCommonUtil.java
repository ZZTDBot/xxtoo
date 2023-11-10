package com.tg.bot.base.bot.utils;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 按钮公共工具类
 */
public class ButtonCommonUtil {


    public static String buttonRowContextRegex = "^设置底部按钮(\r\n|\n)((\\[(.*?)\\](\r\n|\n)?)+)";//匹配每一行按钮的正则表达式
    public static String buttonRowRegex = "\\[(.*?)\\]";//匹配每一行按钮的正则表达式
    public static String buttonSplitKey = "\\$\\$";//一行按钮之间的分隔符
    public static String keyAndValueSplitKey = "&&";//一个按钮中,名称与内容的分隔符

    public static void main(String[] args) {
        String input = "设置底部按钮\n" +
                "[按钮11&&按钮11的内容$$按钮12&&按钮12的内容]\n" +
                "[按钮21&&按钮21的内容$$按钮22&&按钮22的内容]";
//        String input = "[按钮11&&按钮11的内容$$按钮12&&按钮12的内容][按钮21&&按钮21的内容$$按钮22&&按钮22的内容]";
        String buttonContext = getTargetButtonRowContext(input,buttonRowContextRegex,2);

        if(StringUtils.hasText(buttonContext)){
            // 使用正则表达式匹配按钮和按钮内容的组合
            List<LinkedHashMap<String, String>> lists = parseTextButtonToListMap(input, buttonRowRegex, buttonSplitKey, keyAndValueSplitKey);
            System.out.println(lists);

            List<List<String>> listListButtonNames = getListListButtonNames(lists);

            System.out.println(listListButtonNames);
//            System.out.println(JSONObject.valueToString(lists));
        }
    }

    /**
     * 获取底部按钮的所有名称
     * @param lists
     * @return
     */
    public static List<List<String>> getListListButtonNames(List<LinkedHashMap<String, String>> lists) {
        List<List<String>> listListButtonNames = new ArrayList<>();
        for (LinkedHashMap<String, String> linkedHashMap : lists){
            Set<Map.Entry<String, String>> entries = linkedHashMap.entrySet();
            List<String> listButtonNames = new ArrayList<>();
            listListButtonNames.add(listButtonNames);
            for (Map.Entry<String, String> entry : entries){
                listButtonNames.add(entry.getKey());
            }
        }
        return listListButtonNames;
    }

    /**
     * 获取目标按钮的行文本内容
     * @param input                   输入文本内容
     * @param regx                    正则表达式
     * @param rowContextRegxIndex     匹配的目标索引
     * @return
     */
    public static String getTargetButtonRowContext(String input , String regx , int rowContextRegxIndex) {
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(input);

        String buttonContext = "";

        if(matcher.find()){
            buttonContext = matcher.group(rowContextRegxIndex);
        }
        return buttonContext;
    }

    /**
     * 解析自定义按钮布局内容
     * @param input                     按钮布局内容   例如： [按钮11&&按钮11的内容$$按钮12&&按钮12的内容][按钮21&&按钮21的内容$$按钮22&&按钮22的内容]
     * @param regex                     匹配每一行按钮的正则表达式  例如：\[(.*?)\]
     * @param buttonSplitKey            一行按钮之间的分隔符   例如：\$\$
     * @param keyAndValueSplitKey      一个按钮中  名称与内容的分隔符     例如：&&
     * @return
     */
    public static List<LinkedHashMap<String, String>> parseTextButtonToListMap(String input, String regex, String buttonSplitKey ,String keyAndValueSplitKey) {
        List<LinkedHashMap<String, String>> list = new ArrayList<>();


        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // 遍历匹配到的按钮组合
        while (matcher.find()) {

            String buttonGroup = matcher.group(1); // 获取匹配到的按钮组合内容
            String[] buttons = buttonGroup.split(buttonSplitKey); // 使用"$$"分隔按钮

//            List<LinkedHashMap<String, String>> list = new ArrayList<>();
//            listList.add(list);
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String> ();
            list.add(linkedHashMap);

//            System.out.println("创建底部按钮:");

            for (String button : buttons) {

                String[] buttonParts = button.split(keyAndValueSplitKey); // 使用"&&"分隔按钮和按钮内容
                if (buttonParts.length == 2) {
                    String buttonName = buttonParts[0].trim();
                    String buttonContent = buttonParts[1].trim();

                    linkedHashMap.put(buttonName,buttonContent);

                }

            }
        }


        return list;
    }


}
