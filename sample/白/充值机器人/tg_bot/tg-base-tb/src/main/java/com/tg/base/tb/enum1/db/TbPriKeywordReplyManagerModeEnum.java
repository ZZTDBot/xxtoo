package com.tg.base.tb.enum1.db;

import java.util.HashMap;

/**
 * 私聊关键词回复模式
 */
public enum TbPriKeywordReplyManagerModeEnum {
    REPLYMODE_PTFH(1,"普通返回"),
    REPLYMODE_HFMS(2,"回复模式"),
    REPLYMODE_WZMS(0,"未知模式");

    private static HashMap<Integer,TbPriKeywordReplyManagerModeEnum> hashMap = new HashMap<Integer,TbPriKeywordReplyManagerModeEnum>();

    static {
        hashMap.put(REPLYMODE_PTFH.getId(),REPLYMODE_PTFH);
        hashMap.put(REPLYMODE_HFMS.getId(),REPLYMODE_HFMS);
    }

    /**
     * 获取指定回复模式编号的枚举类
     * @param replyModeId   回复模式ID
     * @return
     */
    public static TbPriKeywordReplyManagerModeEnum getEnumById(int replyModeId){
        TbPriKeywordReplyManagerModeEnum tbKeywordReplyManagerReplyModeEnum = hashMap.get(replyModeId);
        if(tbKeywordReplyManagerReplyModeEnum != null){
            return tbKeywordReplyManagerReplyModeEnum;
        }else {
            return REPLYMODE_WZMS;
        }
    }

    /**
     * 获取相反回复模式的枚举类
     * @param replyModeId   回复模式ID
     * @return
     */
    public static TbPriKeywordReplyManagerModeEnum getFanEnumById(int replyModeId){
        if(replyModeId == REPLYMODE_PTFH.getId()){
            return REPLYMODE_PTFH;
        }

        if(replyModeId == REPLYMODE_HFMS.getId()){
            return REPLYMODE_HFMS;
        }

        return REPLYMODE_WZMS;
    }

    private int id;
    private String msg;

    TbPriKeywordReplyManagerModeEnum(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
