package com.tg.base.tb.tg.service;


import org.telegram.telegrambots.meta.api.objects.Update;

/**
* 群管理飞机服务类
*/
public interface TbQunManagerTgService {

    /**
     * 保存群信息
     * @param update
     * @return
     */
    public Boolean saveOrUpdateTgQun(Update update);

}
