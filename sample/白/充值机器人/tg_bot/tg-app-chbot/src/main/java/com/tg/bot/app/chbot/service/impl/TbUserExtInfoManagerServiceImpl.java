package com.tg.bot.app.chbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.bot.app.chbot.entity.TbUserExtInfoManager;
import com.tg.bot.app.chbot.mapper.TbUserExtInfoManagerMapper;
import com.tg.bot.app.chbot.service.TbUserExtInfoManagerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @description 针对表【tb_user_ext_info_manager(用户拓展信息表管理)】的数据库操作Service实现
* @createDate 2023-10-08 19:20:32
*/
@Service
public class TbUserExtInfoManagerServiceImpl extends ServiceImpl<TbUserExtInfoManagerMapper, TbUserExtInfoManager>
    implements TbUserExtInfoManagerService {

    @Override
    public Boolean isBindedId(String tgUserId) {
        TbUserExtInfoManager tbUserExtInfoManager = baseMapper.selectById(tgUserId);
        if( tbUserExtInfoManager != null && StringUtils.hasText(tbUserExtInfoManager.getBindId()) ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean saveOrUpdateBindId(String tgUserId, String bindId) {
        TbUserExtInfoManager tbUserExtInfoManager = new TbUserExtInfoManager();
        tbUserExtInfoManager.setTgUserId(tgUserId);
        tbUserExtInfoManager.setBindId(bindId);
        return this.saveOrUpdate(tbUserExtInfoManager);
    }

    @Override
    public Boolean haveUserUsdInfo(Long tgUserId) {
        TbUserExtInfoManager tbUserExtInfoManager = baseMapper.selectById(tgUserId);
        if( tbUserExtInfoManager != null && StringUtils.hasText(tbUserExtInfoManager.getQuUsdtInfo()) ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean saveOrUpdateQuUsdInfo(String tgUserId, String bindQuUsdInfoText) {
        TbUserExtInfoManager tbUserExtInfoManager = new TbUserExtInfoManager();
        tbUserExtInfoManager.setTgUserId(tgUserId);
        tbUserExtInfoManager.setQuUsdtInfo(bindQuUsdInfoText);
        return this.saveOrUpdate(tbUserExtInfoManager);
    }

    @Override
    public String queryUsdtAccountInfoByTgUserId(Long tgUserId) {
        TbUserExtInfoManager tbUserExtInfoManager = baseMapper.selectById(tgUserId);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("usdt账户信息如下:\n");
        stringBuilder.append(tbUserExtInfoManager.getQuUsdtInfo());
        return stringBuilder.toString();
    }

    @Override
    public Boolean haveUserHwInfo(Long tgUserId) {
        TbUserExtInfoManager tbUserExtInfoManager = baseMapper.selectById(tgUserId);
        if( tbUserExtInfoManager != null && StringUtils.hasText(tbUserExtInfoManager.getQuHwInfo()) ){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean saveOrUpdateQuHwInfo(String tgUserId, String bindQuHwInfoText) {
        TbUserExtInfoManager tbUserExtInfoManager = new TbUserExtInfoManager();
        tbUserExtInfoManager.setTgUserId(tgUserId);
        tbUserExtInfoManager.setQuHwInfo(bindQuHwInfoText);
        return this.saveOrUpdate(tbUserExtInfoManager);
    }

    @Override
    public String queryHwAccountInfoByTgUserId(Long tgUserId) {
        TbUserExtInfoManager tbUserExtInfoManager = baseMapper.selectById(tgUserId);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("汇旺账户信息如下:\n");
        stringBuilder.append(tbUserExtInfoManager.getQuHwInfo());
        return stringBuilder.toString();
    }
}




