package com.tg.bot.app.chbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.bot.app.chbot.entity.TbUserCunQuManager;
import com.tg.bot.app.chbot.service.TbUserCunQuManagerService;
import com.tg.bot.app.chbot.mapper.TbUserCunQuManagerMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【tb_user_cun_qu_manager(用户存取记录表管理)】的数据库操作Service实现
*/
@Service
public class TbUserCunQuManagerServiceImpl extends ServiceImpl<TbUserCunQuManagerMapper, TbUserCunQuManager>
    implements TbUserCunQuManagerService{

    @Override
    public void saveOrder(String tgUserCunQuUuid,Long tgUserId, Integer userMessageId, String kefuQunId, Integer kefuQunMessageId, String bindId,
                          int cunQuType, int accountType, String usdtAcountInfo, String hwAcountInfo, String fileId,
                          int orderStatus) {

        TbUserCunQuManager tbUserCunQuManager = new TbUserCunQuManager();

        tbUserCunQuManager.setTgUserCunQuUuid(tgUserCunQuUuid);
        tbUserCunQuManager.setTgUserId(tgUserId.toString());
        tbUserCunQuManager.setTgUserMsgId(userMessageId);
        tbUserCunQuManager.setKefuQunId(kefuQunId);
        tbUserCunQuManager.setQunMsgId(kefuQunMessageId);
        tbUserCunQuManager.setBindId(bindId);
        tbUserCunQuManager.setCunQuType(cunQuType);
        tbUserCunQuManager.setCunAccountType(accountType);
        tbUserCunQuManager.setCunCruUsdtAccountInfo(usdtAcountInfo);
        tbUserCunQuManager.setCunCruHwAccountInfo(hwAcountInfo);
        tbUserCunQuManager.setCunFileId(fileId);

        tbUserCunQuManager.setOrderStatus(orderStatus);

        baseMapper.insert(tbUserCunQuManager);

    }

    @Override
    public int saveOrderQu(String tgUserCunQuUuid, Long tgUserId, Integer userMessageId, String kefuQunId, Integer kefuQunMessageId, String bindId,int cunQuType,
                           int quAccountType, double quAmount, String quCruUsdtAccountInfo, String quCruHwAccountInfo, int orderStatus) {


        TbUserCunQuManager tbUserCunQuManager = new TbUserCunQuManager();

        tbUserCunQuManager.setTgUserCunQuUuid(tgUserCunQuUuid);
        tbUserCunQuManager.setTgUserId(tgUserId.toString());
        tbUserCunQuManager.setTgUserMsgId(userMessageId);
        tbUserCunQuManager.setKefuQunId(kefuQunId);
        tbUserCunQuManager.setQunMsgId(kefuQunMessageId);
        tbUserCunQuManager.setBindId(bindId);
        tbUserCunQuManager.setCunQuType(cunQuType);

        tbUserCunQuManager.setQuAccountType(quAccountType);
        tbUserCunQuManager.setQuAmount(quAmount);
        tbUserCunQuManager.setQuCruUsdtAccountInfo(quCruUsdtAccountInfo);
        tbUserCunQuManager.setQuCruHwAccountInfo(quCruHwAccountInfo);

        tbUserCunQuManager.setOrderStatus(orderStatus);

        return baseMapper.insert(tbUserCunQuManager);
    }

    @Override
    public int updateByOrderStatusByUuid(String uuid,int orderStatus) {
        TbUserCunQuManager byId = baseMapper.selectById(uuid);
        byId.setOrderStatus(orderStatus);

        return baseMapper.updateById(byId);
    }
}




