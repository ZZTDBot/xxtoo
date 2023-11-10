package com.tg.base.tb.controller;

import com.tg.base.tb.utils.JSONResult;
import com.tg.base.tb.controller.vo.req.*;
import com.tg.base.tb.service.TbBotInstanceManagerService;
import com.tg.base.tb.service.dto.req.TbBotInstanceManagerReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


/**
 * 机器人管理器
 */
@RestController
@RequestMapping("/botmanager")
@Api(description = "机器人管理模块")
@Slf4j
public class BotManagerController {

    @Autowired
    private TelegramBotsApi telegramBotsApi;

    @Autowired
    private TbBotInstanceManagerService tbBotInstanceManagerService;

//    @GetMapping("/getBot/{botTypeId}")
//    @ApiOperation("通过机器人ID查询")
//    public JSONResult getBot(@PathVariable("botTypeId") Integer botTypeId){
//        return JSONResult.ok(tbBotInstanceManagerService.selectById(botTypeId));
//    }

    //查询机器人列表
    @PostMapping("/adminList")
    @ApiOperation("查询机器人列表")
    public JSONResult adminList(@RequestBody TbBotInstanceManagerReqDto tbBotInstanceManagerReqDto){
        return JSONResult.ok(tbBotInstanceManagerService.selectAllBotPageByConditionAdmin(tbBotInstanceManagerReqDto));
    }


    //添加机器人
    @PostMapping("/addBot")
    @ApiOperation("添加机器人")
    public JSONResult addBot(@RequestBody AddBotReq addBotReq){
        return JSONResult.ok(tbBotInstanceManagerService.addBot(addBotReq.getUserTgId(),addBotReq.getBotUserName(),addBotReq.getBotToken(),addBotReq.getBotNo()));
    }

    //更新机器人
    @PostMapping("/updateBot")
    @ApiOperation("更新机器人")
    public JSONResult updateBot(@RequestBody UpdateBotReq updateBotReq){
        return JSONResult.ok(tbBotInstanceManagerService.updateBot(
                updateBotReq.getTbBotTypeId()
                ,updateBotReq.getBotUserName()
                ,updateBotReq.getBotToken()
                ,updateBotReq.getBotNo()
                ,updateBotReq.getBotRemark()
                )
        );
    }

    //删除机器人
    @PostMapping("/deleteBot")
    @ApiOperation("删除机器人")
    public JSONResult deleteBot(@RequestBody DeleteBotReq deleteBotReq){
        return JSONResult.ok(tbBotInstanceManagerService.deleteBot(deleteBotReq.getTbBotTypeId()));
    }

    //启用/停用-机器人
    @PostMapping("/startEndBot")
    @ApiOperation("启用/停用-机器人")
    public JSONResult startEndBot(@RequestBody StartEndBotReq startEndBotReq){
        return JSONResult.ok(tbBotInstanceManagerService.startEndBot(startEndBotReq.getTbBotTypeId(),startEndBotReq.getBotQyStatus()));
    }

    //启动/停止-机器人
    @PostMapping("/upDownBot")
    @ApiOperation("启用/停用-机器人")
    public JSONResult upDownBot(@RequestBody UpDownBotReq upDownBotReq) throws TelegramApiException, ClassNotFoundException {
        return JSONResult.ok(tbBotInstanceManagerService.upDownBot(upDownBotReq.getTbBotTypeId(),upDownBotReq.getRunStatus()));
    }





}
