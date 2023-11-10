package com.tg.bot.base.bot.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 命令对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBotCommand {

    //命令对象
    protected BotCommand botCommand;

    private static final String REPLY_CONTEXT_FIELD = "reply_context"; //命令回复字段

    @JsonProperty(REPLY_CONTEXT_FIELD)
    @NonNull
    protected String replyContext; ///< Description of the command, 3-256 characters.


    /**
     * 自定义命令集合转官方集合
     * @param commands
     * @return
     */
    public static List<BotCommand> toBotCommand(List<MyBotCommand> commands){
        if(!CollectionUtils.isEmpty(commands)){
            return commands.stream().map(x -> {
                return x.getBotCommand();
            }).collect(Collectors.toList());
        }
        return new ArrayList<BotCommand>();
    }

    /**
     * 从命令列表查找指定命令
     * @param commands
     * @return
     */
    public static MyBotCommand searchCommand(List<MyBotCommand> commands,String command){

        if (!StringUtils.hasText(command)) return null;
        if (CollectionUtils.isEmpty(commands)) return null;

        for (MyBotCommand myBotCommand : commands){
            if(command.equals(myBotCommand.getBotCommand().getCommand())){
                return myBotCommand;
            }
        }

        return null;
    }



}
