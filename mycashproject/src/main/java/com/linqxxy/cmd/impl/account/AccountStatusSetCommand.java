package com.linqxxy.cmd.impl.account;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.common.AccountStatus;
import com.linqxxy.common.AccountType;
import com.linqxxy.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

@CommandMeta(
        name = "ZHGL",
        desc="账户管理",
        group="【账号信息】"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("所有账户");
        List<Account> accountList=accountService.browseAccount();
        if (accountList.isEmpty()){
            System.out.println("目前还没有账户");
        }else {
            for (Account account: accountList){
                System.out.println(account);
            }
        }
        print("请输入要管理的账户编号");
        int id=sc.nextInt();
        Account account=accountService.getAccountById(id);
        print("请为该账户配置使用权限：1为管理员，2为用户");
        account.setAccountType(AccountType.valueOf(sc.nextInt()));
        print("请设置该账户的账号状态：1为启用，2为封禁");
        account.setAccountStatus(AccountStatus.valueOf(sc.nextInt()));
        if (accountService.updateAcccount(account)){
            System.out.println("更新成功");
        }else {
            System.out.println("更新失败");
        }
        return;
    }
}
