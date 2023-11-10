package com.tg.base.tb.config;

import com.tg.base.tb.entity.TbSysParamManager;
import com.tg.base.tb.service.TbSysParamManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class TbSysParamConfig {

    @Autowired
    TbSysParamManagerService tbSysParamManagerService;

    public static Map<String, String> hashMap = new HashMap<String, String>();

    @PostConstruct
    public void init() {
        // 查询数据库数据
        List<TbSysParamManager> list = tbSysParamManagerService.list();
        list.stream().forEach(x -> {
            String key = x.getKey();
            String value = x.getValue();
            hashMap.put(key,value);
        });
        logger.info("系统启动成功，codeMap加载完成！");
        logger.info("=======参数列表========");
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for(Map.Entry<String, String> en : entries){
            logger.info("key=" + en.getKey() + ", value=" + en.getValue());
        }
    }

}
