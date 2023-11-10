package com.tg.base.tb.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class RegxUtils {
    /**
     * 检查给定的文本能否匹配所有命令正则中的一个
     * @param context
     * @return 是否匹配
     */
    public static Boolean checkMatchStringInListCmd(List<String> cmdList, String context){
        for (String reg : cmdList) {
            Pattern compile = Pattern.compile(reg);
            Matcher matcher = compile.matcher(context);

            if (matcher.find()){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 获取正则匹配器
     * @param context
     * @param regx
     * @return 正则匹配器
     */
    public static Matcher matcherRegx(String context, String regx){
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(context);
        return matcher;
    }


}
