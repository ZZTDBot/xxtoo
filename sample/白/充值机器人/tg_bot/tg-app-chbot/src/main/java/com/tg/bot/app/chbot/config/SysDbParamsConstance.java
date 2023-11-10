package com.tg.bot.app.chbot.config;

import lombok.Data;

/**
 * 系统参数配置类
 * 配置再数据库的参数
 */
@Data
public class SysDbParamsConstance {
    /**
     * cun-hw公告
     */
    public static String cunKanHwGg = "cun.hw.gg" ;

    /**
     * cun-usdt公告
     */
    public static String cunKanUsdGg = "cun.usdt.gg" ;

    /**
     * 客服群ID
     */
    public static String kefuQunId = "kefu.qun.id" ;

    /**
     * 主页的文本
     */
    public static String chAppHomeText = "ch.app.home.text" ;

    /**
     * 监控会话ID
     */
    public static String chAppJianKongChatId = "ch.app.jian.kong.chat.id" ;

}
