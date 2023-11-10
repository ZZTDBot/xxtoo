package com.tg.bot.app.chbot.service;

import com.tg.bot.app.chbot.entity.TbUserCunQuManager;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @description 针对表【tb_user_cun_qu_manager(用户存取记录表管理)】的数据库操作Service
*/
public interface TbUserCunQuManagerService extends IService<TbUserCunQuManager> {

    /**
     * 保存存款订单信息
     * @param tgUserId           存入人
     * @param userMessageId      回复存入人的信息
     * @param kefuQunId          客服群ID
     * @param kefuQunMessageId   回复客服群ID
     * @param bindId             用户绑定的ID
     * @param cunQuType          存取类型
     * @param accountType        存入账户类型
     * @param usdtAcountInfo     usdt收款账户
     * @param hwAcountInfo       hw收款账户
     * @param fileId             截图文件id
     * @param orderStatus        订单状态
     */
    void saveOrder(
            String tgUserCunQuUuid,
            Long tgUserId,
            Integer userMessageId,
            String kefuQunId,
            Integer kefuQunMessageId,
            String bindId,
            int cunQuType,
            int accountType,
            String usdtAcountInfo,
            String hwAcountInfo,
            String fileId,
            int orderStatus
    );

    /**
     * 保存存款信息
     * @param tgUserCunQuUuid    存入的UUID主键
     * @param tgUserId           存入人
     * @param userMessageId      回复存入人的信息
     * @param kefuQunId          客服群ID
     * @param kefuQunMessageId   回复客服群ID
     * @param bindId             用户绑定的ID
     *
     * @param quAccountType          取款账户类型
     * @param quAmount               取出金额
     * @param quCruUsdtAccountInfo   取款时的usdt账户信息
     * @param quCruHwAccountInfo     取款时的Hw账户信息
     * @param orderStatus        新但状态
     * @return
     */
    int saveOrderQu(
            String tgUserCunQuUuid,
            Long tgUserId,
            Integer userMessageId,
            String kefuQunId,
            Integer kefuQunMessageId,
            String bindId,
            int cunQuType,

            int quAccountType,
            double quAmount,
            String quCruUsdtAccountInfo,
            String quCruHwAccountInfo,

            int orderStatus
    );

    /**
     * 更新订单状态
     * @param uuid           订单的uuid
     * @param orderStatus    订单状态
     * @return
     */
    int updateByOrderStatusByUuid(String uuid,int orderStatus);
}
