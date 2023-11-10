package com.tg.base.tb.mapper;

import com.tg.base.tb.entity.TbSysParamManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @description 针对表【tb_sys_param_manager(用户表)】的数据库操作Mapper
* @createDate 2023-09-26 14:39:42
* @Entity com.tg.base.tb.entity.TbSysParamManager
*/
@Mapper
public interface TbSysParamManagerMapper extends BaseMapper<TbSysParamManager> {

    /**
     * 查询数据 通过key
     * @param key
     * @return
     */
    TbSysParamManager selectByKey(@Param("key") String key);

    /**
     * 删除数据 通过key
     * @param key
     * @return
     */
    int delByKey(@Param("key") String key);
}




