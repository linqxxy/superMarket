package com.linqxxy.cmd.impl.account;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "ZHGL",
        desc="账户管理",
        group="账号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}
