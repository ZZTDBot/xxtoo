package com.tg.base.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.base.tb.entity.TbUserManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description 针对表【tb_user_manager(用户管理)】的数据库操作Mapper
 * @createDate 2023-06-02 14:02:32
 * @Entity com.tg.bot.bu.db.entity.TbUserManager
 */
@Mapper
public interface TbUserManagerMapper extends BaseMapper<TbUserManager> {

    TbUserManager selectByTgUserName(@Param("tgUserName") String tgUserName);
}




