package com.linqxxy.cmd.impl.goods;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;

@CommandMeta(
        name = "SJSP",
        desc="上架商品",
        group="商品信息"
)
@AdminCommand
public class GoodPutAwayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}

