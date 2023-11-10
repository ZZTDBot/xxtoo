package com.tg.base.tb.service;

import com.tg.base.tb.entity.TbSysParamManager;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @description 针对表【tb_sys_param_manager(用户表)】的数据库操作Service
* @createDate 2023-09-26 14:39:42
*/
public interface TbSysParamManagerService extends IService<TbSysParamManager> {

    /**
     * 保存或查询键值对
     * @param key    键
     * @param value  值
     * @return
     */
    public Boolean saveOrUpdateByKey(String key , String value);

    /**
     * 查询数据 通过key
     * @param key
     * @return
     */
    public TbSysParamManager queryByKey(String key);

    /**
     * 删除数据 通过key
     * @param key
     * @return
     */
    public int delByKey(String key);

    /**
     * 获取底部按钮的二维布局
     * @param sysParamsDownButtonKey   底部按钮的常量key
     * @return
     */
    List<List<String>> queryDownButtonNameList(String sysParamsDownButtonKey);

    /**
     * 检测底部按钮关键词回复
     * @param text                      按钮名称
     * @param sysParamsDownButtonKey    底部按钮的常量key
     * @return
     */
    String queryDownButtonReplyContext(String text,String sysParamsDownButtonKey);
}
