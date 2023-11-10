package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbUserQunManager;
import com.tg.base.tb.mapper.TbUserManagerMapper;
import com.tg.base.tb.mapper.TbUserQunManagerMapper;
import com.tg.base.tb.service.TbUserQunManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author xxx
* @description 针对表【tb_user_qun_manager(用户_群_关系管理)】的数据库操作Service实现
* @createDate 2022-09-13 14:39:06
*/
@Service
public class TbUserQunManagerServiceImpl extends ServiceImpl<TbUserQunManagerMapper, TbUserQunManager>
    implements TbUserQunManagerService {

    @Autowired
    TbUserManagerMapper tbUserManagerMapper;

    @Override
    public int savaOrUpdateUserQunManager(String tgQunId, String tgUserId) {

        TbUserQunManager tbUserQunManagerSelectOne = baseMapper.queryOneByUserTgIdAndQunId(tgQunId, tgUserId);

        if(tbUserQunManagerSelectOne == null){
            TbUserQunManager tbUserQunManager = new TbUserQunManager();
            tbUserQunManager.setTgQunId(tgQunId);
            tbUserQunManager.setTgUserId(tgUserId);
            return baseMapper.insert(tbUserQunManager);
        }

        return 0;
    }

    @Override
    public int deleteUserQunManager(String tgQunId, String tgUserId) {
        return baseMapper.deleteByUserTgIdAndQunId(tgQunId, tgUserId);
    }

}




