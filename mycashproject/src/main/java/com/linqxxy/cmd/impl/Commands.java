package com.linqxxy.cmd.impl;

import com.linqxxy.cmd.Command;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.annotation.EntranceCommand;
import com.linqxxy.cmd.impl.account.AccountBrowseCommand;
import com.linqxxy.cmd.impl.account.AccountPasswordCommand;
import com.linqxxy.cmd.impl.account.AccountStatusSetCommand;
import com.linqxxy.cmd.impl.common.AboutCommand;
import com.linqxxy.cmd.impl.common.HelpCommand;
import com.linqxxy.cmd.impl.common.QuitCommand;
import com.linqxxy.cmd.impl.entrance.LoginCommand;
import com.linqxxy.cmd.impl.entrance.RegisterCommand;
import com.linqxxy.cmd.impl.goods.GoodBrowseCommand;
import com.linqxxy.cmd.impl.goods.GoodPutAwayCommand;
import com.linqxxy.cmd.impl.goods.GoodSoldOutCommand;
import com.linqxxy.cmd.impl.goods.GoodUpdateCommand;
import com.linqxxy.cmd.impl.order.OrderBrowseCommand;
import com.linqxxy.cmd.impl.order.OrderPayCommand;

import java.util.*;

public class Commands {
    public static final Map<String, Command> ADMIN_COMMANDS = new HashMap<>();
    public static final Map<String, Command> CUSTOMER_COMMANDS = new HashMap<>();
    public static final Map<String, Command> ENTRANCE_COMMANDS = new HashMap<>();

    private static final Set<Command> COMMANDS = new HashSet<>();

    private static final Command CACHED_HELP_COMMANDS;

    static {
        Collections.addAll(COMMANDS,
                new AccountBrowseCommand(),
                new AccountPasswordCommand(),
                new AccountStatusSetCommand(),
                new AboutCommand(),
                CACHED_HELP_COMMANDS = new HelpCommand(),
                new QuitCommand(),
                new LoginCommand(),
                new RegisterCommand(),
                new GoodBrowseCommand(),
                new GoodPutAwayCommand(),
                new GoodSoldOutCommand(),
                new GoodUpdateCommand(),
                new OrderBrowseCommand(),
                new OrderPayCommand()
        );
        for (Command command : COMMANDS) {
            //利用反射将命令分配到不同的Map
            Class<?> cls = command.getClass();
            AdminCommand adminCommand = cls.getDeclaredAnnotation(
                    AdminCommand.class);
            CustomerCommand customerCommand = cls.getDeclaredAnnotation(
                    CustomerCommand.class
            );
            EntranceCommand entranceCommand = cls.getDeclaredAnnotation(
                    EntranceCommand.class
            );
            CommandMeta commandMeta = cls.getDeclaredAnnotation(
                    CommandMeta.class
            );
            String commandKey = commandMeta.name();
            if (adminCommand != null) {
                ADMIN_COMMANDS.put(commandKey, command);
            }
            if (customerCommand != null) {
                CUSTOMER_COMMANDS.put(commandKey, command);
            }
            if (entranceCommand != null) {
                ENTRANCE_COMMANDS.put(commandKey, command);
            }
        }
    }

    //得到HelpCommand缓存命令
    public static Command getCachedHelpCommands() {
        return CACHED_HELP_COMMANDS;
    }

    public static Command getAdminCommand(String commandKey) {
        return getCommand(commandKey,ADMIN_COMMANDS);

    }

    public static Command getCustomerCommand(String commandKey) {
        return getCommand(commandKey,CUSTOMER_COMMANDS);

    }

    public static Command getEntranceCommand(String commandKey) {
        return getCommand(commandKey,ENTRANCE_COMMANDS);

    }

    public static Command getCommand(String commandKey,Map<String,Command>
                              commandMap) {
        return commandMap.getOrDefault(commandKey,
                CACHED_HELP_COMMANDS);

    }
}
