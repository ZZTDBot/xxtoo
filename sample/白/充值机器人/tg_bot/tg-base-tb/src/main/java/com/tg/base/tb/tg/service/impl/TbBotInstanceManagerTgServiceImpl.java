package com.tg.base.tb.tg.service.impl;

import com.tg.base.tb.entity.TbBotInstanceManager;
import com.tg.base.tb.service.TbBotInstanceManagerService;
import com.tg.base.tb.tg.bot.BuBaseBot;
import com.tg.base.tb.tg.config.TbBotInstanceManagerTgConfig;
import com.tg.base.tb.tg.service.TbBotInstanceManagerTgService;
import com.tg.base.tb.utils.CollecttionUtils;
import com.tg.base.tb.utils.RegxUtils;
import com.tg.bot.base.bot.enum1.MessageFromTypeEnum;
import com.tg.bot.base.bot.utils.InlineKeyboardButtonUtil;
import com.tg.bot.base.bot.utils.InlineKeyboardMarkupUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TbBotInstanceManagerTgServiceImpl implements TbBotInstanceManagerTgService {

    @Autowired
    TbBotInstanceManagerService tbBotInstanceManagerService;

    @Autowired
    TbBotInstanceManagerTgConfig tbBotInstanceManagerTgConfig;

    //创建机器人的命令模板
    String createBotRegx = "^创建机器人\n" +
            "创建人用户名:(\\@.+)\n"+
            "归属人用户名:(\\@.+)\n"+
            "机器人用户名:(\\@.+)\n"+
            "机器人Token:(\\@.+)";

    /**
     * 捕获机器人用户名的正则表达式
     */
    String botUserNameRegx = "t.me/([0-9a-zA-Z_]+)";
    /**
     * 捕获机器人token的正则表达式
     */
    String botTokenNameRegx = "([0-9]{10}:[0-9a-zA-Z_-]{35})";

    @Override
    public void addBot(Update update, BuBaseBot buBaseBot) throws TelegramApiException {
        if(update.hasMessage()
                && MessageFromTypeEnum.PRIVATE.getType().equals(update.getMessage().getChat().getType())) {

            Pattern createBotRegxCompile = Pattern.compile(createBotRegx);
            Matcher createBotRegxMatcher = createBotRegxCompile.matcher(update.getMessage().getText());
            if (createBotRegxMatcher.find()) {

                String createUserName = createBotRegxMatcher.group(1);
                String gsUserName = createBotRegxMatcher.group(2);
                String botName = createBotRegxMatcher.group(3);
                String botToken = createBotRegxMatcher.group(4);

                tbBotInstanceManagerService.addBotNew(createUserName,gsUserName,botName,botToken);

                buBaseBot.sendMessage(
                        update.getMessage().getChat().getId(),
                        "添加机器人成功!!!",
                        "html");
            }
        }
    }

    @Override
    public void addBotByToken(Update update, BuBaseBot buBaseBot) throws TelegramApiException, ClassNotFoundException {
        if(update.hasMessage()
                && MessageFromTypeEnum.PRIVATE.getType().equals(update.getMessage().getChat().getType())) {

            Pattern botUserNameRegxCompile = Pattern.compile(botUserNameRegx);
            Matcher botUserNameRegxMatcher = botUserNameRegxCompile.matcher(update.getMessage().getText());
            boolean botUserNameFlag = botUserNameRegxMatcher.find();

            Pattern botTokenNameRegxCompile = Pattern.compile(botTokenNameRegx);
            Matcher botTokenNameRegxMatcher = botTokenNameRegxCompile.matcher(update.getMessage().getText());
            boolean botTokenFlag = botTokenNameRegxMatcher.find();

            if (botUserNameFlag && botTokenFlag) {
                String botUserName = botUserNameRegxMatcher.group(1);
                String botToken = botTokenNameRegxMatcher.group(1);
                logger.info("进入添加机器人的方法...   botUserName:{}    botToken:{}",botUserName,botToken);

                tbBotInstanceManagerService.addBotNewFromUserId(
                        update.getMessage().getFrom().getId().toString(),
                        update.getMessage().getFrom().getId().toString(),
                        botUserName,
                        botToken
                );

                buBaseBot.sendMessage(
                        update.getMessage().getChat().getId(),
                        "添加机器人成功!!!",
                        "html");

                startBotByBotName(update, buBaseBot, botUserName);
            }
        }
    }

    /**
     * 根据机器人名称启用机器人
     * @param update
     * @param buBaseBot
     * @param botUserName
     * @throws TelegramApiException
     * @throws ClassNotFoundException
     */
    private void startBotByBotName(Update update, BuBaseBot buBaseBot, String botUserName) throws TelegramApiException, ClassNotFoundException {
        Boolean aBoolean = tbBotInstanceManagerService.startBotByBotUserName(botUserName);

        if(aBoolean){
            buBaseBot.sendMessage(
                    update.getMessage().getChat().getId(),
                    "机器人启动成功!!!",
                    "html");
        }else {
            buBaseBot.sendMessage(
                    update.getMessage().getChat().getId(),
                    "机器人启动失败!!!",
                    "html");
        }
    }

    @Override
    public void proccessPrivate(BuBaseBot buBaseBot, Update update) throws TelegramApiException, ClassNotFoundException {

        if(update.hasMessage()
                && MessageFromTypeEnum.PRIVATE.getType().equals(update.getMessage().getChat().getType())) {

            String text = update.getMessage().getText();
            User from = update.getMessage().getFrom();
            Message updateMessage = update.getMessage();

//            SetMyCommands setMyCommands = new SetMyCommands();
//            setMyCommands.setCommands(tbBotInstanceManagerTgConfig.getBotCommands());
//            buBaseBot.execute(setMyCommands);
//            logger.debug("设置机器人管理模块的命令列表成功...");


            if(StringUtils.hasText(text)){
                logger.debug("进入机器人管理模块的私聊有文本内容模块...");

                if(tbBotInstanceManagerTgConfig.getMyBotList().getCommand().equals(text)){
                    logger.debug("机器人管理模块-查看机器人列表...");
                    //1.获取数据库按钮数据
                    List<TbBotInstanceManager> listBot = tbBotInstanceManagerService.queryMyBotList(from.getId().toString());
                    if(!CollectionUtils.isEmpty(listBot)){
                        //2.转换数据库数据为按钮
                        List<InlineKeyboardButton> collect = listBot.stream().map(tbBotInstanceManager -> {
                            return InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                                    tbBotInstanceManager.getBotUserName(),
                                    tbBotInstanceManagerTgConfig.getInlineButtonCallDataBotDetail() + tbBotInstanceManagerTgConfig.getPathFenGe() + tbBotInstanceManager.getTbBotInstanceId()
                            );
                        }).collect(Collectors.toList());
                        //3.布局按钮
                        List<List<InlineKeyboardButton>> listToListList = CollecttionUtils.listToListList(collect,3);

                        //4.发送按钮数据
                        buBaseBot.sendInLineKeyBoardButtonMessage(
                                updateMessage.getChatId(),
                                InlineKeyboardMarkupUtil.createInlineKeyboardMarkupByListList(listToListList),
                                "我的机器人列表");
                    }
                }

                //2. 停用机器人
                Matcher tyMatcherRegx = RegxUtils.matcherRegx(text, tbBotInstanceManagerTgConfig.getTingYongBotRegx());
                if(tyMatcherRegx.find()){
                    String botName = tyMatcherRegx.group(1);
                    logger.info("进入机器人管理模块--停用机器人--botName:{}",botName);
                    Boolean aBoolean = tbBotInstanceManagerService.stopBotByBotUserName(botName);
                    if(aBoolean){
                        buBaseBot.sendReplyMessage(updateMessage.getChatId(),updateMessage.getMessageId(),"机器人 @"+botName + " 停用成功.");
                    }
                }

                //3. 启用机器人
                Matcher qyMatcherRegx = RegxUtils.matcherRegx(text, tbBotInstanceManagerTgConfig.getQyBotRegx());
                if(qyMatcherRegx.find()){
                    String botName = qyMatcherRegx.group(1);
                    logger.info("进入机器人管理模块--启用机器人--botName:{}",botName);
                    startBotByBotName(update,buBaseBot,botName);
                }

            }
        }

        //2.内联按钮事件处理
        CallbackQuery callbackQuery = update.getCallbackQuery();
        if(callbackQuery != null){
            String data = callbackQuery.getData();
            logger.debug("进入机器人管理模块--内联按钮");
            //2.1 匹配机器人详情查询
            Pattern inlineButtonCallDataBotDetailRegxCompile = Pattern.compile(tbBotInstanceManagerTgConfig.getInlineButtonCallDataBotDetailRegx());
            Matcher inlineButtonCallDataBotDetailRegxMatcher = inlineButtonCallDataBotDetailRegxCompile.matcher(data);
            if(inlineButtonCallDataBotDetailRegxMatcher.find()){
                String botId = inlineButtonCallDataBotDetailRegxMatcher.group(1);
                logger.debug("进入机器人管理模块--内联按钮--机器人详情查询   机器人ID：{}",botId);
                TbBotInstanceManager byId = tbBotInstanceManagerService.getById(botId);
                if(byId != null){
                    List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
                    InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton("","");
                }
            }


        }


    }
}
