package com.tg.bot.base.bot.enum1;

import java.util.HashMap;

/**
 * 飞机的群角色枚举类
 */
public enum TgQunRoleEnum {

    TG_QUNROLE_MEMBER("member","成员"),
    TG_QUNROLE_ADMINISTRATOR("administrator","管理员"),
    TG_QUNROLE_CREATOR("creator","群主");


    private static HashMap<String,TgQunRoleEnum> tgQunRoleHashMap = new HashMap<>();

    static {
        tgQunRoleHashMap.put(TG_QUNROLE_MEMBER.status,TG_QUNROLE_MEMBER);
        tgQunRoleHashMap.put(TG_QUNROLE_ADMINISTRATOR.status,TG_QUNROLE_ADMINISTRATOR);
        tgQunRoleHashMap.put(TG_QUNROLE_CREATOR.status,TG_QUNROLE_CREATOR);
    }


    //群角色编码
    private String status;

    //群角色注释
    private String statusRemark;

    TgQunRoleEnum(String status, String statusRemark) {
        this.status = status;
        this.statusRemark = statusRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemark() {
        return statusRemark;
    }

    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark;
    }

    /**
     * 根据角色编码  获取角色注释
     * @param status
     * @return
     */
    public static String getStatusRemarkByStatus(String status){
        TgQunRoleEnum tgQunRoleEnum = tgQunRoleHashMap.get(status);
        if(tgQunRoleEnum != null){
            return tgQunRoleEnum.getStatusRemark();
        }

        return null;
    }

}
