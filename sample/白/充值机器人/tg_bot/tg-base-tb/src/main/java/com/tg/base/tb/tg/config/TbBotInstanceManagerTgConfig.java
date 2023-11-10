package com.tg.base.tb.tg.config;

import lombok.Data;
import org.glassfish.jersey.internal.util.PropertiesClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * 机器人实例模块的配置类
 */
@Data
@Component
@ConfigurationProperties(value = "com.tg.base.tb.tg.config.tbbotinstancemanagertgconfig")
public class TbBotInstanceManagerTgConfig {
    //---------命令配置区域----------
    private List<BotCommand> botCommands = new ArrayList<>();
    private BotCommand myBotList = new BotCommand("/mybotlist","我的机器人列表");
    {
        botCommands.add(myBotList);
    }

    //---------内联按钮配置区域----------
    //回调参数路径分隔符
    private String pathFenGe = "/";

    //机器人实例模块-内联按钮的根路径
    private String inlineButtonCallDataRoot = "/botinstance";

    //机器人实例模块-查询机器人详情的路径  /botinstance/botdetail/{botId}
    private String inlineButtonCallDataBotDetail = inlineButtonCallDataRoot + "/botdetail";
    //机器人实例模块-查询机器人详情的路径-正则  /botinstance/botdetail/{botId}
    private String inlineButtonCallDataBotDetailRegx = "/botinstance/botdetail/([0-9]+)";

    //机器人的全类名称
    private String botFullClassName = "com.tg.base.tb.tg.bot.BuBaseBot";


    //---------正则命令---------
    /**
     * 停用机器人
     * group(1) = 机器用名称
     *
     *
     * 停用机器人:@123_bot
     */
    private String tingYongBotRegx = "^停用机器人:\\@([0-9a-zA-z]+)";

    /**
     * 启用机器人
     * group(1) = 机器用名称
     *
     *
     * 启用机器人:@123_bot
     */
    private String qyBotRegx = "^启用机器人:\\@([0-9a-zA-z]+)";

}
