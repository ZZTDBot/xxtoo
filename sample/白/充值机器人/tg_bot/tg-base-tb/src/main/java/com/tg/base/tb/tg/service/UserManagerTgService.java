package com.tg.base.tb.tg.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * 用户管理飞机服务类
 */
public interface UserManagerTgService {

    /**
     * 添加所有被机器人捕获的用户信息到数据库
     * @param update
     * @return
     */
    @Deprecated
    public Boolean addTgUserToDbBySendMesToBot(Update update);


    /**
     * 添加进群组的用户到数据库
     * @param update
     * @return
     */
    @Deprecated
    public Boolean addTgUserToDbByUserInQun(Update update);

    /**
     * 私聊机器人添加用户
     * @param update
     * @return
     */
    public Boolean privateAddUser(Update update);

    /**
     * 群机器人添加用户
     * @param update
     * @return
     */
    public Boolean qunComEventAddUser(Update update);
}
