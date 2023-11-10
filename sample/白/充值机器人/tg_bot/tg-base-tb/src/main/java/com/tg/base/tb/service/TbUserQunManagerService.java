package com.tg.base.tb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.base.tb.entity.TbUserQunManager;

/**
* @author xxx
* @description 针对表【tb_user_qun_manager(用户_群_关系管理)】的数据库操作Service
* @createDate 2022-09-13 14:39:06
*/
public interface TbUserQunManagerService extends IService<TbUserQunManager> {
    /**
     * 保存用户群关系
     * @param tgQunId     群ID
     * @param tgUserId    用户ID
     * @return            数据库影响行数
     */
    public int savaOrUpdateUserQunManager(String tgQunId,String tgUserId);


    /**
     * 删除用户和群关系记录
     * @param tgQunId      群ID
     * @param tgUserId     用户ID
     * @return             数据库影响记录数
     */
    public int deleteUserQunManager(String tgQunId,String tgUserId);

}
