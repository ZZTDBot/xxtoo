package com.tg.base.tb.tg.session;

import com.tg.base.tb.tg.bot.BuBaseBot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.util.HashMap;

/**
 * 正在运行的机器人句柄对象
 */
@Data
@Slf4j
@AllArgsConstructor
public class TgBotRunSession {

    /**
     * 机器人运行时句柄对象
     */
    private BotSession botSession;


    /**
     * 机器人对象
     */
    private BuBaseBot buBaseBot;

    /**
     * 保存所有已注册机器人会话
     */
    public static final HashMap<Integer, TgBotRunSession> startSuccessJzBaseBotMap = new HashMap<Integer,TgBotRunSession>();
}
