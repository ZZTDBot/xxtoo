package com.tg.base.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.base.tb.entity.TbBotAdminManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @description 针对表【tb_bot_admin_manager(机器人的管理员表)】的数据库操作Mapper
* @createDate 2023-06-02 17:04:25
* @Entity com.tg.base.tb.tb_bot_admin_manager.entity.TbBotAdminManager
*/
@Mapper
public interface TbBotAdminManagerMapper extends BaseMapper<TbBotAdminManager> {

    /**
     * 通过机器人实例号和用户ID查询
     * @param botId              机器人实例号
     * @param opratorUserTgId    飞机用户ID
     * @return
     */
    TbBotAdminManager selectByBotIdAndOpratorUserTgId(@Param("botId") int botId,@Param("opratorUserTgId")  String opratorUserTgId);

    /**
     * 通过机器人ID和用户Id删除数据
     * @param botId
     * @param userTgId
     * @return
     */
    int deleteByBotIdAndUserTgId(@Param("botId") int botId,@Param("userTgId") String userTgId);
}




