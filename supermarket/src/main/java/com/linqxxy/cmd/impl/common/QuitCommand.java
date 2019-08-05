package com.linqxxy.cmd.impl.common;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.annotation.EntranceCommand;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "EXIT",
        desc="退出系统",
        group="公共功能"
)
@AdminCommand
@EntranceCommand
@CustomerCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}
