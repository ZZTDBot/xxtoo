package com.tg.base.tb.tg.service.common.impl;

import com.google.gson.Gson;
import com.tg.base.tb.service.TbSysParamManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.service.common.TbCommonDownButtonTgService;
import com.tg.bot.base.bot.enum1.MessageModeEnum;
import com.tg.bot.base.bot.utils.ButtonCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @description 机器人公共按钮服务类
 * @createDate 2023-08-17 17:45:56
 */
@Slf4j
@Service
public class TbCommonDownButtonTgServiceImpl implements TbCommonDownButtonTgService {

    Gson gson = new Gson();


    @Autowired
    TbSysParamManagerService tbSysParamManagerService;

    public static String buttonRowContextRegex = "^设置底部按钮(\r\n|\n)((\\[(.*?)\\](\r\n|\n)?)+)";//匹配每一行按钮的正则表达式
    public static String buttonRowRegex = "\\[(.*?)\\]";//匹配每一行按钮的正则表达式
    public static String buttonSplitKey = "\\$\\$";//一行按钮之间的分隔符
    public static String keyAndValueSplitKey = "&&";//一个按钮中,名称与内容的分隔符

    public static String SYS_PARAMS_DOWN_BUTTON = "sys.params.down.button";//  底部按钮系统参数的key
    public static String ACTION_SET_DOWM_BUTTON_SUCCESS = "设置底部按钮成功!!!";//  设置底部按钮成功



    @Override
    public void priMsgProccess(Update update, BuBaseBot buBaseBot) throws TelegramApiException, SchedulerException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        if(buBaseBot.isPrivateMsg(update)) {

            if(update.getMessage().hasText()){

                String text = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();

                //1. 设置底部按钮
                String buttonContext = ButtonCommonUtil.getTargetButtonRowContext(text,buttonRowContextRegex,2);
                if(StringUtils.hasText(buttonContext)){
                    // 1. 保存底部按钮到数据库
                    List<LinkedHashMap<String, String>> listLinkedMap = ButtonCommonUtil.parseTextButtonToListMap(buttonContext, buttonRowRegex, buttonSplitKey, keyAndValueSplitKey);
                    logger.info("管理员设置了底部按钮,底部按钮={}",listLinkedMap);
                    if(!CollectionUtils.isEmpty(listLinkedMap)){
                        tbSysParamManagerService.saveOrUpdateByKey(SYS_PARAMS_DOWN_BUTTON, gson.toJson(listLinkedMap));
                      //  1.1 更新底部按钮
                        List<List<String>> listListButtonNames = ButtonCommonUtil.getListListButtonNames(listLinkedMap);
                        ReplyKeyboardMarkup replyKeyboardMarkup = buBaseBot.createReplyKeyboardMarkup(Boolean.TRUE, listListButtonNames);
                        buBaseBot.sendReplyKeyboardMarkupMessage(replyKeyboardMarkup,chatId,ACTION_SET_DOWM_BUTTON_SUCCESS);
                    }
                }

                // 2. 启动更新按钮
                if("/start".equals(text)){
                    //1. 获取数据库底部按钮字符串
                    List<List<String>> queryDownButtonNameList =  tbSysParamManagerService.queryDownButtonNameList(SYS_PARAMS_DOWN_BUTTON);
                    //3. 创建底部按钮
                    ReplyKeyboardMarkup replyKeyboardMarkup = buBaseBot.createReplyKeyboardMarkup(Boolean.TRUE, queryDownButtonNameList);
                    //4. 设置到客户端
                    buBaseBot.sendReplyKeyboardMarkupMessage(replyKeyboardMarkup,chatId,ACTION_SET_DOWM_BUTTON_SUCCESS);
                }

                //3. 检测按钮关键词回复
                String queryDownButtonReplyContext = tbSysParamManagerService.queryDownButtonReplyContext(text,SYS_PARAMS_DOWN_BUTTON);
                if(StringUtils.hasText(queryDownButtonReplyContext)){
                    buBaseBot.sendMessage(update.getMessage().getChatId(),queryDownButtonReplyContext,update.getMessage().getMessageId(), MessageModeEnum.HTML.getType());
                }
            }
        }
    }
}
