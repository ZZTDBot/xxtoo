package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tg.base.tb.compoent.GetBean;
import com.tg.base.tb.entity.TbUserManager;
import com.tg.base.tb.enum1.db.TbBotInstanceManagerBotQyStatus;
import com.tg.base.tb.exception.BaseException;
import com.tg.base.tb.entity.TbBotInstanceManager;
import com.tg.base.tb.enum1.db.TbBotInstanceManagerRunStatus;
import com.tg.base.tb.exception.BaseErrorEnum;
import com.tg.base.tb.mapper.TbBotInstanceManagerMapper;
import com.tg.base.tb.mapper.TbUserManagerMapper;
import com.tg.base.tb.service.TbBotInstanceManagerService;
import com.tg.base.tb.service.dto.req.TbBotInstanceManagerReqDto;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbBotInstanceManagerTgConfig;
import com.tg.base.tb.tg.session.TgBotRunSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.util.ArrayList;
import java.util.List;



/**
* @description 针对表【tb_bot_instance_manager(机器人实例管理)】的数据库操作Service实现
* @createDate 2022-10-22 12:53:50
*/
@Slf4j
@Service
public class TbBotInstanceManagerServiceImpl extends ServiceImpl<TbBotInstanceManagerMapper, TbBotInstanceManager>
    implements TbBotInstanceManagerService {

    @Autowired
    private TelegramBotsApi telegramBotsApi;

    @Autowired
    private TbUserManagerMapper tbUserManagerMapper;

    @Autowired
    private TbBotInstanceManagerTgConfig tbBotInstanceManagerTgConfig;

    @Autowired
    GetBean getBean;


    @Override
    public Boolean startAllBotByQyStatus(Integer botQyStatus,Class<? extends BuBaseBot> clazz) throws TelegramApiException, InstantiationException, IllegalAccessException {

        //1.获取所有机器人配置列表
        List<TbBotInstanceManager> list = baseMapper.selectBotByQyStatus(botQyStatus);

        //2.创建机器人对象
        List<BuBaseBot> buBaseBots = this.convertTgBots(list,clazz);
        logger.debug("机器人数量:{}   机器人实例列表  baseBotList：{}",buBaseBots.size(),buBaseBots);

        //3.注册机器人
        batchRegistBot(buBaseBots);

        //更新注册成功的机器人列表
        logger.info("当前完成注册的机器人信息startSuccessJzBaseBotMap：{}", TgBotRunSession.startSuccessJzBaseBotMap);

        return Boolean.TRUE;
    }





    @Override
    public PageInfo<TbBotInstanceManager> selectAllBotPageByConditionAdmin(TbBotInstanceManagerReqDto tbBotInstanceManagerRDto) {

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(tbBotInstanceManagerRDto.getCruPage(), tbBotInstanceManagerRDto.getPageSize());

        List<TbBotInstanceManager> tbBotInstanceManagers = baseMapper.selectAllBotPageByConditionAdmin(
                tbBotInstanceManagerRDto.getBotTypeId()
                , tbBotInstanceManagerRDto.getBotUserName()
                , tbBotInstanceManagerRDto.getBotToken()
                , tbBotInstanceManagerRDto.getBotNo()
                , tbBotInstanceManagerRDto.getBotQyStatus()
                , tbBotInstanceManagerRDto.getRunStatus()
                , tbBotInstanceManagerRDto.getStartCreateTime()
                , tbBotInstanceManagerRDto.getEndCreateTime());

        //用PageInfo对结果进行包装
        PageInfo page = new PageInfo(tbBotInstanceManagers);
        return page;
    }



    @Override
    @Transactional
    public Boolean upDownBot(Integer tbBotTypeId, Integer runStatus) throws TelegramApiException, ClassNotFoundException {

        if(runStatus == TbBotInstanceManagerRunStatus.RUNNING.getId()){
            logger.info("正在启动机器人tbBotTypeId:{}",tbBotTypeId);
            return startBotByBotId(tbBotTypeId);

        }else if (runStatus == TbBotInstanceManagerRunStatus.STOP.getId()){
            logger.info("正在停止机器人tbBotTypeId:{}",tbBotTypeId);
            return stopBotByBotId(tbBotTypeId);
        }

        return Boolean.FALSE;
    }

    @Override
    @Transactional
    public Boolean startBotByBotId(Integer botTypeId) throws TelegramApiException, ClassNotFoundException {
        TgBotRunSession tgBotRunSession = TgBotRunSession.startSuccessJzBaseBotMap.get(botTypeId);
        if(tgBotRunSession != null){
            BotSession botSession = tgBotRunSession.getBotSession();
            if(botSession != null && !botSession.isRunning()){
                botSession.start();
                baseMapper.updateQyStatusAndRunStatusByBotTypeId(botTypeId, TbBotInstanceManagerBotQyStatus.QI_YONG.getId() ,TbBotInstanceManagerRunStatus.RUNNING.getId());
            }
            logger.info("机器人启动成功,机器人ID：{}",botTypeId);
        }else{
            TbBotInstanceManager tbBotInstanceManager = baseMapper.selectById(botTypeId);

            Class<BuBaseBot> aClass = (Class<BuBaseBot>) Class.forName(tbBotInstanceManagerTgConfig.getBotFullClassName());
            registBot(convertTgBot(aClass, tbBotInstanceManager));
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean startBotByBotUserName(String botUserName) throws TelegramApiException, ClassNotFoundException {
        TbBotInstanceManager tbBotInstanceManager = baseMapper.selectByBotName(botUserName);
        //1.检查创建人是否存在
        if(tbBotInstanceManager == null){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_BOT_USERNAME_NOT_EXIST.getCode(),BaseErrorEnum.BOTMANAGER_BOT_USERNAME_NOT_EXIST.getMessage());
        }
        return startBotByBotId(tbBotInstanceManager.getTbBotInstanceId());
    }

    @Override
    public Boolean stopBotByBotUserName(String botUserName) {
        TbBotInstanceManager tbBotInstanceManager = baseMapper.selectByBotName(botUserName);
        //1.检查创建人是否存在
        if(tbBotInstanceManager == null){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_BOT_USERNAME_NOT_EXIST.getCode(),BaseErrorEnum.BOTMANAGER_BOT_USERNAME_NOT_EXIST.getMessage());
        }

        return stopBotByBotId(tbBotInstanceManager.getTbBotInstanceId());
    }

    @Override
    @Transactional
    public Boolean stopBotByBotId(Integer botTypeId) {
        TgBotRunSession tgBotRunSession = TgBotRunSession.startSuccessJzBaseBotMap.get(botTypeId);
        if(tgBotRunSession != null && tgBotRunSession.getBotSession() != null && tgBotRunSession.getBotSession().isRunning()){
            tgBotRunSession.getBotSession().stop();
            baseMapper.updateQyStatusAndRunStatusByBotTypeId(botTypeId, TbBotInstanceManagerBotQyStatus.TING_YONG.getId() ,TbBotInstanceManagerRunStatus.STOP.getId());
            TgBotRunSession.startSuccessJzBaseBotMap.remove(botTypeId);
            logger.info("机器人停止成功,机器人ID：{}",botTypeId);

        }
        return Boolean.TRUE;
    }

    @Override
    public int addBot(String userTgId,String botUserName, String botToken, String botNo) {

        Assert.hasText(userTgId, BaseErrorEnum.BOTMANAGER_USERTGID_NOT_NULL.getMessage());
        Assert.hasText(botUserName, BaseErrorEnum.BOTMANAGER_USERNAME_NOT_NULL.getMessage());
        Assert.hasText(botToken, BaseErrorEnum.BOTMANAGER_TOKEN_NOT_NULL.getMessage());
        Assert.hasText(botNo, BaseErrorEnum.BOTMANAGER_BOTNO_NOT_NULL.getMessage());

        TbBotInstanceManager tbBotInstanceManager = new TbBotInstanceManager();

        tbBotInstanceManager.setTgBotCreateUserId(userTgId);
        tbBotInstanceManager.setBotUserName(botUserName);
        tbBotInstanceManager.setBotToken(botToken);
        tbBotInstanceManager.setBotNo(botNo);

        tbBotInstanceManager.setBotQyStatus(0);
        tbBotInstanceManager.setRunStatus(0);

        return baseMapper.insert(tbBotInstanceManager);
    }

    @Override
    public int addBotNew(String createTgUserName, String botGsTgUserName, String botUserName, String botToken) {

        TbUserManager tbUserManagerCreate = tbUserManagerMapper.selectByTgUserName(createTgUserName);

        //1.检查创建人是否存在
        if(tbUserManagerCreate == null){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_CREATE_NOT_NULL.getCode(),BaseErrorEnum.BOTMANAGER_CREATE_NOT_NULL.getMessage());
        }

        TbUserManager tbUserManagerGs = tbUserManagerMapper.selectByTgUserName(botGsTgUserName);
        //2.检查归属人是否存在
        if(tbUserManagerGs == null){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_GS_NOT_NULL.getCode(),BaseErrorEnum.BOTMANAGER_GS_NOT_NULL.getMessage());
        }

        return addBotNewFromUserId(tbUserManagerCreate.getTgUserId(),tbUserManagerGs.getTgUserId(),botUserName,botToken);
    }

    @Override
    public int addBotNewFromUserId(String createTgUserId, String botGsTgUserId, String botUserName, String botToken) {

        TbBotInstanceManager selectByBotName = baseMapper.selectByBotName(botUserName);

        //1.检查机器人botName是否重复
        if(selectByBotName != null){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_BOT_USERNAME_NOT_CF.getCode(),BaseErrorEnum.BOTMANAGER_BOT_USERNAME_NOT_CF.getMessage());
        }

        TbBotInstanceManager selectByToken = baseMapper.selectByToken(botToken);
        //2.检查机器人Token是否重复
        if(selectByToken != null){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_BOT_TOKEN_NOT_CF.getCode(),BaseErrorEnum.BOTMANAGER_BOT_TOKEN_NOT_CF.getMessage());
        }

        TbBotInstanceManager tbBotInstanceManager = new TbBotInstanceManager();
        tbBotInstanceManager.setTgBotCreateUserId(createTgUserId);
        tbBotInstanceManager.setTgBotGsUserId(botGsTgUserId);
        tbBotInstanceManager.setBotUserName(botUserName);
        tbBotInstanceManager.setBotToken(botToken);

        return baseMapper.insert(tbBotInstanceManager);
    }

    @Override
    public int updateBot(Integer tbBotTypeId, String botUserName, String botToken, String botNo, String botRemark) {


        if (tbBotTypeId == null || tbBotTypeId == 0){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_TBBOTTYPEID_NOT_NULL.getCode(),BaseErrorEnum.BOTMANAGER_TBBOTTYPEID_NOT_NULL.getMessage());
        }

        Assert.hasText(botUserName, BaseErrorEnum.BOTMANAGER_USERNAME_NOT_NULL.getMessage());
        Assert.hasText(botToken, BaseErrorEnum.BOTMANAGER_TOKEN_NOT_NULL.getMessage());
        Assert.hasText(botNo, BaseErrorEnum.BOTMANAGER_BOTNO_NOT_NULL.getMessage());


        TbBotInstanceManager tbBotInstanceManager = new TbBotInstanceManager();


        tbBotInstanceManager.setTbBotInstanceId(tbBotTypeId);

        tbBotInstanceManager.setBotUserName(botUserName);
        tbBotInstanceManager.setBotToken(botToken);
        tbBotInstanceManager.setBotNo(botNo);
        tbBotInstanceManager.setBotRemark(botRemark);


        return baseMapper.updateById(tbBotInstanceManager);
    }

    @Override
    public int deleteBot(Integer tbBotTypeId) {
        if (tbBotTypeId == null || tbBotTypeId == 0){
            throw new BaseException(BaseErrorEnum.BOTMANAGER_TBBOTTYPEID_NOT_NULL.getCode(),BaseErrorEnum.BOTMANAGER_TBBOTTYPEID_NOT_NULL.getMessage());
        }

        return baseMapper.deleteById(tbBotTypeId);
    }

    @Override
    public int deleteBotByBotName(String botUserName) {
        return baseMapper.deleteBotByBotName(botUserName);
    }


    @Override
    public int startEndBot(Integer tbBotTypeId, Integer botQyStatus) {

        Assert.notNull(tbBotTypeId,BaseErrorEnum.BOTMANAGER_TBBOTTYPEID_NOT_NULL.getMessage());

        TbBotInstanceManager tbBotInstanceManagerSelect = baseMapper.selectById(tbBotTypeId);

        Assert.isTrue(tbBotInstanceManagerSelect.getRunStatus() == TbBotInstanceManagerRunStatus.STOP.getId(),BaseErrorEnum.BOTMANAGER_BOTRUNNING_NOT_STOP.getMessage());

        Assert.notNull(tbBotTypeId,BaseErrorEnum.BOTMANAGER_BOTQYSTATUS_NOT_NULL.getMessage());

        TbBotInstanceManager tbBotInstanceManager = new TbBotInstanceManager();
        tbBotInstanceManager.setTbBotInstanceId(tbBotTypeId);
        tbBotInstanceManager.setBotQyStatus(botQyStatus);

        return baseMapper.updateById(tbBotInstanceManager);
    }




    /**
     * 注册机器人
     * @param buBaseBot
     * @return
     * @throws TelegramApiException
     */
    public Boolean registBot(BuBaseBot buBaseBot) throws TelegramApiException {
        logger.info("当前正在注册的机器人信息buBaseBot：{}", buBaseBot);
        BotSession botSession = telegramBotsApi.registerBot(buBaseBot);
        TgBotRunSession.startSuccessJzBaseBotMap.put(buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),new TgBotRunSession(botSession, buBaseBot));

        baseMapper.updateQyStatusAndRunStatusByBotTypeId(buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),
                TbBotInstanceManagerBotQyStatus.QI_YONG.getId() ,TbBotInstanceManagerRunStatus.RUNNING.getId());

        return Boolean.TRUE;
    }

    @Override
    public Boolean isBotOwner(int botId,String userTgId) {

        TbBotInstanceManager tbBotInstanceManager = baseMapper.selectOneByBotIdAndUserTgId(botId,userTgId);

        if(tbBotInstanceManager == null){
            return Boolean.FALSE;
        }else {
            return Boolean.TRUE;
        }


    }

    @Override
    public List<TbBotInstanceManager> queryMyBotList(String userId) {
        return baseMapper.selectByGsUserId(userId);
    }

    /**
     * 批量注册机器人
     * @param buBaseBotList
     * @return
     * @throws TelegramApiException
     */
    private Boolean batchRegistBot(List<BuBaseBot> buBaseBotList) throws TelegramApiException {
        for (BuBaseBot buBaseBot : buBaseBotList) {
            registBot(buBaseBot);
        }
        return Boolean.TRUE;
    }


    /**
     * 配置转换成机器人对象
     * @param list
     * @return
     */
    private List<BuBaseBot> convertTgBots(List<TbBotInstanceManager> list,Class<? extends BuBaseBot> clazz) throws InstantiationException, IllegalAccessException {
        List<BuBaseBot> botInstanceList = new ArrayList<BuBaseBot>();
        for (TbBotInstanceManager tbBotInc : list ) {

            BuBaseBot newInstance = convertTgBot(clazz, tbBotInc);

            botInstanceList.add(newInstance);
        }
        return botInstanceList;
    }

    /**
     * 配置转换成机器人对象
     * @param clazz       机器人处理类
     * @param tbBotInc    机器人配置对象
     * @return
     */
    private BuBaseBot convertTgBot(Class<? extends BuBaseBot> clazz, TbBotInstanceManager tbBotInc) {
        logger.info("初始化机器人的全类名称:{}",tbBotInstanceManagerTgConfig.getBotFullClassName());
        BuBaseBot newInstance = getBean.getBean(clazz);
        newInstance.setTbBotInstanceManager(tbBotInc);
        return newInstance;
    }

}




