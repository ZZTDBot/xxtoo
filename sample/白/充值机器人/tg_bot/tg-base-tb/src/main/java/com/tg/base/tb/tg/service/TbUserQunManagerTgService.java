package com.tg.base.tb.tg.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * 用户与群关系的业务操作类
 */
public interface TbUserQunManagerTgService {

    /**
     * 添加一个用户与群的关系记录,在有人进群时
     * @param update     飞机消息
     * @return
     */
    int addUserQunRalationByUserInQun(Update update);

    /**
     * 删除一个用户与群的关系记录，在有人退群时
     * @param update     飞机消息
     * @return
     */
    int delUserQunRalationByUserOutQun(Update update);
}
