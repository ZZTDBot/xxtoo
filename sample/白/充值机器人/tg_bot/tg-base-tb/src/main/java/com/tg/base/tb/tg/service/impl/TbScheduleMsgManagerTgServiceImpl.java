package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.entity.TbScheduleMsgManager;
import com.tg.base.tb.enum1.db.TbPriKeywordReplyManagerModeEnum;
import com.tg.base.tb.service.TbScheduleMsgManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbScheduleMsgManagerConfig;
import com.tg.base.tb.tg.service.TbScheduleMsgManagerTgService;
import com.tg.base.tb.utils.RegxUtils;
import com.tg.bot.base.bot.enum1.MessageModeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;

/**
 * 调度消息设置类
 */
@Component
@Slf4j
public class TbScheduleMsgManagerTgServiceImpl implements TbScheduleMsgManagerTgService {

    @Autowired
    private TbScheduleMsgManagerConfig tbScheduleMsgManagerConfig;

    @Autowired
    private TbScheduleMsgManagerService tbScheduleMsgManagerService;


    @Override
    public void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException {

        logger.info("进入配置调度消息的方法类....");

        if(buBaseBot.isPrivateMsg(update)) {

            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            //有文本消息
            if(StringUtils.hasText(text)){

                //1.捕获创建周期调度消息的
                Matcher createMsgmatcher = RegxUtils.matcherRegx(text, tbScheduleMsgManagerConfig.getCreateMsgRegx());
                if(createMsgmatcher.find()){
                    StringBuilder sb = new StringBuilder();
                    sb.append("点击复制下方创建模板\n\n");
                    sb.append("<code>" +
                            "创建群消息\n" +
                            "群ID:" + "\n");
                    sb.append("消息内容:</code>" );

                    buBaseBot.sendMessage(update.getMessage().getChatId(),sb.toString(), MessageModeEnum.HTML.getType());
                }

                //1.捕获创建周期调度消息的
                Matcher matcher = RegxUtils.matcherRegx(text, tbScheduleMsgManagerConfig.getMsgZqRegx());
                if(matcher.find()){
                    String targChatId = matcher.group(1);
                    String targMsg = matcher.group(2);
                    tbScheduleMsgManagerService.saveOrUpdateMsgByQun(
                            update.getMessage().getFrom().getId(),
                            buBaseBot.getTbBotInstanceManager().getTbBotInstanceId(),
                            targChatId,
                            targMsg
                    );
                    buBaseBot.sendMessage(chatId,"创建消息成功!!!", MessageModeEnum.HTML.getType());
                }

                //2.查看消息列表
                Matcher queryMsgRegxMatch = RegxUtils.matcherRegx(text, tbScheduleMsgManagerConfig.getQueryMsgRegx());
                if(queryMsgRegxMatch.find()){
                    String queryListContext = tbScheduleMsgManagerService.queryListContextByUserTgId(update.getMessage().getFrom().getId());
                    buBaseBot.sendMessage(chatId,queryListContext, MessageModeEnum.HTML.getType());
                }

                //3.删除消息
                Matcher delMsgRegxMatch = RegxUtils.matcherRegx(text, tbScheduleMsgManagerConfig.getDelMsgRegx());
                if(delMsgRegxMatch.find()){
                    String msgId = delMsgRegxMatch.group(1);
                    boolean removeById = tbScheduleMsgManagerService.removeById(Integer.valueOf(msgId));
                    if(removeById) buBaseBot.sendMessage(chatId,"删除成功 !!!");
                }

                //4.修改消息
                Matcher upMsgRegxMatch = RegxUtils.matcherRegx(text, tbScheduleMsgManagerConfig.getUpMsgRegx());
                if(upMsgRegxMatch.find()){
                    String msgId = upMsgRegxMatch.group(1);
                    String chatIdNew = upMsgRegxMatch.group(2);
                    String msgContext = upMsgRegxMatch.group(3);

                    TbScheduleMsgManager tbScheduleMsgManager = new TbScheduleMsgManager();
                    tbScheduleMsgManager.setScheduleMsgId(Integer.valueOf(msgId));
                    tbScheduleMsgManager.setChatId(chatIdNew);
                    tbScheduleMsgManager.setMsgContext(msgContext);

                    boolean updateById = tbScheduleMsgManagerService.updateById(tbScheduleMsgManager);
                    if(updateById) buBaseBot.sendMessage(chatId,"更新成功 !!!");

                }


            }

        }

    }
}
