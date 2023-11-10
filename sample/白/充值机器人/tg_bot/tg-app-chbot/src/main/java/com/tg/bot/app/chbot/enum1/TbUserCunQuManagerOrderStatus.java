package com.tg.bot.app.chbot.enum1;

import java.util.HashMap;

public enum TbUserCunQuManagerOrderStatus {
    YI_TI_JIAO(1,"已提交"),
    ZHENG_ZAI_CHU_LI(2,"正在处理"),
    YI_WAN_CHENG(3,"已完成"),
    ;

    static HashMap<String,TbUserCunQuManagerOrderStatus> hashMap = new HashMap<String,TbUserCunQuManagerOrderStatus>();

    static {
        hashMap.put(TbUserCunQuManagerOrderStatus.YI_TI_JIAO.getCode() + "",TbUserCunQuManagerOrderStatus.YI_TI_JIAO);
        hashMap.put(TbUserCunQuManagerOrderStatus.ZHENG_ZAI_CHU_LI.getCode() + "",TbUserCunQuManagerOrderStatus.ZHENG_ZAI_CHU_LI);
        hashMap.put(TbUserCunQuManagerOrderStatus.YI_WAN_CHENG.getCode() + "",TbUserCunQuManagerOrderStatus.YI_WAN_CHENG);
    }

    /**
     * 通过订单状态编码获取订单状态枚举类
     * @param orderStatusCode
     * @return
     */
    public static TbUserCunQuManagerOrderStatus getTbUserCunQuManagerOrderStatusByCode(String orderStatusCode){
        return hashMap.get(orderStatusCode);
    }


    private int code;

    private String msg;

    TbUserCunQuManagerOrderStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "TbUserCunQuManagerCunQuType{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
