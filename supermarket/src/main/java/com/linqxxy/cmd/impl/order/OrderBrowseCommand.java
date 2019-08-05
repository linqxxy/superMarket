package com.linqxxy.cmd.impl.order;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "LLDD",
        desc="浏览订单",
        group="订单信息"
)
@CustomerCommand
public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}

