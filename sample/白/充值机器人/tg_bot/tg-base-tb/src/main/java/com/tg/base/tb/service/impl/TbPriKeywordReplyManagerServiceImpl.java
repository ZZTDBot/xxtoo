package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tg.base.tb.entity.TbPriKeywordReplyManager;
import com.tg.base.tb.enum1.db.TbPriKeywordReplyManagerModeEnum;
import com.tg.base.tb.mapper.TbPriKeywordReplyManagerMapper;
import com.tg.base.tb.service.TbPriKeywordReplyManagerService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @description 针对表【tb_pri_keyword_reply_manager(私聊关键词回复表)】的数据库操作Service实现
* @createDate 2023-08-17 17:45:56
*/
@Service
public class TbPriKeywordReplyManagerServiceImpl extends ServiceImpl<TbPriKeywordReplyManagerMapper, TbPriKeywordReplyManager>
    implements TbPriKeywordReplyManagerService{

    @Override
    public Boolean saveOrUpdatePriKeyReply(Integer tbBotInstanceId, String key, String replyWord, int replyMode) {

        TbPriKeywordReplyManager tbPriKeywordReplyManager = this.selectTbPriKeyObjByBotidAndKey(tbBotInstanceId,key);

        if(tbPriKeywordReplyManager == null){
            TbPriKeywordReplyManager tbPriKeywordReplyManagerNew = new TbPriKeywordReplyManager();
            tbPriKeywordReplyManagerNew.setTbBotInstanceId(tbBotInstanceId);
            tbPriKeywordReplyManagerNew.setKeyWord(key);
            tbPriKeywordReplyManagerNew.setReplyContext(replyWord);
            tbPriKeywordReplyManagerNew.setReplyMode(replyMode);
            baseMapper.insert(tbPriKeywordReplyManagerNew);
        }else {
            tbPriKeywordReplyManager.setReplyContext(replyWord);
            baseMapper.updateById(tbPriKeywordReplyManager);
        }
        return Boolean.TRUE;
    }

    @Override
    public TbPriKeywordReplyManager selectTbPriKeyObjByBotidAndKey(Integer tbBotInstanceId, String key) {
        return baseMapper.selectByBotIdKeyReplyWord(tbBotInstanceId,key);
    }

    @Override
    public List<TbPriKeywordReplyManager> listPriKeyReplyByBotId(Integer tbBotInstanceId) {
        return baseMapper.selectPriKeyReplyByBotId(tbBotInstanceId);
    }

    @Override
    public String listPriKeyReplyStringByBotId(Integer tbBotInstanceId,int limit) {
        List<TbPriKeywordReplyManager> tbPriKeywordReplyManagers = this.listPriKeyReplyByBotId(tbBotInstanceId);
        StringBuilder stringBuilder = new StringBuilder();
        if(!CollectionUtils.isEmpty(tbPriKeywordReplyManagers)){

            if(tbPriKeywordReplyManagers.size() > limit){
                tbPriKeywordReplyManagers = tbPriKeywordReplyManagers.stream().limit(limit).collect(Collectors.toList());
            }

            stringBuilder.append("最近创建的" + tbPriKeywordReplyManagers.size() + "条关键词回复列表\n\n");

            for (int i = 0; i < tbPriKeywordReplyManagers.size() ; i++){
                TbPriKeywordReplyManager tbPriKeywordReplyManager = tbPriKeywordReplyManagers.get(i);
                stringBuilder.append("第"+(i + 1)+"条关键词回复\n");
                stringBuilder.append("关键词:" + tbPriKeywordReplyManager.getKeyWord() + "\n");
                stringBuilder.append("回复模式:" + TbPriKeywordReplyManagerModeEnum.getEnumById(tbPriKeywordReplyManager.getReplyMode()).getMsg()  + "\n");
                stringBuilder.append("回复:" + tbPriKeywordReplyManager.getReplyContext() + "\n\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public int deletePriKeyReply(Integer tbBotInstanceId, String key) {
        return baseMapper.deleteTbPriKeyObjByBotIdAndKey(tbBotInstanceId,key);
    }


}




