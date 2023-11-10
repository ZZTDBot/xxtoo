package com.tg.base.tb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tg.base.tb.entity.TbSysParamManager;
import com.tg.base.tb.service.TbSysParamManagerService;
import com.tg.base.tb.mapper.TbSysParamManagerMapper;
import com.tg.bot.base.bot.utils.ButtonCommonUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
* @description 针对表【tb_sys_param_manager(用户表)】的数据库操作Service实现
* @createDate 2023-09-26 14:39:42
*/
@Service
public class TbSysParamManagerServiceImpl extends ServiceImpl<TbSysParamManagerMapper, TbSysParamManager>
    implements TbSysParamManagerService{

    Gson gson = new Gson();

    private List<LinkedHashMap<String, String>> linkedHashMaps = new ArrayList<>();

    @Override
    public Boolean saveOrUpdateByKey(String key, String value) {
        TbSysParamManager tbSysParamManager = this.queryByKey(key);
        if(tbSysParamManager != null){
            tbSysParamManager.setValue(value);
            baseMapper.updateById(tbSysParamManager);
        }else {
            TbSysParamManager tbSysParamManagerNew = new TbSysParamManager();
            tbSysParamManagerNew.setKey(key);
            tbSysParamManagerNew.setValue(value);
            baseMapper.insert(tbSysParamManagerNew);
        }
        return Boolean.TRUE;
    }

    @Override
    public TbSysParamManager queryByKey(String key) {
        return baseMapper.selectByKey(key);
    }

    @Override
    public int delByKey(String key) {
        return baseMapper.delByKey(key);
    }

    @Override
    public List<List<String>> queryDownButtonNameList(String sysParamsDownButton) {
        List<LinkedHashMap<String, String>> linkedHashMapsQuery = queryDownButtonListMaps(sysParamsDownButton);
        if (CollectionUtils.isEmpty(linkedHashMapsQuery)) return null;
        List<List<String>> listListButtonNames = ButtonCommonUtil.getListListButtonNames(linkedHashMapsQuery);
        return listListButtonNames;
    }

    @Override
    public String queryDownButtonReplyContext(String text,String sysParamsDownButtonKey) {

        List<LinkedHashMap<String, String>> linkedHashMapsQuery = queryDownButtonListMaps(sysParamsDownButtonKey);
        if (CollectionUtils.isEmpty(linkedHashMapsQuery)) return null;

        LinkedHashMap<String, String> linkedHashMap = linkedHashMapsQuery.stream().reduce((x, y) -> {
            x.putAll(y);
            return x;
        }).get();
        return linkedHashMap.get(text);
    }

    /**
     * 查询数据库里面机器人的底部按钮字符串
     * @param sysParamsDownButton        底部按钮在系统参数里面的key
     * @return
     */
    private List<LinkedHashMap<String, String>> queryDownButtonListMaps(String sysParamsDownButton) {
        TbSysParamManager tbSysParamManager = queryByKey(sysParamsDownButton);
        String value = tbSysParamManager.getValue();
        //1.参数不存在
        if(!StringUtils.hasText(value)){
            return null;
        }
        List<LinkedHashMap<String, String>> fromJson = gson.fromJson(value, new TypeToken<ArrayList<LinkedHashMap<String, String>>>(){}.getType());
        return fromJson;
    }


}




