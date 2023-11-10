package com.tg.base.tb.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数字工具类
 */
public class NumberUtils {

    private static final int scale = 2;

    //格式换数字，保留两位小数
    public static final String NUMBERfORMAT_0_00  = "0.00";


    private NumberUtils() {
        // 这个类不能外部实例化
    }

    /**
     * 格式化小数为百分号
     *
     * @param decimal
     * @param scale
     * @return
     */
    public static String decimalToPercent(double decimal, int scale) {
        // 获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        if (Math.abs(decimal) == 1) {
            nt.setMinimumFractionDigits(0);
        } else {
            // 设置百分数精确度2即保留两位小数
            nt.setMinimumFractionDigits(scale);
            nt.setRoundingMode(RoundingMode.HALF_UP);
        }
        return nt.format(decimal);
    }

    /**
     * 格式化小数为百分号
     *
     * @param decimal
     * @param scale
     * @return
     */
    public static String decimalToPercent(String decimal, int scale) {
        return decimalToPercent(Double.parseDouble(decimal), scale);
    }

    /**
     * 格式化小数为百分号
     *
     * @param decimal
     * @return
     */
    public static String decimalToPercent(String decimal) {
        return decimalToPercent(Double.parseDouble(decimal), scale);
    }

    /**
     * 格式化小数为百分号
     *
     * @param decimal
     * @return
     */
    public static String decimalToPercent(double decimal) {
        return decimalToPercent(decimal, scale);
    }

    /**
     * 格式化数值
     *
     * @param data
     * @param pattern
     * @return
     */
    public static String formatNumber(String data, String pattern) {
        return formatNumber(Double.parseDouble(data), pattern);
    }

    /**
     * 格式化数值
     *
     * @param data
     * @param pattern
     * @return
     */
    public static String formatNumber(double data, String pattern) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(pattern);// 将格式应用于格式化器
        // 在格式化的时候会自动进行舍入，舍入模式是：
        // 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
        return df.format(data);
    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.formatNumber("0.5234", "0.0000"));

        System.out.println(decimalToPercent("0.5"));
        System.out.println(decimalToPercent("0.04336"));
        System.out.println(decimalToPercent("0.0417"));
        System.out.println("--------------------------------------------------------");

        double data = 1000000000990.405607809;
        System.out.println("原数据：" + data);
        // 模式中的"."表示小数分隔符
        // 模式中的"0"表示如果该位存在字符，则显示字符，如果不存在，则显示0
        String pattern = "0.00";// 显示格式
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        pattern = "00000.000 kg";// 可以在模式最后加自己想要的任何字符，比如单位
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        // 模式中的"#"表示如果该位存在字符，则显示字符，如果不存在，则不显示。
        pattern = "##.000 kg";// 注意#只能出现在模式的两头，不能在0中间
        // 错误！ pattern = "##00#.#0"
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        // 模式中的"-"表示输出为负数，要放在最前面
        pattern = "-000.000";
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        // 模式中的","在数字中添加逗号，方便读数字
        pattern = "-0,000.0#";
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        // 模式中的"E"表示输出为指数，"E"之前的字符串是底数的格式，
        // "E"之后的是字符串是指数的格式
        pattern = "0.00E000";
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        // 模式中的"%"表示乘以100并显示为百分数，要放在最后。
        pattern = "0.00%";
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
        // 模式中的"\u2030"表示乘以1000并显示为千分数，要放在最后。
        pattern = "0.00\u2030";
        System.out.println(pattern + "样式：" + formatNumber(data, pattern));
    }
}
