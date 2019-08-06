package com.linqxxy.cmd.impl.account;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "CZMM",
        desc="重置密码",
        group="【账号信息】"
)
@AdminCommand
public class AccountPasswordCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("输入密码");
    }
}
