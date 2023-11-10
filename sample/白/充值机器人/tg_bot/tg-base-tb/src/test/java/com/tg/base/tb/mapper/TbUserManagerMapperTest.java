package com.tg.base.tb.mapper;

import com.tg.base.tb.entity.TbUserManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TbUserManagerMapperTest {
    @Autowired
    private TbUserManagerMapper tbUserManagerMapper;


    @Test
    public void selectByTgUserName() {
        TbUserManager tbUserManager = tbUserManagerMapper.selectByTgUserName("111");
        logger.info("tbUserManager:{}",tbUserManager);
    }
}
