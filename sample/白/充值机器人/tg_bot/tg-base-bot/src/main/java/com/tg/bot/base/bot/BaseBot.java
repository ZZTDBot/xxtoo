package com.tg.bot.base.bot;

import com.game.qs.bot.tgresponse.TiRenResponse;
import com.google.gson.Gson;
import com.tg.bot.base.bot.component.MyInlineKeyboardButton;
import com.tg.bot.base.bot.enum1.MessageFromTypeEnum;
import com.tg.bot.base.bot.enum1.TgPhotoSizePaiXuMode;
import com.tg.bot.base.bot.service.dto.BatchSendMsgDto;
import de.taimos.httputils.HTTPResponse;
import de.taimos.httputils.WS;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMemberCount;
import org.telegram.telegrambots.meta.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

@Slf4j
public abstract class BaseBot extends TelegramLongPollingBot {

    protected Gson gson = new Gson();


    /**
     * 获取群成员信息
     * @param chatId  群ID
     * @param userId  用户ID
     */
    public ChatMember getChatMember(Long chatId, Long userId) throws TelegramApiException {
        return execute(new GetChatMember(chatId + "", userId));
    }
    /**
     * 获取群成员个数
     * @param chatId
     * @return
     */
    public Integer getChatMemberCount(String chatId) throws TelegramApiException {
        GetChatMemberCount getChatMemberCount = new GetChatMemberCount(chatId);
        Integer execute = execute(getChatMemberCount);
        return execute;
    }

    /**
     * 获取图片的下载地址
     * @param photoSize
     * @return
     */
    public String getPhotoUrl(PhotoSize photoSize) throws TelegramApiException {
        GetFile getFile = new GetFile(photoSize.getFileId());
        File execute = execute(getFile);
        return execute.getFileUrl(getBotToken());
    }

    /**
     * 获取排序图片的下载地址
     * @param photo
     * @return
     */
    public String getPhotoUrlBySort(List<PhotoSize> photo , TgPhotoSizePaiXuMode tgPhotoSizePaiXuMode) throws TelegramApiException {
        PhotoSize photoSize = getPhotoSize(photo, tgPhotoSizePaiXuMode);
        return getPhotoUrl(photoSize);
    }

    /**
     * 获取排序后的第一个   PhotoSize
     * @param photos
     * @param tgPhotoSizePaiXuMode
     * @return
     */
    public static PhotoSize getPhotoSize(List<PhotoSize> photos, TgPhotoSizePaiXuMode tgPhotoSizePaiXuMode) {
        Collections.sort(photos, new Comparator<PhotoSize>() {
            @Override
            public int compare(PhotoSize o1, PhotoSize o2) {
                if(tgPhotoSizePaiXuMode.getMode().equals(TgPhotoSizePaiXuMode.TG_PHOTO_SIZE_PAIXU_MODE_DESC.getMode())){
                    return o1.getFileSize() > o2.getFileSize() ? -1 : 1;
                }else{
                    return o1.getFileSize() > o2.getFileSize() ? 1 : -1;
                }
            }
        });
        PhotoSize photoSize = photos.get(0);
        return photoSize;
    }

    /**
     * 下载图片到本地
     * @param photoFileUrl     图片所在服务器的地址
     * @param localFilePath    本地图片地址
     * @return
     */
    public Boolean downLoadPhotoToLocal(String photoFileUrl,String localFilePath) throws IOException {

        java.io.File downFilePhoto = new java.io.File(localFilePath);
        FileUtils.forceMkdir(downFilePhoto.getParentFile());
        IOUtils.copy(new URL(photoFileUrl),downFilePhoto);

        return Boolean.TRUE;
    }

    /**
     * 获取内联试用按钮
     * @param text         内联按钮名称
     * @param url          内联按钮链接
     * @return
     */
    public InlineKeyboardMarkup createInlineKeyboardMarkupUrl(String text, String url){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        // 第一行内容
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton11 = new InlineKeyboardButton();

        inlineKeyboardButton11.setText(text);
        inlineKeyboardButton11.setUrl(url);

        markupInline.setKeyboard(rowsInline);
        rowsInline.add(rowInline1);
        rowInline1.add(inlineKeyboardButton11);
        return markupInline;
    }

    /**
     * 创建一行行内联按钮
     * @return
     */
    public InlineKeyboardMarkup createInlineKeyboardMarkup(List<InlineKeyboardButton> inlineKeyboardButtonList) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(inlineKeyboardButtonList);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    /**
     * 通过二维集合 创建多行内联按钮
     * 这个方法已经过期  @link InlineKeyboardMarkupUtil.createInlineKeyboardMarkupByListList
     * @return
     */
    @Deprecated
    public InlineKeyboardMarkup createInlineKeyboardMarkupByListList(List<List<InlineKeyboardButton>> listListInlineKeyBoardButton) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(listListInlineKeyBoardButton);
        return markupInline;
    }

    /**
     * 创建多行内联按钮
     * @return
     */
    public InlineKeyboardMarkup createInlineKeyboardMarkup(InlineKeyboardButton inlineKeyboardButtonSS[][]) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        for (InlineKeyboardButton inlineKeyboardButtonS[] : inlineKeyboardButtonSS ) {
            List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
            for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtonS ) {

                InlineKeyboardButton inlineKeyboardButton11 = new InlineKeyboardButton();
                inlineKeyboardButton11.setText(inlineKeyboardButton.getText());
                inlineKeyboardButton11.setCallbackData(inlineKeyboardButton.getCallbackData());
                inlineKeyboardButton11.setUrl(inlineKeyboardButton.getUrl());

                rowInline1.add(inlineKeyboardButton11);
            }
            rowsInline.add(rowInline1);
        }
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    /**
     * 创建一维内联按钮集合
     * @param inlineKeyboardButtonS  一维数组
     * @return
     */
    public List<InlineKeyboardButton> createInlineKeyboardMarkup(MyInlineKeyboardButton inlineKeyboardButtonS[]) {
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        for (MyInlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtonS) {

            InlineKeyboardButton inlineKeyboardButton11 = new InlineKeyboardButton();
            inlineKeyboardButton11.setText(inlineKeyboardButton.getText());
            inlineKeyboardButton11.setCallbackData(inlineKeyboardButton.getCallbackData());
            inlineKeyboardButton11.setUrl(inlineKeyboardButton.getUrl());

            rowInline1.add(inlineKeyboardButton11);
        }
        return rowInline1;
    }

    /**
     * 创建一维内联按钮集合
     * @param inlineKeyboardButton  一维数组
     * @return
     */
    public InlineKeyboardMarkup createInlineKeyboardMarkup(InlineKeyboardButton... inlineKeyboardButton) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        ArrayList<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<InlineKeyboardButton>();
        keyboard.add(inlineKeyboardButtons);
        if(inlineKeyboardButton != null && inlineKeyboardButton.length > 0){
            Arrays.stream(inlineKeyboardButton).forEach(x -> {
                inlineKeyboardButtons.add(x);
            });
        }
        return inlineKeyboardMarkup;
    }


    /**
     * 合并两个内联按钮
     * @param firstInlineKeyboardMarkup   第一个内联按钮
     * @param myInlineToTgInline          第二个内联按钮
     * @return
     */
    public InlineKeyboardMarkup mergeInlineKeyboardMarkup(InlineKeyboardMarkup firstInlineKeyboardMarkup, List<List<InlineKeyboardButton>> myInlineToTgInline) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> keyboard = firstInlineKeyboardMarkup.getKeyboard();
        for (List<InlineKeyboardButton> list : myInlineToTgInline){
            keyboard.add(list);
        }
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    /**
     * 合并两个内联按钮
     * @param myInlineToTgInline          第1个内联按钮
     * @param firstInlineKeyboardMarkup   第2个内联按钮
     * @return
     */
//    public InlineKeyboardMarkup mergeInlineKeyboardMarkup2(List<List<InlineKeyboardButton>> myInlineToTgInline,InlineKeyboardMarkup firstInlineKeyboardMarkup) {
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//
//        List<List<InlineKeyboardButton>> keyboard = firstInlineKeyboardMarkup.getKeyboard();
//        for (List<InlineKeyboardButton> list : keyboard){
//            myInlineToTgInline.add(list);
//        }
//
//        inlineKeyboardMarkup.setKeyboard(myInlineToTgInline);
//
//        return inlineKeyboardMarkup;
//    }

    /**
     * 获取一行Button对象
     * @param buttonNames
     * @return
     */
    public KeyboardRow createKeyboardButtons(String... buttonNames) {
        KeyboardRow keyboardButtons1 = new KeyboardRow();
        int length = buttonNames.length;
        if(length != 0){
            for (String button : buttonNames) {
                keyboardButtons1.add(button);
            }
        }
        return keyboardButtons1;
    }

    /**
     * 获取一行Button对象
     * @param buttonNames
     * @return
     */
    public KeyboardRow createKeyboardButtons(List<String> buttonNames) {
        KeyboardRow keyboardButtons1 = new KeyboardRow();
        if(buttonNames != null && buttonNames.size() > 0){
            for (String button : buttonNames) {
                keyboardButtons1.add(button);
            }
        }
        return keyboardButtons1;
    }

    /**
     * 同步二维数组获取按键布局对象
     * @param isZHY             是否自动大小
     * @param keyboardRowS      键盘布局
     * @return
     */
    public ReplyKeyboardMarkup createReplyKeyboardMarkup(Boolean isZHY, String keyboardRowS[][]) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(isZHY);
        List<KeyboardRow> listKeyBoard = new ArrayList<KeyboardRow>();
        for (String keyBoardRow[] : keyboardRowS ) {
            KeyboardRow keyboardButtons = createKeyboardButtons(keyBoardRow);
            listKeyBoard.add(keyboardButtons);
        }
        replyKeyboardMarkup.setKeyboard(listKeyBoard);
        return replyKeyboardMarkup;
    }

    /**
     * 同步二维数组获取按键布局对象
     * @param isZHY             是否自动大小
     * @param keyboardRows      键盘布局
     * @return
     */
    public ReplyKeyboardMarkup createReplyKeyboardMarkup(Boolean isZHY, List<List<String>> keyboardRows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(isZHY);

        List<KeyboardRow> listKeyBoard = new ArrayList<KeyboardRow>();
        replyKeyboardMarkup.setKeyboard(listKeyBoard);

        for (List<String> keyBoardRow : keyboardRows ) {
            KeyboardRow keyboardButtons = createKeyboardButtons(keyBoardRow);
            listKeyBoard.add(keyboardButtons);
        }
        return replyKeyboardMarkup;
    }

    /**
     * 发送图片消息
     * @param chatId                会话ID
     * @param photoPath             图片地址
     * @param caption               图片说明
     * @return
     */
    public Message sendPhotoMessage(String chatId,String photoPath, String caption) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new java.io.File(photoPath)));
        sendPhoto.setCaption(caption);
        return execute(sendPhoto);
    }

    /**
     * 发送图片消息
     * @param chatId                会话ID
     * @param photoPath             图片地址
     * @param caption               图片说明
     * @return
     */
    public Message sendPhotoMessage(Long chatId,String photoPath, String caption) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile(new java.io.File(photoPath)));
        sendPhoto.setCaption(caption);
        return execute(sendPhoto);
    }

    /**
     * 转发图片
     * @param chatId                   会话ID
     * @param inlineKeyboardMarkup     按钮布局
     * @param caption                  图片备注
     * @throws TelegramApiException
     * @throws IOException
     */
    public Message sendEditPhotoInLineKeyBoardMessage(Long chatId, Integer msgId,InlineKeyboardMarkup inlineKeyboardMarkup, String caption) throws TelegramApiException, IOException {
//        EditMessageText editMessageText = new EditMessageText();
//        new EditMessageReplyMarkup(chatId,msgId,)
//        editMessageText.setChatId(chatId);
//        editMessageText.setText(text);
//        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
//
//        editMessageText.setMessageId(msgId);


        return null;
    }

    /**
     * 转发图片
     * @param photoSize                 需要转发的图片
     * @param chatId                   会话ID
     * @throws TelegramApiException
     * @throws IOException
     */
    public Message sendPhotoForwordMessage(PhotoSize photoSize,String chatId) throws TelegramApiException, IOException {
        String photoUrlBySort = getPhotoUrl(photoSize);
        // 创建 URL 对象
        URL url = new URL(photoUrlBySort);
        // 打开 URL 连接
        InputStream inputStream = url.openStream();
        InputFile inputFile = new InputFile(inputStream, UUID.randomUUID().toString());
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);

        return execute(sendPhoto);
    }

    /**
     * 转发图片
     * @param photoSize                需要转发的图片
     * @param chatId                   会话ID
     * @param inlineKeyboardMarkup      按钮布局
     * @throws TelegramApiException
     * @throws IOException
     */
    public Message sendPhotoSizeInLineKeyBoardButtonMessage(PhotoSize photoSize,String chatId,InlineKeyboardMarkup inlineKeyboardMarkup) throws TelegramApiException, IOException {
        String photoUrlBySort = getPhotoUrl(photoSize);
        // 创建 URL 对象
        URL url = new URL(photoUrlBySort);
        // 打开 URL 连接
        InputStream inputStream = url.openStream();
        InputFile inputFile = new InputFile(inputStream, UUID.randomUUID().toString());
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);
        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        return execute(sendPhoto);
    }

    /**
     * 转发图片
     * @param photoSize                需要转发的图片
     * @param chatId                   会话ID
     * @param inlineKeyboardMarkupup      按钮布局
     * @param caption                  图片注释
     * @throws TelegramApiException
     * @throws IOException
     */
    public Message sendPhotoSizeInLineKeyBoardButtonMessage(PhotoSize photoSize,String chatId,InlineKeyboardMarkup inlineKeyboardMarkupup,String caption) throws TelegramApiException, IOException {
        String photoUrlBySort = getPhotoUrl(photoSize);
        // 创建 URL 对象
        URL url = new URL(photoUrlBySort);
        // 打开 URL 连接
        InputStream inputStream = url.openStream();
        InputFile inputFile = new InputFile(inputStream, UUID.randomUUID().toString());
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);
        sendPhoto.setReplyMarkup(inlineKeyboardMarkupup);
        sendPhoto.setCaption(caption);
        return execute(sendPhoto);
    }

    /**
     * 转发图片
     * @param photos                   需要转发的图片
     * @param chatId                   会话ID
     * @param tgPhotoSizePaiXuMode     正序或倒序
     * @throws TelegramApiException
     * @throws IOException
     */
    public Message sendPhotoForwordMessage(List<PhotoSize> photos,String chatId,TgPhotoSizePaiXuMode tgPhotoSizePaiXuMode) throws TelegramApiException, IOException {
        String photoUrlBySort = getPhotoUrlBySort(photos, tgPhotoSizePaiXuMode);
        // 创建 URL 对象
        URL url = new URL(photoUrlBySort);
        // 打开 URL 连接
        InputStream inputStream = url.openStream();
        InputFile inputFile = new InputFile(inputStream, UUID.randomUUID().toString());
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);

        return execute(sendPhoto);
    }

    /**
     * 转发图片
     * @param photos                   需要转发的图片
     * @param tgPhotoSizePaiXuMode     正序或倒序
     * @param chatId                   会话ID
     * @param caption                  图片备注
     * @throws TelegramApiException
     * @throws IOException
     */
    public Message sendPhotoForwordMessage(List<PhotoSize> photos,TgPhotoSizePaiXuMode tgPhotoSizePaiXuMode,String chatId,String caption) throws TelegramApiException, IOException {
        String photoUrlBySort = getPhotoUrlBySort(photos, TgPhotoSizePaiXuMode.TG_PHOTO_SIZE_PAIXU_MODE_DESC);
        // 创建 URL 对象
        URL url = new URL(photoUrlBySort);
        // 打开 URL 连接
        InputStream inputStream = url.openStream();
        InputFile inputFile = new InputFile(inputStream, UUID.randomUUID().toString());
        SendPhoto sendPhoto = new SendPhoto(chatId, inputFile);

        sendPhoto.setCaption(caption);

        return execute(sendPhoto);
    }


    /**
     * 发送消息
     * @param chatId
     * @param messageContext
     * @return
     */
    public Message sendMessage(Long chatId, String messageContext) throws TelegramApiException {
        return sendMessage(chatId + "",messageContext);
    }

    /**
     * 发送消息
     * @param chatId
     * @param messageContext
     * @return
     */
    public Message sendMessage(String chatId,String messageContext) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageContext);
        return execute(message);
    }

    /**
     * 发送回复消息
     * @param chatId
     * @param messageContext
     * @param replyToMessageId
     * @return
     */
    public Message sendMessage(String chatId,String messageContext,Integer replyToMessageId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageContext);
        message.setReplyToMessageId(replyToMessageId);
        return execute(message);
    }

    /**
     * 发送回复消息
     * @param chatId
     * @param messageContext
     * @param replyToMessageId
     * @return
     */
    public Message sendMessage(String chatId,String messageContext,Integer replyToMessageId,String msgMode) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageContext);
        message.setReplyToMessageId(replyToMessageId);
        message.setParseMode(msgMode);
        return execute(message);
    }

    /**
     * 发送回复消息
     * @param chatId
     * @param messageContext
     * @param replyToMessageId
     * @return
     */
    public Message sendMessage(Long chatId,String messageContext,Integer replyToMessageId) throws TelegramApiException {
        return sendMessage(chatId.toString(),messageContext,replyToMessageId);
    }

    /**
     * 发送回复消息
     * @param chatId
     * @param messageContext
     * @param replyToMessageId
     * @return
     */
    public Message sendMessage(Long chatId,String messageContext,Integer replyToMessageId, String msgMode) throws TelegramApiException {
        return sendMessage(chatId.toString(),messageContext,replyToMessageId,msgMode);
    }

    /**
     * 发送指定模式的消息
     *
     * @param chatId  会话ID
     * @param context 内容
     * @param msgMode 消息模式
     * @return
     * @throws TelegramApiException
     */
    public Message sendMessage(Long chatId, String context, String msgMode) throws TelegramApiException {
        return sendMessage(chatId + "",context,msgMode);
    }

    /**
     * 发送指定模式的消息
     *
     * @param chatId  会话ID
     * @param context 内容
     * @param msgMode 消息模式
     * @return
     * @throws TelegramApiException
     */
    public Message sendMessage(String chatId, String context, String msgMode) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(context);
        if(msgMode != null){
            message.setParseMode(msgMode);
        }
        return execute(message);
    }





    /**
     * 发送带按钮的编辑消息
     * @param chatId          会话ID
     * @param messageId       消息ID
     * @param context         发送的消息内容
     * @param inlineKeyboardMarkup       发送的按钮布局
     * @return
     * @throws TelegramApiException
     */
    public Serializable sendEditMessageText(Long chatId,int messageId,String context,InlineKeyboardMarkup inlineKeyboardMarkup) throws TelegramApiException {
        return sendEditMessageText(chatId,messageId,context,inlineKeyboardMarkup,null);
    }

    /**
     * 发送带按钮的编辑消息
     * @param chatId          会话ID
     * @param messageId       消息ID
     * @param context         发送的消息内容
     * @param inlineKeyboardMarkup       发送的按钮布局
     * @param msgMode         消息类型
     * @return
     * @throws TelegramApiException
     */
    public Serializable sendEditMessageText(Long chatId,int messageId,String context,InlineKeyboardMarkup inlineKeyboardMarkup,String msgMode) throws TelegramApiException {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText(context);

        if(StringUtils.hasText(msgMode)){
            editMessageText.setParseMode(msgMode);
        }

        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
        Serializable serializable = execute(editMessageText);
        return serializable;
    }

    /**
     * 发送带按钮的编辑消息
     * @param chatId          会话ID
     * @param messageId       消息ID
     * @param context         发送的消息内容
     * @param msgMode         消息类型
     * @return
     * @throws TelegramApiException
     */
    public Serializable sendEditMessageText(Long chatId,int messageId,String context,String msgMode) throws TelegramApiException {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText(context);
        if(StringUtils.hasText(msgMode)){
            editMessageText.setParseMode(msgMode);
        }
        Serializable serializable = execute(editMessageText);
        return serializable;
    }

    /**
     * 批量发送消息
     * @param chatIds
     * @param msg
     * @return
     */
    public BatchSendMsgDto sendBatchMsg(Set<Long> chatIds, String msg) {
        BatchSendMsgDto batchSendMsgDto = new BatchSendMsgDto();
        if (StringUtils.hasText(msg) && !CollectionUtils.isEmpty(chatIds)){
            chatIds.stream().forEach(chatId -> {

                BatchSendMsgDto.SendMsgDto sendMsgDto = new BatchSendMsgDto.SendMsgDto();
                batchSendMsgDto.getSendMsgDtoList().add(sendMsgDto);

                try {
                    sendMsgDto.setMessage(sendMessage(chatId, msg));
                    sendMsgDto.setIsSendSuccess(Boolean.TRUE);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return batchSendMsgDto;
    }

    /**
     * 批量发送消息
     * @param chatId    会话ID
     * @param msgs      一批消息
     * @return
     */
    public Boolean sendBatchMsg(Long chatId, List<String> msgs) throws TelegramApiException {

        if(msgs == null || msgs.size() == 0){
            return Boolean.TRUE;
        }

        for(String msg : msgs){
            sendMessage(chatId,msg);
        }

        return Boolean.TRUE;
    }

    /**
     * 删除指定的消息
     * @param chatId 群id
     * @param messageId 消息ID
     * @return
     */
    public Boolean deleteMessageByChatIdAndMsgId(String chatId, String messageId) throws TelegramApiException {
        logger.info("准备删除" + chatId + "群里面的" + messageId + "消息");
        DeleteMessage deleteMessage = new DeleteMessage(chatId, Integer.parseInt(messageId));
        return execute(deleteMessage);
    }

    /**
     * 删除指定的消息
     * @param chatId 群id
     * @param messageId 消息ID
     * @return
     */
    public Boolean deleteMessageByChatIdAndMsgId(Long chatId, Integer messageId) throws TelegramApiException {
        return deleteMessageByChatIdAndMsgId(chatId + "",messageId + "");
    }

    /**
     * 踢人方法
     * @param chatId
     * @param userId
     * @return
     */
    public Boolean tiRenByChatIdAndUserId(String chatId, String userId) {
        String kickChatMemberUrl = getBaseUrl() + "/kickChatMember?chat_id=" + chatId + "&user_id=" + userId;
        HTTPResponse httpResponse = WS.url(kickChatMemberUrl).get();
        String responseAsString = httpResponse.getResponseAsString();

        logger.info("踢人响应结果:responseAsString" + responseAsString);

        TiRenResponse tiRenResponse = new Gson().fromJson(responseAsString, TiRenResponse.class);

        if (tiRenResponse != null && tiRenResponse.getOk()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 创建一行一个的内联按钮
     * @param text               内联按钮的显示名称
     * @param callDatat          内联按钮传送数据
     */
    public List<InlineKeyboardButton> createCallbackInlineKeyboardButton(String text, String callDatat) {
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton11 = new InlineKeyboardButton();
        inlineKeyboardButton11.setText(text);
        inlineKeyboardButton11.setCallbackData(callDatat);
        rowInline1.add(inlineKeyboardButton11);

        return rowInline1;
    }

    /**
     * 发送一个内联按钮消息到聊天会话
     * @param chatId
     * @param inlineKeyboardMarkup
     * @param text
     */
    public Message sendInLineKeyBoardButtonMessage(Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) throws TelegramApiException {
        SendMessage message1 = new SendMessage();
        message1.setChatId(chatId);
        message1.setText(text);
        message1.setReplyMarkup(inlineKeyboardMarkup);
        return execute(message1);
    }
    /**
     * 发送一个内联按钮消息到聊天会话
     * @param chatId           会话ID
     * @param messageId        消息ID
     * @param inlineKeyboardMarkup
     * @param text
     */
    public Message sendInLineKeyBoardButtonReplyMessage(Long chatId,int messageId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) throws TelegramApiException {
        SendMessage message1 = new SendMessage();
        message1.setChatId(chatId);
        message1.setText(text);

        message1.setReplyToMessageId(messageId);

        message1.setReplyMarkup(inlineKeyboardMarkup);
        return execute(message1);
    }

    /**
     * 发送一个内联按钮消息到聊天会话
     * @param chatId           会话ID
     * @param messageId        消息ID
     * @param inlineKeyboardMarkup
     * @param text
     * @param msgMode    消息格式
     */
    public Message sendInLineKeyBoardButtonReplyMessage(Long chatId,int messageId, InlineKeyboardMarkup inlineKeyboardMarkup, String text, String msgMode) throws TelegramApiException {
        SendMessage message1 = new SendMessage();
        message1.setChatId(chatId);
        message1.setText(text);
        message1.setReplyToMessageId(messageId);
        message1.setParseMode(msgMode);
        message1.setReplyMarkup(inlineKeyboardMarkup);
        return execute(message1);
    }

    /**
     * 发送一个内联按钮消息到聊天会话
     * @param chatId
     * @param inlineKeyboardMarkup
     * @param text
     * @param msgMode    消息格式
     */
    public Message sendInLineKeyBoardButtonMessage(String chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text, String msgMode) throws TelegramApiException {
        SendMessage message1 = new SendMessage();
        message1.setChatId(chatId);
        message1.setText(text);
        message1.setParseMode(msgMode);
        message1.setReplyMarkup(inlineKeyboardMarkup);
        return execute(message1);
    }

    /**
     * 批量发送一个内联按钮消息到聊天会话
     * @param chatIds
     * @param inlineKeyboardMarkup
     * @param text
     */
    public BatchSendMsgDto sendBatchInLineKeyBoardButtonMessage(Set<Long> chatIds, InlineKeyboardMarkup inlineKeyboardMarkup, String text) throws TelegramApiException {

        BatchSendMsgDto batchSendMsgDto = new BatchSendMsgDto();

        if (StringUtils.hasText(text) && !CollectionUtils.isEmpty(chatIds)){
            chatIds.stream().forEach(chatId -> {

                BatchSendMsgDto.SendMsgDto sendMsgDto = new BatchSendMsgDto.SendMsgDto();
                batchSendMsgDto.getSendMsgDtoList().add(sendMsgDto);

                try {
                    sendMsgDto.setMessage(sendInLineKeyBoardButtonMessage(chatId, inlineKeyboardMarkup , text ));
                    sendMsgDto.setIsSendSuccess(Boolean.TRUE);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return batchSendMsgDto;
    }

    /**
     * 批量发送一个内联按钮消息到聊天会话
     * @param chatIds
     * @param inlineKeyboardMarkupss
     * @param text
     */
    public BatchSendMsgDto sendBatchInLineKeyBoardButtonMessage(Set<Long> chatIds, List<List<InlineKeyboardButton>> inlineKeyboardMarkupss, String text) {

        BatchSendMsgDto batchSendMsgDto = new BatchSendMsgDto();

        if (StringUtils.hasText(text) && !CollectionUtils.isEmpty(chatIds)){
            chatIds.stream().forEach(chatId -> {

                BatchSendMsgDto.SendMsgDto sendMsgDto = new BatchSendMsgDto.SendMsgDto();
                batchSendMsgDto.getSendMsgDtoList().add(sendMsgDto);

                try {

                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    inlineKeyboardMarkup.setKeyboard(inlineKeyboardMarkupss);

                    sendMsgDto.setMessage(sendInLineKeyBoardButtonMessage(chatId, inlineKeyboardMarkup , text ));
                    sendMsgDto.setIsSendSuccess(Boolean.TRUE);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }

            });
        }
        return batchSendMsgDto;
    }



    /**
     * 发送一个内联按钮的编辑消息到聊天会话
     * @param chatId
     * @param inlineKeyboardMarkup
     * @param text
     * @param msgId                   消息ID
     */
//    public Serializable sendInLineKeyBoardButtonEditMessage(Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text,Integer msgId) throws TelegramApiException {
//        EditMessageText editMessageText = new EditMessageText();
//        editMessageText.setChatId(chatId);
//        editMessageText.setText(text);
//        editMessageText.setReplyMarkup(inlineKeyboardMarkup);
//
//        editMessageText.setMessageId(msgId);
//
//        Serializable execute = execute(editMessageText);
//
//        return execute;
//    }

    /**
     * 发送一个内联按钮的编辑消息到聊天会话
     * @param chatId
     * @param inlineKeyboardMarkup
     * @param text
     * @param msgId                   消息ID
     */
    public Serializable sendInLineKeyBoardButtonEditMessage(String chatId, Integer msgId, InlineKeyboardMarkup inlineKeyboardMarkup, String text) throws TelegramApiException {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setText(text);
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);

        editMessageText.setMessageId(msgId);
        return execute(editMessageText);
    }



    /**
     * 发送一个内联按钮的编辑消息到聊天会话
     * @param chatId
     * @param inlineKeyboardMarkup
     * @param text
     * @param msgId                   消息ID
     * @param paseMode                消息格式
     */
    public void sendInLineKeyBoardButtonEditMessage(Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text,Integer msgId,String paseMode) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setText(text);
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);

        editMessageText.setMessageId(msgId);
        editMessageText.setParseMode(paseMode);

        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送一个内联按钮的编辑消息到聊天会话
     * @param chatId
     * @param inlineKeyboardMarkup
     * @param text
     * @param msgId                   消息ID
     */
    public Serializable sendInLineKeyBoardButtonEditMessage(Long chatId, InlineKeyboardMarkup inlineKeyboardMarkup, String text,Integer msgId) throws TelegramApiException {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setText(text);
        editMessageText.setReplyMarkup(inlineKeyboardMarkup);

        editMessageText.setMessageId(msgId);

        return execute(editMessageText);
    }

    /**
     * 发送一个内联按钮的编辑消息到聊天会话
     * @param chatId
     * @param msgId                   消息ID
     * @param inlineKeyboardMarkup
     */
    public void sendInLineKeyBoardButtonEditMessage(Long chatId , Integer msgId , InlineKeyboardMarkup inlineKeyboardMarkup) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();

        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(msgId);
        editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

        try {
            execute(editMessageReplyMarkup);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 发送回复消息
     * @param chatId            会话ID
     * @param messageId         消息ID
     * @param context           消息内容
     * @return
     * @throws TelegramApiException
     */
    public Message sendReplyMessage(Long chatId,int messageId,String context) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setReplyToMessageId(messageId);
        message.setText(context);
        Message execute = execute(message);
        return execute;
    }

    /**
     * 发送键盘布局回复消息
     * @param replyKeyboardMarkup         键盘布局对象
     * @param chatId                      会话ID
     * @param textContext                 文本内容
     */
    public void sendReplyKeyboardMarkupMessage(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String textContext) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textContext);
        message.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 发送键盘布局回复消息
     *
     * @param replyKeyboardMarkup 键盘布局对象
     * @param chatId              会话ID
     * @param textContext         文本内容
     * @return
     */
    public Message sendReplyKeyboardMarkupMessage(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String textContext, int messageId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textContext);
        message.setReplyToMessageId(messageId);
        message.setReplyMarkup(replyKeyboardMarkup);
        try {
           return execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送键盘布局回复消息
     * @param replyKeyboardMarkup         键盘布局对象
     * @param chatId                      会话ID
     * @param textContext                 文本内容
     * @param paseMode                    文本内容格式
     */
    public void sendReplyKeyboardMarkupMessage(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String textContext,String paseMode) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textContext);
        message.setReplyMarkup(replyKeyboardMarkup);
        message.setParseMode(paseMode);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送本地图片消息到指定会话ID
     * @param chatId               会话ID
     * @param photoLocalPath       本地图片地址
     * @param context              内容
     * @return
     * @throws TelegramApiException
     */
    public Message sendLocalPhotoToTgChat( String chatId , String photoLocalPath, String context) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(
                new InputFile(
                        new java.io.File(photoLocalPath)
                )
        );
        if(!StringUtils.isEmpty(context)){
            sendPhoto.setCaption(context);
        }
        return execute(sendPhoto);
    }

    /**
     * 发送本地图片消息到指定会话ID
     * @param chatId               会话ID
     * @param photoLocalPath       本地图片地址
     * @return
     * @throws TelegramApiException
     */
    public Message sendLocalPhotoToTgChat( String chatId , String photoLocalPath) throws TelegramApiException {
        return sendLocalPhotoToTgChat(chatId,photoLocalPath,null);
    }

    /**
     * 发送内联按钮弹框提示
     *
     * @param msg              消息内容
     * @param callbackQueryId  回调按钮的查询ID
     * @return
     * @throws TelegramApiException
     */
    public Boolean sendAnswerCallbackQuery(String msg , String callbackQueryId) throws TelegramApiException {

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setText(msg);
        answerCallbackQuery.setShowAlert(Boolean.TRUE);
        answerCallbackQuery.setCallbackQueryId(callbackQueryId);

        return execute(answerCallbackQuery);

    }

    /**
     * 退出群
     *
     * @param chatId   群ID
     * @return
     * @throws TelegramApiException
     */
    public Boolean existQun(String chatId) throws TelegramApiException {
        LeaveChat leaveChat = new LeaveChat(chatId);
        Boolean execute = execute(leaveChat);
        return execute;

    }



    /**
     * 判断是否是私聊信息
     * @param update
     * @return
     */
    public Boolean isPrivateMsg(Update update) {
        if (update.hasMessage() && MessageFromTypeEnum.PRIVATE.getType().equals(update.getMessage().getChat().getType())){
            //判断是不是私聊信息
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }


}
