package com.linqxxy.cmd.impl.common;

import com.linqxxy.cmd.Command;
import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.annotation.EntranceCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.cmd.impl.Commands;
import com.linqxxy.entity.Account;

import java.util.*;

@CommandMeta(
        name = "HELP",
        desc = "帮助信息",
        group = "公共功能"
)
@AdminCommand
@EntranceCommand
@CustomerCommand
public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account = subject.getAccount();
        if (account == null) {
            entranceHelp();
        } else {
            switch (account.getAccountType()) {
                case CUSTOMER: customerHelp();
                    break;
                case ADMIN: adminHelp();
                    break;
                default:
            }
        }
    }

    private void entranceHelp() {
        print("欢迎", Commands.ENTRANCE_COMMANDS.values());
    }
    private void customerHelp() {
        print("客户端", Commands.CUSTOMER_COMMANDS.values());
    }
    private void adminHelp() {
        print("管理员", Commands.ADMIN_COMMANDS.values());
    }
    private void print(String title, Collection<Command> values) {
        System.out.println("***********" + title + "**********");
        Map<String, List<String>> helpInfo = new HashMap<>();
        for (Command command :
                values) {
            CommandMeta commandMeta=command.getClass().
                    getDeclaredAnnotation(CommandMeta.class);
            String group=commandMeta.group();
            List<String> func = helpInfo.computeIfAbsent(group, k -> new ArrayList<>());
            func.add(commandMeta.desc()+"("+commandMeta.name()+")");
        }
        int i = 0;
        for (Map.Entry<String, List<String>> entry:
        helpInfo.entrySet()){
            i++;
            System.out.println(i+"."+entry.getKey());
            int j=0;
            for (String item :
                    entry.getValue()) {
                j++;
                System.out.println("\t"+i+"."+j+item);
            }
        }
    }
}
