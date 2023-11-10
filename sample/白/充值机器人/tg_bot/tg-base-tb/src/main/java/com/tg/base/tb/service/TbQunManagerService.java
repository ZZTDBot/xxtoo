package com.tg.base.tb.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.base.tb.entity.TbQunManager;

/**
* @author xxx
* @description 针对表【tb_qun_manager(tg群管理)】的数据库操作Service
* @createDate 2022-09-13 14:39:06
*/
public interface TbQunManagerService extends IService<TbQunManager> {

    /**
     * 保存群信息
     * @param tgChatId           飞机群ID
     * @param chatTitle          飞机群Title
     * @param qunType            飞机群类型
     * @param chatUserName       飞机群用户名
     */
    public Boolean saveOrUpdateTgQun(String tgChatId, String chatTitle,Integer qunType,String chatUserName);

    /**
     * 通过群ID获取群名称
     * @param tgQunId   群ID
     * @return   群名称
     */
    String selectQunNameByQunId(String tgQunId);
}
