package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.exception.BaseException;
import com.tg.base.tb.entity.TbBotAdminManager;
import com.tg.base.tb.exception.BaseErrorEnum;
import com.tg.base.tb.mapper.TbBotAdminManagerMapper;
import com.tg.base.tb.service.TbBotAdminManagerService;
import com.tg.base.tb.service.TbBotInstanceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @description 针对表【tb_bot_admin_manager(机器人的管理员表)】的数据库操作Service实现
* @createDate 2023-06-02 17:04:25
*/
@Service
public class TbBotAdminManagerServiceImpl extends ServiceImpl<TbBotAdminManagerMapper, TbBotAdminManager>
    implements TbBotAdminManagerService{

    @Autowired
    private TbBotInstanceManagerService tbBotInstanceManagerService;

    @Override
    public int addBotAdmin(String opratorUserTgId,int botId,String userTgId) throws BaseException{
        autherPromise(opratorUserTgId, botId);

        //3.判断管理员有没有重复添加
        Boolean botAdmin2 = this.isBotAdmin(botId, userTgId);
        if(botAdmin2){
            throw new BaseException(BaseErrorEnum.EXIST_BOT_ADMIN_ERROR.getCode(),BaseErrorEnum.EXIST_BOT_ADMIN_ERROR.getMessage());
        }

        TbBotAdminManager tbBotAdminManager = new TbBotAdminManager();
        tbBotAdminManager.setTbBotInstanceId(botId);
        tbBotAdminManager.setTgUserId(userTgId);
        return baseMapper.insert(tbBotAdminManager);
    }

    /**
     * 判断当前操作人有没有机器人的管理权限
     * @param opratorUserTgId   操作人的ID
     * @param botId             机器人的ID
     */
    public Boolean autherPromise(String opratorUserTgId, int botId) throws BaseException{
        //1.查询当前操作人是不是机器人的所有人
        Boolean botOwner = tbBotInstanceManagerService.isBotOwner(botId, opratorUserTgId);
        if(botOwner){
            return Boolean.TRUE;
        }

        //2.查询操作人是不是本机器人管理员
        Boolean botAdmin = this.isBotAdmin(botId, opratorUserTgId);
        if(botAdmin){
            return Boolean.TRUE;
        }
        throw new BaseException(BaseErrorEnum.NOT_BOT_ADMIN_ERROR.getCode(),BaseErrorEnum.NOT_BOT_ADMIN_ERROR.getMessage());
    }

    @Override
    public int deleteBotAdmin(String opratorUserTgId, int botId, String userTgId) throws BaseException {
        autherPromise(opratorUserTgId, botId);
        return baseMapper.deleteByBotIdAndUserTgId(botId,userTgId);
    }

    /**
     * 判断是不是机器人的管理员
     * @param botId
     * @param opratorUserTgId
     * @return
     */
    public Boolean isBotAdmin(int botId, String opratorUserTgId) {
        TbBotAdminManager tbBotAdminManager = baseMapper.selectByBotIdAndOpratorUserTgId(botId, opratorUserTgId);
        if(tbBotAdminManager == null){
            return Boolean.FALSE;
        }else{
            return Boolean.TRUE;
        }
    }
}




