package com.tg.base.tb.utils;

import java.util.Set;

/**
 * 生成订单校验码的工具类
 */
public class ValidataCodeUtil {

    /**
     * 获取三位数以内的数字，不能存在在列表内
     * @Parms         nums 已存在的三位以为数字
     * @return        三位数以内的数字
     */
    public static int getMinNumForListNums(Set<Integer> nums){
        for (int i = 1 ; i <= 999 ; i++) {
            if (!nums.contains(i)){
                return i;
            }
        }
        throw new RuntimeException("没有三位以内可用的数字了");
    }

    /**
     * 去除字符串数字后面的0
     * @param shuZi
     * @return
     */
    public static int quChuShuZiHou0(String shuZi) {
        StringBuilder stringBuilder = new StringBuilder(shuZi).reverse();
        int parseInt = Integer.parseInt(stringBuilder.toString());
        StringBuffer reverse = new StringBuffer(parseInt + "").reverse();
        int parseInt1 = Integer.parseInt(reverse.toString());
        return parseInt1;
    }
}
