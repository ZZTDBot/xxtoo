package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.entity.TbPriKeywordReplyManager;
import com.tg.base.tb.enum1.db.TbPriKeywordReplyManagerModeEnum;
import com.tg.base.tb.service.TbPriKeywordReplyManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbPriKeywordReplyManagerTgConfig;
import com.tg.base.tb.tg.service.TbPriKeywordReplyManagerTgService;
import com.tg.base.tb.utils.RegxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.regex.Matcher;

/**
 * 机器人关键词处理类
 */
@Service
@Slf4j
public class TbPriKeywordReplyManagerTgServiceImpl implements TbPriKeywordReplyManagerTgService {

    @Autowired
    TbPriKeywordReplyManagerTgConfig tbPriKeywordReplyManagerTgConfig;

    @Autowired
    TbPriKeywordReplyManagerService tbPriKeywordReplyManagerService;

    @Override
    public void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException {
//        创建关键词回复
//        关键词:哈哈哈
//        回复:呵呵
        logger.info("私聊-关键词回复模块 ");
        if(buBaseBot.isPrivateMsg(update)){
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            //有文本消息
            if(StringUtils.hasText(text)){

                //1.创建关键词回复
                Matcher matcher = RegxUtils.matcherRegx(text, tbPriKeywordReplyManagerTgConfig.getCreatePriKeyReplyRegx());
                if(matcher.find()){
                    String key = matcher.group(1);
                    String replyMode = matcher.group(2);
                    String replyWord = matcher.group(3);
                    logger.info("私聊-关键词回复模块-创建-key:{}-replyWord:{}",key,replyWord);

                    if(tbPriKeywordReplyManagerService.saveOrUpdatePriKeyReply(
                            buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),
                            key,
                            replyWord,
                            Integer.parseInt(replyMode)
                    )){
                        buBaseBot.sendMessage(chatId,"创建成功!!!\n关键词:"+key + "\n回复模式:" + replyMode + "\n回复内容:" + replyWord);
                    }
                }

                //2.查询关键词回复
                TbPriKeywordReplyManager tbPriKeywordReplyManager = tbPriKeywordReplyManagerService.selectTbPriKeyObjByBotidAndKey(buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(), text);
                if(tbPriKeywordReplyManager != null){
                    Integer replyMode = tbPriKeywordReplyManager.getReplyMode();
                    if(TbPriKeywordReplyManagerModeEnum.REPLYMODE_PTFH.getId() == replyMode){
                        buBaseBot.sendMessage(chatId,tbPriKeywordReplyManager.getReplyContext());
                    }else if(TbPriKeywordReplyManagerModeEnum.REPLYMODE_HFMS.getId() == replyMode){
                        buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),tbPriKeywordReplyManager.getReplyContext());
                    }else{
                        logger.info("私聊-关键词回复-未知得回复模式...");
                    }
                }

                //3.查看关键词列表
                Matcher listPriKeyReplyRegxMatcher = RegxUtils.matcherRegx(text, tbPriKeywordReplyManagerTgConfig.getListPriKeyReplyRegx());
                if(listPriKeyReplyRegxMatcher.find()){
                    String listPriKeyReplyStringByBotId = tbPriKeywordReplyManagerService.listPriKeyReplyStringByBotId(buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(), tbPriKeywordReplyManagerTgConfig.getListPriKeyReplyLimit());
                    buBaseBot.sendReplyMessage(chatId,update.getMessage().getMessageId(),listPriKeyReplyStringByBotId);
                }

                //4.删除关键词回复
                Matcher deleteMatcher = RegxUtils.matcherRegx(text, tbPriKeywordReplyManagerTgConfig.getDeletePriKeyReplyRegx());
                if(deleteMatcher.find()){
                    String key = deleteMatcher.group(1);
                    logger.info("私聊-关键词回复模块-删除-key:{}",key);

                    tbPriKeywordReplyManagerService.deletePriKeyReply(
                            buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),
                            key);

                    buBaseBot.sendMessage(chatId,"删除成功!!!\n关键词:" + key );

                }

            }
        }
    }
}
