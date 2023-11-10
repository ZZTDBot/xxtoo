package com.tg.bot.base.bot.enum1;

import java.util.HashMap;

public enum MessageFromTypeEnum {
    ///< Type of the chat, one of “private”, “group” or “channel” or "supergroup"
    GROUP(1,"group"),
    SUPERGROUP(2,"supergroup"),
    PRIVATE(3,"private"),
    CHANNEL(4,"channel"),
    ;

    public static HashMap<String,Integer> hashMap = new HashMap<String,Integer>();

    static {
        hashMap.put(MessageFromTypeEnum.GROUP.type,MessageFromTypeEnum.GROUP.id);
        hashMap.put(MessageFromTypeEnum.SUPERGROUP.type,MessageFromTypeEnum.SUPERGROUP.id);
        hashMap.put(MessageFromTypeEnum.PRIVATE.type,MessageFromTypeEnum.PRIVATE.id);
        hashMap.put(MessageFromTypeEnum.CHANNEL.type,MessageFromTypeEnum.CHANNEL.id);
    }

    private int id;
    private String type;

    MessageFromTypeEnum(int id,String type) {
        this.id = id;
        this.type = type;
    }

    public static HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public static void setHashMap(HashMap<String, Integer> hashMap) {
        MessageFromTypeEnum.hashMap = hashMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
