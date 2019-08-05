package com.linqxxy.cmd.impl.goods;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "LLSP",
        desc="浏览商品",
        group="商品信息"
)
@AdminCommand
@CustomerCommand
public class GoodBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}

