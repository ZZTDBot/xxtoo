package com.tg.bot.app.chbot.service;

import com.tg.bot.app.chbot.entity.TbUserExtInfoManager;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @description 针对表【tb_user_ext_info_manager(用户拓展信息表管理)】的数据库操作Service
* @createDate 2023-10-08 19:20:32
*/
public interface TbUserExtInfoManagerService extends IService<TbUserExtInfoManager> {

    /**
     * 是否绑定了用户ID
     * @param tgUserId
     * @return
     */
    Boolean isBindedId(String tgUserId);

    /**
     * 绑定用户的游戏ID
     * @param tgUserId    用户ID
     * @param bindId      用户的有系ID
     * @return
     */
    Boolean saveOrUpdateBindId(String tgUserId, String bindId);

    /**
     * 是否添加了usd账户信息
     * @param tgUserId
     * @return
     */
    Boolean haveUserUsdInfo(Long tgUserId);

    /**
     * 添加用户的usdt账户信息
     * @param tgUserId
     * @param bindQuUsdInfoText
     * @return
     */
    Boolean saveOrUpdateQuUsdInfo(String tgUserId, String bindQuUsdInfoText);

    /**
     * 通过用户id查询用户的usdt账户信息
     * @param tgUserId
     * @return
     */
    String queryUsdtAccountInfoByTgUserId(Long tgUserId);

    /**
     * 是否添加了Hw账户信息
     * @param tgUserId
     * @return
     */
    Boolean haveUserHwInfo(Long tgUserId);

    /**
     * 添加用户的汇旺账户信息
     * @param tgUserId
     * @param bindQuHwInfoText
     * @return
     */
    Boolean saveOrUpdateQuHwInfo(String tgUserId, String bindQuHwInfoText);

    /**
     * 通过用户id查询用户的汇旺账户信息
     * @param tgUserId
     * @return
     */
    String queryHwAccountInfoByTgUserId(Long tgUserId);
}
