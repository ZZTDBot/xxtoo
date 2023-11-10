package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbUserManager;
import com.tg.base.tb.mapper.TbUserManagerMapper;
import com.tg.base.tb.service.TbUserManagerService;
import org.springframework.stereotype.Service;

/**
* @description 针对表【tb_user_manager(用户管理)】的数据库操作Service实现
* @createDate 2022-09-13 14:39:06
*/
@Service
public class TbUserManagerServiceImpl extends ServiceImpl<TbUserManagerMapper, TbUserManager>
    implements TbUserManagerService {

    @Override
    public Boolean saveOrUpdateTgUser(String tgUserId,String firstName,String userLastName,String userName,int isBot,String languageCode) {

        TbUserManager tbUserManager = new TbUserManager();
        tbUserManager.setTgUserId(tgUserId);
        tbUserManager.setUserFirstName(firstName);
        tbUserManager.setUserLastName(userLastName);
        tbUserManager.setUserName(userName);
        tbUserManager.setIsBot(isBot);
        tbUserManager.setLanguageCode(languageCode);

        return this.saveOrUpdate(tbUserManager);
    }

    @Override
    public int saveUser(String userTgId,String firstUserName, String lastUserName, String userName, Boolean isBot, String languageCode) {

        TbUserManager tbUserManager = new TbUserManager();

        tbUserManager.setTgUserId(userTgId);
        tbUserManager.setUserFirstName(firstUserName);
        tbUserManager.setUserLastName(lastUserName);
        tbUserManager.setUserName(userName);

        if( isBot ) {
            tbUserManager.setIsBot(1);
        } else {
            tbUserManager.setIsBot(0);
        }

        tbUserManager.setLanguageCode(languageCode);

        return baseMapper.insert(tbUserManager);

    }

//    @Override
//    public Boolean havaPermission(String userTgId) {
//        TbUserManager tbUserManager = baseMapper.selectById(userTgId);
//        Date grantEndDateTime = tbUserManager.getGrantEndDateTime();
//        if(grantEndDateTime != null && grantEndDateTime.getTime() > new Date().getTime()){
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }

    @Override
    public TbUserManager selectByTgUserName(String tgUserName) {
        return baseMapper.selectByTgUserName(tgUserName);
    }

//    @Override
//    public TbUserManager startBotSy(String userTgId,String firstUserName, String lastUserName, String userName, Boolean isBot, String languageCode) {
//
//        TbUserManager tbUserManager = baseMapper.selectById(userTgId);
//
//        if (tbUserManager == null) {
//            int saveUser = saveUser(userTgId, firstUserName, lastUserName, userName, isBot, lastUserName);
//        }
//
//        TbUserManager tbUserManager1 = baseMapper.selectById(userTgId);
//        Date grantEndDateTime = tbUserManager1.getGrantEndDateTime();
//
//        if (grantEndDateTime == null) {
//            Date date = DateUtils.addHours(new Date(), 6);
//            tbUserManager1.setGrantEndDateTime(date);
//            tbUserManager1.setUpdateTime(new Date());
//
//            baseMapper.updateById(tbUserManager1);
//        }
//
//        return tbUserManager1;
//    }

//    @Override
//    public int updateUserGrantEndDateTimeById(String tgUserId, Date date) {
//        TbUserManager tbUserManager = new TbUserManager();
//
//        tbUserManager.setTgUserId(tgUserId);
//        tbUserManager.setGrantEndDateTime(date);
//        tbUserManager.setUpdateTime(new Date());
//
//        return baseMapper.updateById(tbUserManager);
//    }

//    @Override
//    public Boolean checkQunVipIsYx(String tgUserId) {
//        TbUserManager tbUserManager = baseMapper.selectById(tgUserId);
//        if(tbUserManager != null && tbUserManager.getGrantEndDateTime() != null && tbUserManager.getGrantEndDateTime().getTime() > new Date().getTime()){
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//    }
}


