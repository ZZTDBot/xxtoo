package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbQunManager;
import com.tg.base.tb.enum1.db.TbQunManagerQunType;
import com.tg.base.tb.mapper.TbQunManagerMapper;
import com.tg.base.tb.service.TbQunManagerService;
import org.springframework.stereotype.Service;

/**
* @description 针对表【tb_qun_manager(tg群管理)】的数据库操作Service实现
* @createDate 2022-09-13 14:39:06
*/
@Service
public class TbQunManagerServiceImpl extends ServiceImpl<TbQunManagerMapper, TbQunManager>
    implements TbQunManagerService {

    @Override
    public Boolean saveOrUpdateTgQun(String tgChatId, String chatTitle, Integer qunType, String chatUserName) {

        TbQunManager tbQunManager = new TbQunManager();

        tbQunManager.setTgQunId(tgChatId);
        tbQunManager.setQunName(chatTitle);
        tbQunManager.setQunType(qunType);
        tbQunManager.setQunUserName(chatUserName);

        return this.saveOrUpdate(tbQunManager);
    }

    @Override
    public String selectQunNameByQunId(String tgQunId) {
        TbQunManager tbQunManager = baseMapper.selectById(tgQunId);
        if(tbQunManager == null){
            return "null";
        }

        Integer qunType = tbQunManager.getQunType();

        String context = tbQunManager.getQunName();

        if(qunType == TbQunManagerQunType.QUN_GROUP.getId()){
            context = context + "(" + TbQunManagerQunType.QUN_GROUP.getRemark() + ")";
        } else if(qunType == TbQunManagerQunType.QUN_SUPER_GROUP.getId()){
            context = context + "(" + TbQunManagerQunType.QUN_SUPER_GROUP.getRemark() + ")";
        } else if(qunType == TbQunManagerQunType.QUN_PRIVATE.getId()){
            context = context + "(" + TbQunManagerQunType.QUN_PRIVATE.getRemark() + ")";
        } else if(qunType == TbQunManagerQunType.QUN_CHANNEL.getId()){
            context = context + "(" + TbQunManagerQunType.QUN_CHANNEL.getRemark() + ")";
        }

        return context;
    }

}




