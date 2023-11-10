package com.tg.base.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.base.tb.entity.TbBotInstanceManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @description 针对表【tb_bot_instance_manager(机器人实例管理)】的数据库操作Mapper
* @createDate 2022-10-22 12:53:50
* @Entity com.game.qs.entity.TbBotInstanceManager
*/
@Mapper
public interface TbBotInstanceManagerMapper extends BaseMapper<TbBotInstanceManager> {

    /**
     * 根据主键ID更新机器人运行状态
     * @param tbBotInstanceId      机器人主键ID
     * @param runStatus            机器人运行实例
     * @return
     */
    int updateRunStatusByBotTypeId(
            @Param("tbBotInstanceId") int tbBotInstanceId,
            @Param("runStatus") int runStatus);

    /**
     * 根据条件分页查询机器人列表---管理员分页查询
     * @param botTypeId
     * @param botUserName
     * @param botToken
     * @param botNo
     * @param botQyStatus
     * @param runStatus
     * @param startCreateTime
     * @param endCreateTime
     * @return
     */
    List<TbBotInstanceManager> selectAllBotPageByConditionAdmin(
            @Param("botTypeId") Integer botTypeId
            , @Param("botUserName") String botUserName
            , @Param("botToken") String botToken
            , @Param("botNo") String botNo
            , @Param("botQyStatus") Integer botQyStatus
            , @Param("runStatus") Integer runStatus
            , @Param("startCreateTime") Date startCreateTime
            , @Param("endCreateTime") Date endCreateTime);

    /**
     * 根据启用状态查询机器人实例列表
     * @param qyStatus       启用状态
     * @return
     */
    List<TbBotInstanceManager> selectBotByQyStatus(@Param("qyStatus") int qyStatus);

    /**
     * 根据机器人ID和所属用户ID查询
     * @param botId      机器人ID
     * @param userTgId   用户ID
     * @return
     */
    TbBotInstanceManager selectOneByBotIdAndUserTgId(@Param("botId") int botId,@Param("userTgId") String userTgId);

    /**
     * 通过token查询机器人
     * @param botToken
     * @return
     */
    TbBotInstanceManager selectByToken(@Param("botToken") String botToken);

    /**
     * 通过botName查询机器人
     * @param botName
     * @return
     */
    TbBotInstanceManager selectByBotName(@Param("botName") String botName);

    /**
     * 通过机器人的用户名删除
     * @param botUserName
     * @return
     */
    int deleteBotByBotName(@Param("botName") String botUserName);

    /**
     * 根据归属人查询机器人列表
     * @param userId   归属人用户ID
     * @return
     */
    List<TbBotInstanceManager> selectByGsUserId(@Param("userId") String userId);

    /**
     * 更新启用和运行状态
     * @param tbBotInstanceId     机器人ID
     * @param qyStatus            启用状态
     * @param runStatus           停用状态
     * @return
     */
    int updateQyStatusAndRunStatusByBotTypeId(@Param("tbBotInstanceId") int tbBotInstanceId,
                                              @Param("qyStatus") int qyStatus,
                                              @Param("runStatus") int runStatus);
}




