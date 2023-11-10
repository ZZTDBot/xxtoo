package com.tg.base.tb.controller;

import com.tg.base.tb.context.UserContext;
import com.tg.base.tb.utils.JSONResult;
import com.tg.base.tb.service.TbUserBotinstanceQunManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userBotQunManager")
@Api(description = "用户-机器人-群列表---管理接口")
@Slf4j
public class UserBotQunManagerController {

    @Autowired
    private TbUserBotinstanceQunManagerService tbUserBotinstanceQunManagerService;

    /**
     * 查询我的群列表
     */
    @GetMapping("/queryMyGroupList")
    @ApiOperation("查询我的群列表")
    public JSONResult queryMyGroupList(){
        return JSONResult.ok(tbUserBotinstanceQunManagerService.queryGroupListByUserIdAndBotId(UserContext.getUser().getTgUserId(),UserContext.getUser().getBotInstanceId()));
    }

    //1.添加登录验证码
    //2.删除登录验证码
    //1.查询登录验证码列表

}
