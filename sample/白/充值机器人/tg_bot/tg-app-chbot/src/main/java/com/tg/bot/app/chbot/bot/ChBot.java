package com.tg.bot.app.chbot.bot;

import com.tg.base.tb.entity.TbSysParamManager;
import com.tg.base.tb.service.TbSysParamManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.utils.RegxUtils;
import com.tg.bot.app.chbot.config.SysConfig;
import com.tg.bot.app.chbot.config.SysDbParamsConstance;
import com.tg.bot.app.chbot.entity.TbUserCunQuManager;
import com.tg.bot.app.chbot.entity.TbUserExtInfoManager;
import com.tg.bot.app.chbot.enum1.TbUserCunQuManagerAccountType;
import com.tg.bot.app.chbot.enum1.TbUserCunQuManagerCunQuType;
import com.tg.bot.app.chbot.enum1.TbUserCunQuManagerOrderStatus;
import com.tg.bot.app.chbot.service.TbUserCunQuManagerService;
import com.tg.bot.app.chbot.service.TbUserExtInfoManagerService;
import com.tg.bot.base.bot.enum1.MessageModeEnum;
import com.tg.bot.base.bot.enum1.TgPhotoSizePaiXuMode;
import com.tg.bot.base.bot.utils.InlineKeyboardButtonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

@Component
@Slf4j
public class ChBot extends BuBaseBot {

    @Autowired
    SysConfig sysConfig;

    @Autowired
    TbSysParamManagerService tbSysParamManagerService;

    @Autowired
    TbUserExtInfoManagerService tbUserExtInfoManagerService;

    @Autowired
    TbUserCunQuManagerService tbUserCunQuManagerService;

    /**
     * 用户路径保存
     */
    HashMap<String,String> userAndPath = new HashMap<String,String>();


    @Override
    public void onUpdateReceived(Update update) {
        super.onUpdateReceived(update);

        logger.info("数据内容update:" + update);
        try {

            //1/转发消息到监控群
            forwordMsg(update);

            if(isPrivateMsg(update)){
                String text = update.getMessage().getText();

                if("/start".equals(text)){

                    SetMyCommands setMyCommands = sysConfig.getSetMyCommands();
                    execute(setMyCommands);

                    homeButton(update.getMessage().getChatId(),null);
                    userAndPath.remove(update.getMessage().getFrom().getId());
                }

                if(StringUtils.hasText(text)){
                    //1.设置hw存公告
                    Matcher cunHwTextRegx = RegxUtils.matcherRegx(text, sysConfig.getCunHwTextRegx());
                    if(cunHwTextRegx.find()){
                        String hwContext = cunHwTextRegx.group(2);
                        tbSysParamManagerService.saveOrUpdateByKey(SysDbParamsConstance.cunKanHwGg, hwContext);
                        sendReplyMessage(update.getMessage().getChatId(),update.getMessage().getMessageId(),sysConfig.getSetSuccess());
                    }

                    //2.设置usdt存公告
                    Matcher cunUsdtTextRegx = RegxUtils.matcherRegx(text, sysConfig.getCunUsdtTextRegx());
                    if(cunUsdtTextRegx.find()){
                        String usdtContext = cunUsdtTextRegx.group(2);
                        tbSysParamManagerService.saveOrUpdateByKey(SysDbParamsConstance.cunKanUsdGg, usdtContext);
                        sendReplyMessage(update.getMessage().getChatId(),update.getMessage().getMessageId(),sysConfig.getSetSuccess());
                    }

                    //3.设置客服群ID
                    Matcher keFuQunIdTextRegx = RegxUtils.matcherRegx(text, sysConfig.getKeFuQunIdTextRegx());
                    if(keFuQunIdTextRegx.find()){
                        String kefuQunId = keFuQunIdTextRegx.group(1);
                        tbSysParamManagerService.saveOrUpdateByKey(SysDbParamsConstance.kefuQunId, kefuQunId);
                        sendReplyMessage(update.getMessage().getChatId(),update.getMessage().getMessageId(),sysConfig.getSetSuccess());
                    }

                    //4.设置主页欢迎语
                    Matcher homeTextRegx = RegxUtils.matcherRegx(text, sysConfig.getHomeTextRegx());
                    if(homeTextRegx.find()){
                        String homeText = homeTextRegx.group(2);
                        tbSysParamManagerService.saveOrUpdateByKey(SysDbParamsConstance.chAppHomeText, homeText);
                        sendReplyMessage(update.getMessage().getChatId(),update.getMessage().getMessageId(),sysConfig.getSetSuccess());
                    }

                    //5.设置监控会话ID
                    Matcher jianKongChatIdRegx = RegxUtils.matcherRegx(text, sysConfig.getJianKongChatIdRegx());
                    if(jianKongChatIdRegx.find()){
                        String jianKongChatId = jianKongChatIdRegx.group(1);
                        tbSysParamManagerService.saveOrUpdateByKey(SysDbParamsConstance.chAppJianKongChatId, jianKongChatId);
                        sendReplyMessage(update.getMessage().getChatId(),update.getMessage().getMessageId(),sysConfig.getSetSuccess());
                    }

                }

                //5.获取用户当前的操作路径
                String userPath = userAndPath.get(update.getMessage().getFrom().getId().toString());
                if(StringUtils.hasText(userPath)){

                    //4.0.1 用户在进行账户ID绑定
                    if(sysConfig.getCunKuanBindIdCallData().equals(userPath)){

                        logger.info("用户在进行账户ID绑定,用户ID：{}",update.getMessage().getFrom().getId());
                        // 创建返回和关闭按钮
                        List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                                sysConfig.getFhButtonText(),
                                sysConfig.getWelComPath(),
                                sysConfig.getButtonCloseText(),
                                sysConfig.getButtonCloseTextData()
                        );

                        tbUserExtInfoManagerService.saveOrUpdateBindId(update.getMessage().getFrom().getId().toString(),update.getMessage().getText());

                        sendInLineKeyBoardButtonMessage(
                                update.getMessage().getChatId().toString(),
                                InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons),
                                sysConfig.getSetSuccess(),
                                MessageModeEnum.HTML.getType()
                        );
                        userAndPath.remove(update.getMessage().getFrom().getId().toString());

                    }

                    //4.0.2 用户在进行上传usdt存款截图
                    if( sysConfig.getCunKuanUsdYiZhuanPath().equals(userPath) || sysConfig.getCunKuanHwYiZhuanPath().equals(userPath) ) {

                        logger.info("用户在进行上传存款截图,用户ID：{}",update.getMessage().getFrom().getId());
                        // 创建返回和关闭按钮
                        List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                                sysConfig.getFhButtonText(),
                                sysConfig.getCunKuanUsdYiZhuanPath(),
                                sysConfig.getButtonCloseText(),
                                sysConfig.getButtonCloseTextData()
                        );

                        List<PhotoSize> photo = update.getMessage().getPhoto();
                        if(photo == null){
                            //没有上传截图
                            sendInLineKeyBoardButtonReplyMessage(
                                    update.getMessage().getChatId(),
                                    update.getMessage().getMessageId(),
                                    InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons),
                                    sysConfig.getCunKuanUsdYiZhuanContext(),
                                    MessageModeEnum.HTML.getType()
                            );
                            return;
                        }

                        TbUserExtInfoManager userExtInfoManager = tbUserExtInfoManagerService.getById(update.getMessage().getFrom().getId());

                        TbSysParamManager tbSysParamManager = tbSysParamManagerService.queryByKey(SysDbParamsConstance.kefuQunId);


                        //转发消息到客服群
                        List<PhotoSize> photos = update.getMessage().getPhoto();
                        PhotoSize photoSize = getPhotoSize(photos, TgPhotoSizePaiXuMode.TG_PHOTO_SIZE_PAIXU_MODE_DESC);

                        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                        String cunKuanHwYiZhuanContextReplyQun = String.format(sysConfig.getCunKuanHwYiZhuanContextReplyQun(), userExtInfoManager.getBindId());

                        // -- 创建群里的的底部按钮
                        List<InlineKeyboardButton> inlineKeyboardButtons1 = sysConfig.getInlineKeyboardOrderStatus(uuid,TbUserCunQuManagerOrderStatus.YI_TI_JIAO);
                        InlineKeyboardMarkup inlineKeyboardMarkup2 = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons1);

                        Message messageQun = sendPhotoSizeInLineKeyBoardButtonMessage(photoSize, tbSysParamManager.getValue(), inlineKeyboardMarkup2,cunKuanHwYiZhuanContextReplyQun);

                        //回复给用户
                        TbUserCunQuManagerOrderStatus tbUserCunQuManagerOrderStatusByCode =
                                TbUserCunQuManagerOrderStatus.getTbUserCunQuManagerOrderStatusByCode(TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode() + "");

                        InlineKeyboardButton callDataInlineKeyboardButton = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                                sysConfig.getOrderStatusButtonText() + tbUserCunQuManagerOrderStatusByCode.getMsg(),
                                sysConfig.getOrderStatusButtonPath()
                        );
                        InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardMarkup(callDataInlineKeyboardButton);
                        Message sendReplyMessage = sendInLineKeyBoardButtonReplyMessage(
                                    update.getMessage().getChatId(),
                                    update.getMessage().getMessageId(),
                                    inlineKeyboardMarkup,
                                    sysConfig.getTiJiaoSuccess()
                                );





                        if(sysConfig.getCunKuanUsdYiZhuanPath().equals(userPath)){
                            TbSysParamManager tbSysParamManagerUsdt = tbSysParamManagerService.queryByKey(SysDbParamsConstance.cunKanUsdGg);
                            tbUserCunQuManagerService.saveOrder(
                                    uuid,
                                    update.getMessage().getChatId(),
                                    sendReplyMessage.getMessageId(),
                                    tbSysParamManager.getValue(),
                                    messageQun.getMessageId(),
                                    userExtInfoManager.getBindId(),
                                    TbUserCunQuManagerCunQuType.CUN.getCode(),
                                    TbUserCunQuManagerAccountType.USDT.getCode(),
                                    tbSysParamManagerUsdt.getValue(),
                                    null,
                                    photoSize.getFileId(),
                                    TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode()
                            );
                        }else if(sysConfig.getCunKuanHwYiZhuanPath().equals(userPath)){
                            TbSysParamManager tbSysParamManagerHw = tbSysParamManagerService.queryByKey(SysDbParamsConstance.cunKanHwGg);
                            tbUserCunQuManagerService.saveOrder(
                                    uuid,
                                    update.getMessage().getChatId(),
                                    sendReplyMessage.getMessageId(),
                                    tbSysParamManager.getValue(),
                                    messageQun.getMessageId(),
                                    userExtInfoManager.getBindId(),
                                    TbUserCunQuManagerCunQuType.CUN.getCode(),
                                    TbUserCunQuManagerAccountType.HW.getCode(),
                                    null,
                                    tbSysParamManagerHw.getValue(),
                                    photoSize.getFileId(),
                                    TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode()
                            );
                        }



                        userAndPath.remove(update.getMessage().getFrom().getId().toString());
                    }




                    //4.1 如果在进行usdt取款操作
                    if(sysConfig.getQuKuanUsdPath().equals(userPath)){
                        logger.info("用户正在进行usdt取款操作,用户ID：{}",update.getMessage().getFrom().getId());

                        //4.1.1 判断是否符合输入格式
                        Matcher checkAmountRegx = RegxUtils.matcherRegx(text, sysConfig.getCheckAmountRegx());
                        if(!checkAmountRegx.find()){//输入格式不正确

                            // 创建返回和关闭按钮
                            List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                                    sysConfig.getFhButtonText(),
                                    sysConfig.getQuKuanUsdPath(),
                                    sysConfig.getButtonCloseText(),
                                    sysConfig.getButtonCloseTextData()
                            );

                            sendInLineKeyBoardButtonMessage(
                                    update.getMessage().getChatId().toString(),
                                    InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons),
                                    sysConfig.getQuKuanUsdContextInputAmountFaild(),
                                    MessageModeEnum.HTML.getType()
                            );

                        }else {

                            String amount = checkAmountRegx.group(1);
                            String usdtAccountInfo = tbUserExtInfoManagerService.queryUsdtAccountInfoByTgUserId(update.getMessage().getFrom().getId());

                            InlineKeyboardButton queren = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                                    sysConfig.getQuKuanUsdQueRenButtonText(),
                                    String.format(sysConfig.getQuKuanUsdQueRenButtonCallData(),amount)
                            );

                            InlineKeyboardButton fh = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getFhButtonText(), sysConfig.getQuKuanUsdPath());

                            sendInLineKeyBoardButtonMessage(
                                    update.getMessage().getChatId().toString(),
                                    InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(InlineKeyboardButtonUtil.createInlineKeyboardButtons(queren,fh)),
                                    String.format(sysConfig.getQuKuanUsdContextInputAmountReply(),amount) + "\n" + usdtAccountInfo,
                                    MessageModeEnum.HTML.getType()
                            );
                            userAndPath.remove(update.getMessage().getFrom().getId().toString());

                        }
                    }

                    //4.1.1 用户在进行usdt账户信息绑定
                    if(sysConfig.getQuKuanUsdBindCallData().equals(userPath)){
                        logger.info("用户在进行usdt账户信息绑定,用户ID：{}",update.getMessage().getFrom().getId());
                        // 创建返回和关闭按钮
                        List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                                sysConfig.getFhButtonText(),
                                sysConfig.getQuKuanPath(),
                                sysConfig.getButtonCloseText(),
                                sysConfig.getButtonCloseTextData()
                        );

                        tbUserExtInfoManagerService.saveOrUpdateQuUsdInfo(update.getMessage().getFrom().getId().toString(),update.getMessage().getText());

                        sendInLineKeyBoardButtonMessage(
                                update.getMessage().getChatId().toString(),
                                InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons),
                                sysConfig.getSetSuccess(),
                                MessageModeEnum.HTML.getType()
                        );
                        userAndPath.remove(update.getMessage().getFrom().getId().toString());

                    }



                    //4.2 如果在进行汇旺取款操作
                    if(sysConfig.getQuKuanHwPath().equals(userPath)){
                        logger.info("用户正在进行汇旺取款操作,用户ID：{}",update.getMessage().getFrom().getId());

                        //4.2.1 判断是否符合输入格式
                        Matcher checkAmountRegx = RegxUtils.matcherRegx(text, sysConfig.getCheckAmountRegx());
                        if(!checkAmountRegx.find()){//输入格式不正确

                            // 创建返回和关闭按钮
                            List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                                    sysConfig.getFhButtonText(),
                                    sysConfig.getQuKuanHwPath(),
                                    sysConfig.getButtonCloseText(),
                                    sysConfig.getButtonCloseTextData()
                            );

                            sendInLineKeyBoardButtonMessage(
                                    update.getMessage().getChatId().toString(),
                                    InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons),
                                    sysConfig.getQuKuanHwContextInputAmountFaild(),
                                    MessageModeEnum.HTML.getType()
                            );

                        }else {

                            String amount = checkAmountRegx.group(1);
                            String hwAccountInfo = tbUserExtInfoManagerService.queryHwAccountInfoByTgUserId(update.getMessage().getFrom().getId());

                            InlineKeyboardButton queren = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                                    sysConfig.getQuKuanHwQueRenButtonText(),
                                    String.format(sysConfig.getQuKuanHwQueRenButtonCallData(),amount)
                            );
                            InlineKeyboardButton fh = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getFhButtonText(), sysConfig.getQuKuanUsdPath());

                            sendInLineKeyBoardButtonMessage(
                                    update.getMessage().getChatId().toString(),
                                    InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(InlineKeyboardButtonUtil.createInlineKeyboardButtons(queren,fh)),
                                    String.format(sysConfig.getQuKuanHwContextInputAmountReply(),amount) + "\n" + hwAccountInfo,
                                    MessageModeEnum.HTML.getType()
                            );
                            userAndPath.remove(update.getMessage().getFrom().getId().toString());
                        }
                    }

                    //4.2.1 用户在进行汇旺账户信息绑定
                    if(sysConfig.getQuKuanHwBindCallData().equals(userPath)){
                        logger.info("用户在进行汇旺账户信息绑定,用户ID：{}",update.getMessage().getFrom().getId());
                        // 创建返回和关闭按钮
                        List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                                sysConfig.getFhButtonText(),
                                sysConfig.getQuKuanPath(),
                                sysConfig.getButtonCloseText(),
                                sysConfig.getButtonCloseTextData()
                        );

                        tbUserExtInfoManagerService.saveOrUpdateQuHwInfo(update.getMessage().getFrom().getId().toString(),update.getMessage().getText());

                        sendInLineKeyBoardButtonMessage(
                                update.getMessage().getChatId().toString(),
                                InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons),
                                sysConfig.getSetSuccess(),
                                MessageModeEnum.HTML.getType()
                        );
                        userAndPath.remove(update.getMessage().getFrom().getId().toString());
                    }
                }
            }

            CallbackQuery callbackQuery = update.getCallbackQuery();
            if(callbackQuery != null){
                String callBackData = callbackQuery.getData();
                userAndPath.put(update.getCallbackQuery().getFrom().getId().toString(), callBackData);

                //1.存按钮
                if(callBackData.equals(sysConfig.getCunKuanPath())){
                    logger.info("点击了存按钮....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getWelComPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    List<InlineKeyboardButton> callDataInlineKeyboardButtonList = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButtonList(sysConfig.getCunKuanBindIdText(), sysConfig.getCunKuanBindIdCallData());


                    //1.检查是否绑定了ID
                    Boolean isBinded = tbUserExtInfoManagerService.isBindedId(update.getCallbackQuery().getFrom().getId().toString());
                    if(!isBinded){

                        List<List<InlineKeyboardButton>> lists = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList(callDataInlineKeyboardButtonList, inlineKeyboardButtons);

                        sendInLineKeyBoardButtonEditMessage(
                                callbackQuery.getMessage().getChatId(),
                                InlineKeyboardButtonUtil.createInlineKeyboardMarkup(lists),
                                sysConfig.getCunKuanSetAcountInfo(),
                                callbackQuery.getMessage().getMessageId(),
                                MessageModeEnum.HTML.getType());

                        return;
                    }

                    InlineKeyboardButton cunKuanUsdt = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getCunKuanUsd(), sysConfig.getCunKuanUsdPath());
                    InlineKeyboardButton quKuanHw = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getCunKuanHw(), sysConfig.getCunKuanHwPath());
                    List<InlineKeyboardButton> cunButton = InlineKeyboardButtonUtil.createInlineKeyboardButtons(cunKuanUsdt, quKuanHw);
                    List<List<InlineKeyboardButton>> mergButtons = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList2(cunButton,callDataInlineKeyboardButtonList,inlineKeyboardButtons);
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup(mergButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getCunKuanSelectContext(),
                            callbackQuery.getMessage().getMessageId()
                    );

                }

                //1.1.1 绑定账户ID信息按钮
                if(callBackData.equals(sysConfig.getCunKuanBindIdCallData())){
                    logger.info("点击了绑定账户ID信息按钮....");

                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getWelComPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getCunKuanSetAcountInfoTips(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }




                //1.1 usd存按钮
                if(callBackData.equals(sysConfig.getCunKuanUsdPath())){
                    logger.info("点击了usd存按钮....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getCunKuanPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    List<InlineKeyboardButton> callDataInlineKeyboardButtonList = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButtonList(sysConfig.getCunKuanUsdYiZhuan(), sysConfig.getCunKuanUsdYiZhuanPath());
                    List<List<InlineKeyboardButton>> mergeInlineKeyboardButtonList2 = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList2(callDataInlineKeyboardButtonList, inlineKeyboardButtons);
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup(mergeInlineKeyboardButtonList2);

                    String text = sysConfig.getCunKuanUsdContext();

                    TbSysParamManager tbSysParamManager = tbSysParamManagerService.queryByKey(SysDbParamsConstance.cunKanUsdGg);
                    if(tbSysParamManager!=null){
                        text = text + tbSysParamManager.getValue();
                    }

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            text,
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //1.1.1 usd存按钮-已转
                if(callBackData.equals(sysConfig.getCunKuanUsdYiZhuanPath())){
                    logger.info("点击了usd存按钮-已转....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getCunKuanUsdPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getCunKuanUsdYiZhuanContext(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //1.1.2 客服修改USDT订单状态
                Matcher cunQuOrderStatusRegx = RegxUtils.matcherRegx(callBackData, sysConfig.getCunQuOrderStatusRegx());
                if(cunQuOrderStatusRegx.find()){
                    logger.info("点击了客服修改USDT订单状态....");
                    String uuid = cunQuOrderStatusRegx.group(1);
                    String orderStatus = cunQuOrderStatusRegx.group(2);
                    logger.info("uuid = {}, orderStatus = {}",uuid,orderStatus);


                    //1.更新数据库的订单状态
                    tbUserCunQuManagerService.updateByOrderStatusByUuid(uuid, Integer.valueOf(orderStatus));
                    //2.修改群内的订单状态

                    TbUserCunQuManagerOrderStatus tbUserCunQuManagerOrderStatus =
                            TbUserCunQuManagerOrderStatus.getTbUserCunQuManagerOrderStatusByCode(orderStatus);
                    // -- 创建群里的的底部按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons1 = sysConfig.getInlineKeyboardOrderStatus(uuid,tbUserCunQuManagerOrderStatus);
                    InlineKeyboardMarkup inlineKeyboardMarkup2 = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons1);

                    //修改群里的订单状态
                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            callbackQuery.getMessage().getMessageId(),
                            inlineKeyboardMarkup2
                    );

                    //3.修改客户的订单状态
                    TbUserCunQuManager byId = tbUserCunQuManagerService.getById(uuid);
                    InlineKeyboardButton callDataInlineKeyboardButton = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                            sysConfig.getOrderStatusButtonText() + tbUserCunQuManagerOrderStatus.getMsg(),
                            sysConfig.getOrderStatusButtonPath()
                    );
                    InlineKeyboardMarkup inlineKeyboardMarkup = createInlineKeyboardMarkup(callDataInlineKeyboardButton);

                    String context = StringUtils.hasText(callbackQuery.getMessage().getText()) ? callbackQuery.getMessage().getText() : callbackQuery.getMessage().getCaption() ;

                    sendInLineKeyBoardButtonEditMessage(
                            byId.getTgUserId(),
                            byId.getTgUserMsgId(),
                            inlineKeyboardMarkup,
                            context
                    );
                }


                //1.2 hw存按钮
                if(callBackData.equals(sysConfig.getCunKuanHwPath())){
                    logger.info("点击了hw存按钮....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getCunKuanPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    List<InlineKeyboardButton> callDataInlineKeyboardButtonList = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButtonList(sysConfig.getCunKuanHwYiZhuan(), sysConfig.getCunKuanHwYiZhuanPath());
                    List<List<InlineKeyboardButton>> mergeInlineKeyboardButtonList2 = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList2(callDataInlineKeyboardButtonList, inlineKeyboardButtons);
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup(mergeInlineKeyboardButtonList2);

                    String text = sysConfig.getCunKuanHwContext();

                    TbSysParamManager tbSysParamManager = tbSysParamManagerService.queryByKey(SysDbParamsConstance.cunKanHwGg);
                    if(tbSysParamManager!=null){
                        text = text + tbSysParamManager.getValue();
                    }

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            text,
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //1.2.1 hw存按钮-已转
                if(callBackData.equals(sysConfig.getCunKuanHwYiZhuanPath())){
                    logger.info("点击了hw存按钮-已转....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getCunKuanUsdPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getCunKuanUsdYiZhuanContext(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }


                //2.取按钮
                if(callBackData.equals(sysConfig.getQuKuanPath())){
                    logger.info("点击了取按钮....");

                    InlineKeyboardButton cunKuanUsdt =
                            InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getQuKuanUsd(), sysConfig.getQuKuanUsdPath());
                    InlineKeyboardButton quKuanHw =
                            InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getQuKuanHw(), sysConfig.getQuKuanHwPath());
                    List<InlineKeyboardButton> cunButton = InlineKeyboardButtonUtil.createInlineKeyboardButtons(cunKuanUsdt, quKuanHw);

                    InlineKeyboardButton usdtAccountInfo =
                            InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getQuKuanUsdBindText(), sysConfig.getQuKuanUsdBindCallData());
                    InlineKeyboardButton hwAccountInfo =
                            InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getQuKuanHwBindText(), sysConfig.getQuKuanHwBindCallData());
                    List<InlineKeyboardButton> cunButton2 = InlineKeyboardButtonUtil.createInlineKeyboardButtons(usdtAccountInfo, hwAccountInfo);

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getWelComPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );
                    List<List<InlineKeyboardButton>> mergButtons = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList2(cunButton,cunButton2,inlineKeyboardButtons);
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup(mergButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getQuKuanSelectContext(),
                            callbackQuery.getMessage().getMessageId()
                    );
                }

                //2.1 usd取款按钮
                if(callBackData.equals(sysConfig.getQuKuanUsdPath())){
                    logger.info("点击了usd取款按钮....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getQuKuanPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    //1.检查是否绑定了usd账户信息
                    Boolean haveUserUsdInfo = tbUserExtInfoManagerService.haveUserUsdInfo(update.getCallbackQuery().getFrom().getId());
                    if(!haveUserUsdInfo){

                        List<InlineKeyboardButton> callDataInlineKeyboardButtonList = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButtonList(sysConfig.getQuKuanUsdBindText(), sysConfig.getQuKuanUsdBindCallData());
                        List<List<InlineKeyboardButton>> lists = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList(callDataInlineKeyboardButtonList, inlineKeyboardButtons);

                        sendInLineKeyBoardButtonEditMessage(
                                callbackQuery.getMessage().getChatId(),
                                InlineKeyboardButtonUtil.createInlineKeyboardMarkup(lists),
                                sysConfig.getQuKuanUsdSetAcountInfo(),
                                callbackQuery.getMessage().getMessageId(),
                                MessageModeEnum.HTML.getType());

                        return;
                    }


                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getQuKuanUsdContext(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //2.1.1 usd绑定账户按钮
                if(callBackData.equals(sysConfig.getQuKuanUsdBindCallData())){
                    logger.info("点击了usd绑定账户按钮....");

                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getQuKuanPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getQuKuanUsdSetAcountInfoTips(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //2.1.2 usd取款确认按钮
                Matcher quKuanUsdQueRenButtonCallDataRegx = RegxUtils.matcherRegx(callBackData, sysConfig.getQuKuanUsdQueRenButtonCallDataRegx());
                if(quKuanUsdQueRenButtonCallDataRegx.find()){
                    logger.info("点击了usd取款确认按钮....");

                    double amount = Double.valueOf(quKuanUsdQueRenButtonCallDataRegx.group(1));
                    TbUserExtInfoManager userExtInfoManager = tbUserExtInfoManagerService.getById(update.getCallbackQuery().getFrom().getId());

                    String quKuanContext = String.format(sysConfig.getQuKuanUsdContextProccessReplyQun(), userExtInfoManager.getBindId(), amount, TbUserCunQuManagerAccountType.USDT.getMsg(), userExtInfoManager.getQuUsdtInfo());

                    //1.发送数据到客服群

                    // -- 创建群里的的底部按钮
                    TbSysParamManager tbSysParamManagerQun = tbSysParamManagerService.queryByKey(SysDbParamsConstance.kefuQunId);
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    List<InlineKeyboardButton> inlineKeyboardButtons1 = sysConfig.getInlineKeyboardOrderStatus(uuid,TbUserCunQuManagerOrderStatus.YI_TI_JIAO);
                    InlineKeyboardMarkup inlineKeyboardMarkupQun = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons1);
                    Message messageQun = sendInLineKeyBoardButtonMessage(
                            Long.valueOf(tbSysParamManagerQun.getValue()),
                            inlineKeyboardMarkupQun,quKuanContext

                    );

                    //2.回复客户当前订单状态
                    InlineKeyboardButton callDataInlineKeyboardButton = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                            sysConfig.getOrderStatusButtonText() + TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getMsg(),
                            sysConfig.getOrderStatusButtonPath()
                    );
                    InlineKeyboardMarkup inlineKeyboardMarkupUser = createInlineKeyboardMarkup(callDataInlineKeyboardButton);

                    sendInLineKeyBoardButtonEditMessage(
                            update.getCallbackQuery().getFrom().getId(),
                            inlineKeyboardMarkupUser,
                            quKuanContext,
                            update.getCallbackQuery().getMessage().getMessageId()
                    );


                    //3.保存数据到数据库

                    tbUserCunQuManagerService.saveOrderQu(
                            uuid,
                            update.getCallbackQuery().getFrom().getId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            tbSysParamManagerQun.getValue(),
                            messageQun.getMessageId(),
                            userExtInfoManager.getBindId(),
                            TbUserCunQuManagerCunQuType.QU.getCode(),

                            TbUserCunQuManagerAccountType.USDT.getCode(),
                            amount,
                            userExtInfoManager.getQuUsdtInfo(),
                            userExtInfoManager.getQuHwInfo(),

                            TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode()
                    );




                }


                //2.2 hw取款按钮
                if(callBackData.equals(sysConfig.getQuKuanHwPath())){
                    logger.info("点击了hw取款按钮....");

                    // 创建返回和关闭按钮
                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getQuKuanPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );

                    //1.检查是否绑定了hw账户信息
                    Boolean haveUserHwInfo = tbUserExtInfoManagerService.haveUserHwInfo(update.getCallbackQuery().getFrom().getId());
                    if(!haveUserHwInfo){

//                        sendMessage(update.getCallbackQuery().getFrom().getId(),sysConfig.getPleaseBindQuHwInfo(),MessageModeEnum.HTML.getType());

                        List<InlineKeyboardButton> callDataInlineKeyboardButtonList = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButtonList(sysConfig.getQuKuanHwBindText(), sysConfig.getQuKuanHwBindCallData());
                        List<List<InlineKeyboardButton>> lists = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList(callDataInlineKeyboardButtonList, inlineKeyboardButtons);

                        sendInLineKeyBoardButtonEditMessage(
                                callbackQuery.getMessage().getChatId(),
                                InlineKeyboardButtonUtil.createInlineKeyboardMarkup(lists),
                                sysConfig.getQuKuanHwSetAcountInfo(),
                                callbackQuery.getMessage().getMessageId(),
                                MessageModeEnum.HTML.getType());

                        return;
                    }


                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getQuKuanHwContext(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //2.2.1 汇旺绑定账户按钮
                if(callBackData.equals(sysConfig.getQuKuanHwBindCallData())){
                    logger.info("点击了汇旺绑定账户按钮....");

                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                            sysConfig.getFhButtonText(),
                            sysConfig.getQuKuanPath(),
                            sysConfig.getButtonCloseText(),
                            sysConfig.getButtonCloseTextData()
                    );
                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);

                    sendInLineKeyBoardButtonEditMessage(
                            callbackQuery.getMessage().getChatId(),
                            inlineKeyboardMarkup,
                            sysConfig.getQuKuanHwSetAcountInfoTips(),
                            callbackQuery.getMessage().getMessageId(),
                            MessageModeEnum.HTML.getType());
                }

                //2.2.1 汇旺取款确认按钮
                Matcher quKuanHwQueRenButtonCallDataRegx = RegxUtils.matcherRegx(callBackData, sysConfig.getQuKuanHwQueRenButtonCallDataRegx());
                if(quKuanHwQueRenButtonCallDataRegx.find()){

                    logger.info("点击了汇旺取款确认按钮....");

                    double amount = Double.valueOf(quKuanHwQueRenButtonCallDataRegx.group(1));
                    TbUserExtInfoManager userExtInfoManager = tbUserExtInfoManagerService.getById(update.getCallbackQuery().getFrom().getId());
                    String quKuanContext = String.format(sysConfig.getQuKuanHwContextProccessReplyQun(), userExtInfoManager.getBindId(), amount, TbUserCunQuManagerAccountType.HW.getMsg(), userExtInfoManager.getQuHwInfo());

                    //1.发送数据到客服群

                    // -- 创建群里的的底部按钮
                    TbSysParamManager tbSysParamManagerQun = tbSysParamManagerService.queryByKey(SysDbParamsConstance.kefuQunId);
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    List<InlineKeyboardButton> inlineKeyboardButtons1 = sysConfig.getInlineKeyboardOrderStatus(uuid,TbUserCunQuManagerOrderStatus.YI_TI_JIAO);
                    InlineKeyboardMarkup inlineKeyboardMarkupQun = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons1);
                    Message messageQun = sendInLineKeyBoardButtonMessage(
                            Long.valueOf(tbSysParamManagerQun.getValue()),
                            inlineKeyboardMarkupQun,quKuanContext
                    );

                    //2.回复客户当前订单状态
                    InlineKeyboardButton callDataInlineKeyboardButton = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                            sysConfig.getOrderStatusButtonText() + TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getMsg(),
                            sysConfig.getOrderStatusButtonPath()
                    );
                    InlineKeyboardMarkup inlineKeyboardMarkupUser = createInlineKeyboardMarkup(callDataInlineKeyboardButton);

                    sendInLineKeyBoardButtonEditMessage(
                            update.getCallbackQuery().getFrom().getId(),
                            inlineKeyboardMarkupUser,
                            quKuanContext,
                            update.getCallbackQuery().getMessage().getMessageId()
                    );


                    //3.保存数据到数据库

                    tbUserCunQuManagerService.saveOrderQu(
                            uuid,
                            update.getCallbackQuery().getFrom().getId(),
                            update.getCallbackQuery().getMessage().getMessageId(),
                            tbSysParamManagerQun.getValue(),
                            messageQun.getMessageId(),
                            userExtInfoManager.getBindId(),
                            TbUserCunQuManagerCunQuType.QU.getCode(),

                            TbUserCunQuManagerAccountType.HW.getCode(),
                            amount,
                            userExtInfoManager.getQuUsdtInfo(),
                            userExtInfoManager.getQuHwInfo(),

                            TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode()
                    );


//                    List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
//                            sysConfig.getFhButtonText(),
//                            sysConfig.getQuKuanPath(),
//                            sysConfig.getButtonCloseText(),
//                            sysConfig.getButtonCloseTextData()
//                    );
//                    InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup2(inlineKeyboardButtons);
//
//                    sendInLineKeyBoardButtonEditMessage(
//                            callbackQuery.getMessage().getChatId(),
//                            inlineKeyboardMarkup,
//                            sysConfig.getQuKuanHwContextProccessReply(),
//                            callbackQuery.getMessage().getMessageId(),
//                            MessageModeEnum.HTML.getType());
                }



                //3.关闭按钮
                if(callBackData.equals(sysConfig.getButtonCloseTextData())){
                    logger.info("点击了关闭按钮....");
                    deleteMessageByChatIdAndMsgId(
                            callbackQuery.getMessage().getChatId(),
                            callbackQuery.getMessage().getMessageId()
                    );
                }

                //4.主页按钮
                if(callBackData.equals(sysConfig.getWelComPath())){
                    logger.info("点击了主页按钮....");
                    homeButton(callbackQuery.getMessage().getChatId(),callbackQuery.getMessage().getMessageId());
                }


            }


        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }  catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 转发消息到监控群
     * @param update
     */
    private void forwordMsg(Update update) {
        try {
            if(update.hasMessage()){
                TbSysParamManager sysParamManager = tbSysParamManagerService.queryByKey(SysDbParamsConstance.chAppJianKongChatId);
                if(sysParamManager != null){
                    //1.有转发会话ID
                    ForwardMessage forwardMessage = new ForwardMessage();
                    forwardMessage.setChatId(sysParamManager.getValue());
                    forwardMessage.setFromChatId(update.getMessage().getChatId());
                    forwardMessage.setMessageId(update.getMessage().getMessageId());
                    execute(forwardMessage);
                }
            }

        } catch (TelegramApiException e) {
            logger.error("转发消息失败.....",e);
        }
    }


    /**
     * 主页按钮
     * @param chatId     会话ID
     * @throws TelegramApiException
     */
    private void homeButton(Long chatId,Integer msgId) throws TelegramApiException {

        InlineKeyboardButton cunKuan =
                InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getCunKuan(), sysConfig.getCunKuanPath());
        InlineKeyboardButton quKuan =
                InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(sysConfig.getQuKuan(), sysConfig.getQuKuanPath());

        List<InlineKeyboardButton> homeButton = new ArrayList<>();
        homeButton.add(cunKuan);
        homeButton.add(quKuan);

        // 创建返回和关闭按钮
        List<InlineKeyboardButton> inlineKeyboardButtons = createFootInlineButton(
                sysConfig.getFhButtonText(),
                sysConfig.getWelComPath(),
                sysConfig.getButtonCloseText(),
                sysConfig.getButtonCloseTextData()
        );
        List<List<InlineKeyboardButton>> mergButtons = InlineKeyboardButtonUtil.mergeInlineKeyboardButtonList(homeButton,inlineKeyboardButtons);
        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardButtonUtil.createInlineKeyboardMarkup(mergButtons);

        // 获取欢迎语
        TbSysParamManager tbSysParamManager = tbSysParamManagerService.queryByKey(SysDbParamsConstance.chAppHomeText);
        String value = "";
        if(tbSysParamManager != null){
            value = tbSysParamManager.getValue();
        }

        if(msgId == null){
            sendInLineKeyBoardButtonMessage(chatId,inlineKeyboardMarkup,StringUtils.hasText(value) ? value : sysConfig.getWelComContext());
        }else{
            sendInLineKeyBoardButtonEditMessage(
                    chatId,inlineKeyboardMarkup,
                    StringUtils.hasText(value) ? value : sysConfig.getWelComContext(),
                    msgId);
        }

    }


    /**
     * 底部公共按钮
     * @param fbButtonText         返回按钮名称
     * @param fbButtonData         返回按钮数据
     * @param closeButtonText      关闭按钮名称
     * @param closeButtonData      关闭按钮数据
     * @return
     */
    private static List<InlineKeyboardButton> createFootInlineButton(String fbButtonText,String fbButtonData,String closeButtonText,String closeButtonData) {
        InlineKeyboardButton fh = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(fbButtonText, fbButtonData);
        InlineKeyboardButton close = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(closeButtonText, closeButtonData);
        List<InlineKeyboardButton> inlineKeyboardButtons = InlineKeyboardButtonUtil.createInlineKeyboardButtons(fh, close);
        return inlineKeyboardButtons;
    }




}
