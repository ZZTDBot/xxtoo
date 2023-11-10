package com.tg.base.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.base.tb.entity.TbUserQunManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author xxx
* @description 针对表【tb_user_qun_manager(用户_群_关系管理)】的数据库操作Mapper
* @createDate 2022-09-13 14:39:06
* @Entity com.game.qs.entity.TbUserQunManager
*/
@Mapper
public interface TbUserQunManagerMapper extends BaseMapper<TbUserQunManager> {

    /**
     * 根据群ID和用户ID查询
     * @param tgQunId
     * @param tgUserId
     * @return
     */
    TbUserQunManager queryOneByUserTgIdAndQunId(
            @Param("tgQunId") String tgQunId,
            @Param("tgUserId") String tgUserId
    );

    /**
     * 根据群ID和用户ID查询
     * @param tgQunId
     * @param tgUserId
     * @return
     */
    int deleteByUserTgIdAndQunId(
            @Param("tgQunId") String tgQunId,
            @Param("tgUserId") String tgUserId
    );
}




