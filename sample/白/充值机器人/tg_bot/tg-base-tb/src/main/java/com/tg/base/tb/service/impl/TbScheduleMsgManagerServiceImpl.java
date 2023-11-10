package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.tg.base.tb.service.TbScheduleMsgManagerService;
import com.tg.base.tb.mapper.TbScheduleMsgManagerMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
* @description 针对表【tb_schedule_msg_manager(定时消息管理)】的数据库操作Service实现
* @createDate 2023-08-26 14:37:46
*/
@Service
public class TbScheduleMsgManagerServiceImpl extends ServiceImpl<TbScheduleMsgManagerMapper, TbScheduleMsgManager>
    implements TbScheduleMsgManagerService{

    @Override
    public int saveMsg(Long userTgId,Integer tbBotInstanceId, String targChatId, String targMsg) {
        TbScheduleMsgManager tbScheduleMsgManager = new TbScheduleMsgManager();

        tbScheduleMsgManager.setTgUserId(userTgId.toString());
        tbScheduleMsgManager.setTbBotInstanceId(tbBotInstanceId);
        tbScheduleMsgManager.setChatId(targChatId);
        tbScheduleMsgManager.setMsgContext(targMsg);

        return baseMapper.insert(tbScheduleMsgManager);
    }

    @Override
    public int saveOrUpdateMsgByQun(Long userTgId, Integer tbBotInstanceId, String targChatId, String targMsg) {
        TbScheduleMsgManager tbScheduleMsgManager = baseMapper.selectMsgByQunId(targChatId);

        if(tbScheduleMsgManager != null){
            tbScheduleMsgManager.setTgUserId(userTgId.toString());
            tbScheduleMsgManager.setChatId(targChatId);
            tbScheduleMsgManager.setTbBotInstanceId(tbBotInstanceId);
            tbScheduleMsgManager.setMsgContext(targMsg);
            return baseMapper.updateById(tbScheduleMsgManager);
        }else{
            tbScheduleMsgManager = new TbScheduleMsgManager();
            tbScheduleMsgManager.setTgUserId(userTgId.toString());
            tbScheduleMsgManager.setChatId(targChatId);
            tbScheduleMsgManager.setTbBotInstanceId(tbBotInstanceId);
            tbScheduleMsgManager.setMsgContext(targMsg);
            return baseMapper.insert(tbScheduleMsgManager);
        }
    }

    @Override
    public String queryListContextByUserTgId(Long userTgId) {
        List<TbScheduleMsgManager> list = baseMapper.selectListByUserTgId(userTgId.toString());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("创建的消息列表如下:\n");

        if(!CollectionUtils.isEmpty(list)){
            for (int i = 0 ; i < list.size() ; i++){
                TbScheduleMsgManager tbScheduleMsgManager = list.get(i);
                stringBuilder.append("第"+(i+1)+"条消息:\n");
                stringBuilder.append("<code>消息ID:" + tbScheduleMsgManager.getScheduleMsgId() + "\n");
                stringBuilder.append("会话ID:" + tbScheduleMsgManager.getQunChatId() + "\n");
                stringBuilder.append("消息内容:" + tbScheduleMsgManager.getMsgContext() + "</code>\n\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String queryMsgContextByQunId(String qunId) {
        TbScheduleMsgManager tbScheduleMsgManager = baseMapper.selectMsgByQunId(qunId);

        StringBuilder stringBuilder = new StringBuilder();

        if(tbScheduleMsgManager == null){
            stringBuilder.append("本群还没有设置定时推送消息!!!");
            return stringBuilder.toString();
        }

        stringBuilder.append(
                "本群设置的推送消息如下:\n" +
                        "消息内容:" + tbScheduleMsgManager.getMsgContext() + "\n"
        );

        return stringBuilder.toString();
    }

    @Override
    public String queryUpdateMsgContextByQunId(String qunId) {
        TbScheduleMsgManager tbScheduleMsgManager = baseMapper.selectMsgByQunId(qunId);
        StringBuilder stringBuilder = new StringBuilder();
        if(tbScheduleMsgManager == null){
            stringBuilder.append("本群还没有设置定时推送消息,请先创建消息");
            return stringBuilder.toString();
        }

        stringBuilder.append(
                "点击复制下方修改消息的模板:\n\n" +
                        "<code>修改群消息\n" +
                        "消息ID:"+tbScheduleMsgManager.getScheduleMsgId()+"\n" +
                        "群ID:" + tbScheduleMsgManager.getQunChatId() + "\n" +
                        "消息内容:" + tbScheduleMsgManager.getMsgContext() + "</code>"
        );

        return stringBuilder.toString();
    }

    @Override
    public int delByQunId(String qunId) {
        return baseMapper.deleteByQunId(qunId);
    }

    @Override
    public TbScheduleMsgManager queryMsgByQunId(String qunId) {
        return baseMapper.selectMsgByQunId(qunId);
    }

}




