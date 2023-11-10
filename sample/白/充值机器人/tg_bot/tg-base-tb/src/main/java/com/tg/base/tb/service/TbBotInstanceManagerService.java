package com.tg.base.tb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.tg.base.tb.entity.TbBotInstanceManager;
import com.tg.base.tb.service.dto.req.TbBotInstanceManagerReqDto;
import com.tg.base.tb.tg.bot.BuBaseBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


/**
* @description 针对表【tb_bot_instance_manager(机器人实例管理)】的数据库操作Service
* @createDate 2022-10-22 12:53:50
*/
public interface TbBotInstanceManagerService extends IService<TbBotInstanceManager> {

    /**
     * 根据条件分页查询机器人列表---管理员分页查询
     * @param tbBotInstanceManagerRDto
     * @return
     */
    PageInfo<TbBotInstanceManager> selectAllBotPageByConditionAdmin(TbBotInstanceManagerReqDto tbBotInstanceManagerRDto);

    /**
     * 启动机器人-根据机器人ID
     * @param botTypeId   机器人ID
     * @return
     */
    Boolean startBotByBotId(Integer botTypeId) throws TelegramApiException, ClassNotFoundException;

    /**
     * 启动机器人-根据机器人用户名
     * @param botUserName
     * @return
     */
    Boolean startBotByBotUserName(String botUserName) throws TelegramApiException, ClassNotFoundException;

    /**
     * 启动机器人-根据机器人用户名
     * @param botUserName
     * @return
     */
    Boolean stopBotByBotUserName(String botUserName);

    /**
     * 启动所有是启用状态的机器人
     * @param botQyStatus      启用状态
     * @param clazz            机器人的实例全类名
     * @return
     */
    Boolean startAllBotByQyStatus(Integer botQyStatus,Class<? extends BuBaseBot> clazz) throws TelegramApiException, InstantiationException, IllegalAccessException;

    /**
     * 关闭机器人
     * @param botTypeId
     * @return
     */
    Boolean stopBotByBotId(Integer botTypeId);

    /**
     * 添加机器人
     * @param userTgId       用户的飞机ID
     * @param botUserName    机器人用户名
     * @param botToken       机器人token
     * @param botNo          机器人型号
     * @return
     */
    int addBot(String userTgId,String botUserName, String botToken, String botNo);

    /**
     * 添加机器人
     * @param createTgUserName      机器人的创建人用户名
     * @param botGsTgUserName       机器人的归属人用户名
     * @param botUserName           机器人用户名
     * @param botToken              机器人token
     * @return
     */
    int addBotNew(String createTgUserName,String botGsTgUserName,String botUserName, String botToken);

    /**
     * 添加机器人
     * @param createTgUserId        机器人的创建人用户ID
     * @param botGsTgUserId         机器人的归属人用户ID
     * @param botUserName           机器人用户名
     * @param botToken              机器人token
     * @return
     */
    int addBotNewFromUserId(String createTgUserId,String botGsTgUserId,String botUserName, String botToken);

    /**
     * 更新机器人
     * @param tbBotTypeId    机器人ID
     * @param botUserName    机器人用户名
     * @param botToken       机器人token
     * @param botNo          机器人型号
     * @param botRemark      机器人注释
     * @return
     */
    int updateBot(Integer tbBotTypeId, String botUserName, String botToken, String botNo, String botRemark);

    /**
     * 删除机器人
     * @param tbBotTypeId  机器人ID
     * @return
     */
    int deleteBot(Integer tbBotTypeId);

    /**
     * 删除机器人
     * @param botUserName  机器人名称
     * @return
     */
    int deleteBotByBotName(String botUserName);

    /**
     * 启用/停用-机器人
     * @param tbBotTypeId    机器人ID
     * @param botQyStatus    启用/停用 ID
     * @return
     */
    int startEndBot(Integer tbBotTypeId, Integer botQyStatus);

    /**
     * 启用/停用-机器人
     * @param tbBotTypeId    机器人ID
     * @param runStatus      启用/停用 机器人运行状态(1: 运行中 0:停止)
     * @return
     */
    Boolean upDownBot(Integer tbBotTypeId, Integer runStatus) throws TelegramApiException, ClassNotFoundException;


    /**
     * 注册机器人
     * @param buBaseBot
     * @return
     * @throws TelegramApiException
     */
    public Boolean registBot(BuBaseBot buBaseBot) throws TelegramApiException;


    /**
     * 查询用户是不是机器人的所有人
     * @param botId           机器人ID
     * @param userTgId        用户ID
     * @return
     */
    public Boolean isBotOwner(int botId,String userTgId);

    /**
     * 查询我的机器人列表
     * @param userId    用户的飞机ID
     * @return
     */
    List<TbBotInstanceManager> queryMyBotList(String userId);
}
