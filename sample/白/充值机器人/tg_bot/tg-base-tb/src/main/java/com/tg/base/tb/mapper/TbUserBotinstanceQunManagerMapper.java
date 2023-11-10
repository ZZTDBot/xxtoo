package com.tg.base.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.base.tb.entity.TbUserBotinstanceQunManager;
import com.tg.base.tb.service.dto.UserBotInsQunDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【tb_user_botinstance_qun_manager(用户_机器人实例_群_关系管理)】的数据库操作Mapper
* @createDate 2022-12-24 14:35:42
* @Entity com.game.qs.module.common.userbotinsqunmanager.entiry.TbUserBotinstanceQunManager
*/
@Mapper
public interface TbUserBotinstanceQunManagerMapper extends BaseMapper<TbUserBotinstanceQunManager> {


    /**
     * 根据 群ID 机器人ID查询记录
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    TbUserBotinstanceQunManager selectOneByQunBotId(
            @Param("qunId") Long qunId,
            @Param("tbBotInstanceId") Integer tbBotInstanceId
    );

    /**
     * 根据 用户ID 群ID 机器人ID查询记录
     * @param tgUserId
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    TbUserBotinstanceQunManager selectOneByUserQunBotId(
            @Param("tgUserId") Long tgUserId,
            @Param("qunId") Long qunId,
            @Param("tbBotInstanceId") Integer tbBotInstanceId
    );

    /**
     * 根据 群ID 机器人ID 删除记录
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    int deleteByQunBotInsId(
            @Param("qunId") Long qunId,
            @Param("tbBotInstanceId") Integer tbBotInstanceId
    );

    /**
     * 根据飞机ID和机器人ID查询群列表
     * @param tgUserId        飞机ID
     * @param tbBotInstanceId 机器人ID
     * @return
     */
    List<UserBotInsQunDto> selectListByTgUserIdAndBotId(
            @Param("tgUserId") Long tgUserId,
            @Param("tbBotInstanceId") Integer tbBotInstanceId
    );


    /**
     * 根据用户ID 群ID 机器人ID  删除关系
     * @param tgUserId
     * @param qunId
     * @param tbBotInstanceId
     * @return
     */
    int deleteByUserQunBotId(@Param("tgUserId") String tgUserId,@Param("qunId") String qunId, @Param("tbBotInstanceId") Integer tbBotInstanceId);

}




