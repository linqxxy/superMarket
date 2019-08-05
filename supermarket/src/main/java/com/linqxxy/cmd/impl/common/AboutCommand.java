package com.linqxxy.cmd.impl.common;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.annotation.EntranceCommand;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "GYXT",
        desc="关于系统",
        group="公共功能"
)
@AdminCommand
@EntranceCommand
@CustomerCommand
public class AboutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("********    字符界面收银系统    ********");
        System.out.println("********  designed by linqxxy  ********");
        System.out.println("********      2019-08-04       ********");
    }
}
