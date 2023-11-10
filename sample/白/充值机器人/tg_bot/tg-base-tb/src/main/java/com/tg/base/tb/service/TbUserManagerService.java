package com.tg.base.tb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.base.tb.entity.TbUserManager;

/**
* @author xxx
* @description 针对表【tb_user_manager(用户管理)】的数据库操作Service
* @createDate 2022-09-13 14:39:06
*/
public interface TbUserManagerService extends IService<TbUserManager> {

    /**
     * 保存或更新用户
     * @param tgUserId          用户tgID
     * @param firstName         用户姓
     * @param userLastName      用户名
     * @param userName          用户名称
     * @param isBot             是否机器人
     * @param languageCode      语言编码
     * @return
     */
    public Boolean saveOrUpdateTgUser(String tgUserId,String firstName,String userLastName,String userName,int isBot,String languageCode);

    /**
     * 添加用户
     * @param userTgId        用户TgId
     * @param firstUserName   用户的姓
     * @param lastUserName    用户的名称
     * @param userName        用户名
     * @param isBot           是否机器人
     * @param languageCode    语言环境
     * @return                数据库影响行数
     */
    public int saveUser(String userTgId,String firstUserName,String lastUserName,String userName,Boolean isBot,String languageCode);

    /**
     * 查询用户是否有操作机器人权限
     * @param userTgId
     * @return
     */
//    public Boolean havaPermission(String userTgId);

    /**
     * 查询用户是否有操作机器人权限
     * @param tgUserName
     * @return
     */
    public TbUserManager selectByTgUserName(String tgUserName);


    /**
     * 试用机器人方法
     * @param userTgId
     * @return
     */
//    TbUserManager startBotSy(String userTgId,String firstUserName, String lastUserName, String userName, Boolean isBot, String languageCode);

    /**
     * 更新用户的权限过期时间
     *
     * @param tgUserId
     * @param date
     * @return
     */
//    int updateUserGrantEndDateTimeById(String tgUserId, Date date);

    /**
     * 检查用户的会有是否还有效
     * @param tgUserId
     * @return
     */
//    Boolean checkQunVipIsYx(String tgUserId);
}
