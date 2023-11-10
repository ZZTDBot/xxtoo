package com.tg.base.tb.service;

import com.tg.base.tb.exception.BaseException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.base.tb.entity.TbBotAdminManager;

/**
* @description 针对表【tb_bot_admin_manager(机器人的管理员表)】的数据库操作Service
* @createDate 2023-06-02 17:04:25
*/
public interface TbBotAdminManagerService extends IService<TbBotAdminManager> {
    /**
     * 添加一个机器人管理员
     * @param opratorUserTgId   当前操作人
     * @param botId             机器人ID
     * @param userTgId          被添加的用户ID
     * @return
     */
    public int addBotAdmin(String opratorUserTgId,int botId,String userTgId) throws BaseException;

    /**
     * 删除一个机器人管理员
     * @param opratorUserTgId   当前操作人
     * @param botId             机器人ID
     * @param userTgId          被添加的用户ID
     * @return
     */
    public int deleteBotAdmin(String opratorUserTgId,int botId,String userTgId) throws BaseException;

    /**
     * 判断是不是机器人的管理员
     * @param botId
     * @param opratorUserTgId
     * @return
     */
    public Boolean isBotAdmin(int botId, String opratorUserTgId);

    /**
     * 判断当前操作人有没有机器人的管理权限
     * @param opratorUserTgId   操作人的ID
     * @param botId             机器人的ID
     */
    public Boolean autherPromise(String opratorUserTgId, int botId) throws BaseException;
}
