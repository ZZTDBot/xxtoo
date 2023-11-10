package com.tg.bot.app.chbot.config;

import com.tg.bot.app.chbot.enum1.TbUserCunQuManagerOrderStatus;
import com.tg.bot.base.bot.utils.InlineKeyboardButtonUtil;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class SysConfig {


    //------------机器人内联按钮配置区域-----------
    private String welComContext = "欢迎来到xxx";
    private String welComPath = "/home";

    private String cunKuanBindIdText = "绑定账户ID";
    private String cunKuanBindIdCallData = "/cunkuan/bind/id";
    private String cunKuanSetAcountInfo = "你还没有绑定账户ID信息，请点击下面\"绑定账户ID\"按钮进行绑定";
    private String cunKuanSetAcountInfoTips = "现在填写的信息都会保存到账户ID信息中\n例如：想要在账户ID信息中存入\"我的账户ID\"\n*直接给机器人发送:我的账户ID";

    private String cunKuan = "存款";
    private String cunKuanPath = "/cunkuan";
    private String cunKuanSelectContext = "请选择存款方式";

    private String cunKuanUsd = "usdt";
    private String cunKuanUsdPath = "/cunkuan/usdt";
    private String cunKuanUsdContext = "请存入到下面的USDT账户\n";
    private String cunKuanUsdYiZhuan = "已转,上传截图";
    private String cunKuanUsdYiZhuanPath = "/cunkuan/usdt/yz";
    private String cunKuanUsdYiZhuanContext = "请上传转账截图!!!";

    private String cunKuanHw = "汇旺";
    private String cunKuanHwPath = "/cunkuan/hw";
    private String cunKuanHwContext = "请存入到下面的汇旺账户\n";
    private String cunKuanHwYiZhuan = "已转,上传截图";
    private String cunKuanHwYiZhuanPath = "/cunkuan/hw/yz";
    private String cunKuanHwYiZhuanContext = "请上传转账截图!!!";
    private String cunKuanHwYiZhuanContextReplyQun = "存款用户ID：%s\n";



    private String quKuan = "取款";
    private String quKuanPath = "/qukuan";
    private String quKuanSelectContext = "请选择取款方式";

    private String quKuanUsd = "usdt";
    private String quKuanUsdPath = "/qukuan/usdt";
    private String quKuanUsdBindText = "绑定USDT账户信息";
    private String quKuanUsdBindCallData = "/qukuan/usdt/bind";
    private String quKuanUsdSetAcountInfo = "你还没有绑定USDT账户信息，请点击下面\"绑定USDT账户信息\"按钮进行绑定";
    private String quKuanUsdSetAcountInfoTips = "现在填写的信息都会保存到usdt账户信息中\n例如：想要在usdt账户信息中存入\"我的USDT账户xxx\"\n*直接给机器人发送:我的USDT账户xxx";
    private String quKuanUsdContext = "请输入取款余额\n";
    private String quKuanUsdContextInputAmountFaild = "输入格式错误,请重新输入！！！ 例如:10";
    private String quKuanUsdContextInputAmountReply = "确认提取%sUSDT吗?";
    private String quKuanUsdQueRenButtonText = "确认";
    private String quKuanUsdQueRenButtonCallData = "/qukuan/usdt/1/%s";
    private String quKuanUsdQueRenButtonCallDataRegx = "^/qukuan/usdt/1/([0-9]+(\\.([0-9]+))?)$";
    private String quKuanUsdContextProccessReply = "正在处理,请等待...";
    private String quKuanUsdContextProccessReplyQun = "取款用户ID：%s\n" +
                                                      "取款金额：%s\n" +
                                                      "取款账户类型：%s\n" +
                                                      "取款账户信息：%s\n"
                                                  ;

    private String quKuanHw = "汇旺";
    private String quKuanHwPath = "/qukuan/hw";
    private String quKuanHwBindText = "绑定汇旺账户信息";
    private String quKuanHwBindCallData = "/qukuan/hw/bind";
    private String quKuanHwSetAcountInfo = "你还没有绑定汇旺账户信息，请点击下面\"绑定汇旺账户信息\"按钮进行绑定";
    private String quKuanHwSetAcountInfoTips = "现在填写的信息都会保存到汇旺账户信息中\n例如：想要在汇旺账户信息中存入\"我的汇旺账户xxx\"\n*直接给机器人发送:我的汇旺账户xxx";
    private String quKuanHwContext = "请输入取款余额\n";
    private String quKuanHwContextInputAmountFaild = "输入格式错误,请重新输入！！！ 例如:10";
    private String quKuanHwContextInputAmountReply = "确认提取%sUSDT吗?";
    private String quKuanHwQueRenButtonText = "确认";
    private String quKuanHwQueRenButtonCallData = "/qukuan/hw/1/%s";
    private String quKuanHwQueRenButtonCallDataRegx = "^/qukuan/hw/1/([0-9]+(\\.([0-9]+))?)$";
    private String quKuanHwContextProccessReply = "正在处理,请等待...";
    private String quKuanHwContextProccessReplyQun = "取款用户ID：%s\n" +
            "取款金额：%s\n" +
            "取款账户类型：%s\n" +
            "取款账户信息：%s\n"
            ;






    //------------公共配置区域-----------
    private String setSuccess = "设置成功!!!";
    private String tiJiaoSuccess = "提交成功，请稍等！！！";

    private String success = "✅";

    private String buttonCloseText = "关闭";
    private String buttonCloseTextData = "/close";

    private String fhButtonText = "返回";
    private String fHbuttonData = "/home";

    private String orderStatusButtonText = "订单状态:";
    private String orderStatusButtonPath = "/order/status/user";

    //订单状态按钮回调数据 /order/status/用户存取记录ID/订单状态code
    private String orderStatusCallData = "/order/status/%s/%s";

    //------------正则配置区域-----------
    //1.捕获-usd存的内容
    private String cunUsdtTextRegx = "^设置存usdt内容(\n|\r\n)" +
            "((.|\r\n|\n|\r)+)";
    //2.捕获-hw存的内容
    private String cunHwTextRegx = "^设置存汇旺内容(\n|\r\n)" +
            "((.|\r\n|\n|\r)+)";
    //3.捕获-客服群ID
    private String keFuQunIdTextRegx = "^设置客服群ID([\\-0-9]+)";

    //4.捕获-主页欢迎语的内容
    private String homeTextRegx = "^设置主页内容(\n|\r\n)" +
            "((.|\r\n|\n|\r)+)";

    //5.捕获-监控会话ID
    private String jianKongChatIdRegx = "^设置监控会话ID([\\-0-9]+)";

    //6.金额检测正则
    private String checkAmountRegx = "^(([0-9]+)(\\.([0-9])+)?)$";


    //------------路径 正则匹配-----------
    //1.客服修改订单状态按钮
    private String cunQuOrderStatusRegx = "^\\/order\\/status\\/([a-z0-9]{32})\\/([0-9])";



    //---------命令列表-------------
    private SetMyCommands setMyCommands = new SetMyCommands();

    {
        ArrayList<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(new BotCommand("/start","主菜单"));
        setMyCommands.setCommands(botCommands);
    }


    /**
     * 获取客服群 订单状态按钮
     * @param cunQuUuid
     * @return
     */
    public List<InlineKeyboardButton> getInlineKeyboardOrderStatus(String cunQuUuid,TbUserCunQuManagerOrderStatus tbUserCunQuManagerOrderStatus) {

        InlineKeyboardButton yitijiao = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getMsg(),
                String.format(this.getOrderStatusCallData(), cunQuUuid, TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode())
        );

        InlineKeyboardButton zhengzaichuli = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                TbUserCunQuManagerOrderStatus.ZHENG_ZAI_CHU_LI.getMsg(),
                String.format(this.getOrderStatusCallData(), cunQuUuid, TbUserCunQuManagerOrderStatus.ZHENG_ZAI_CHU_LI.getCode())
        );

        InlineKeyboardButton yiwancheng = InlineKeyboardButtonUtil.createCallDataInlineKeyboardButton(
                TbUserCunQuManagerOrderStatus.YI_WAN_CHENG.getMsg(),
                String.format(this.getOrderStatusCallData(), cunQuUuid, TbUserCunQuManagerOrderStatus.YI_WAN_CHENG.getCode())
        );

        switch (tbUserCunQuManagerOrderStatus) {
            case YI_TI_JIAO:
                yitijiao.setText(this.success + yitijiao.getText());
                break;
            case ZHENG_ZAI_CHU_LI:
                zhengzaichuli.setText(this.success + zhengzaichuli.getText());
                break;
            case YI_WAN_CHENG:
                yiwancheng.setText(this.success + yiwancheng.getText());
                break;
            default:
                break;
        }

        return InlineKeyboardButtonUtil.createInlineKeyboardButtons(yitijiao, zhengzaichuli, yiwancheng);
    }



}
