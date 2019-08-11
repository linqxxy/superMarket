package com.linqxxy.cmd.impl.account;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Account;

import java.util.List;

@CommandMeta(
        name = "CKZH",
        desc="查看账户",
        group="【账号信息】"
)
@AdminCommand
public class AccountBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("查看账户");
        List<Account> accountList=accountService.browseAccount();
        if (accountList.isEmpty()){
            System.out.println("目前还没有账户");
        }else {
            for (Account account: accountList){
                System.out.println(account);
            }
        }
    }
}
