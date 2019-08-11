package com.linqxxy.cmd.impl.entrance;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.EntranceCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.common.AccountStatus;
import com.linqxxy.entity.Account;

@CommandMeta(
        name = "DL",
        desc = "登录",
        group = "【入口命令】"
)
@EntranceCommand
public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account = subject.getAccount();
        if (account != null) {
            System.out.println("您已经登陆过");
        } else {
            System.out.println("请输入用户名");
            String username = sc.nextLine();
            System.out.println("请输入密码");
            String password = sc.nextLine();
            account = this.accountService.Login(username, password);
            if (account != null) {
                if (account.getAccountStatus() == AccountStatus.LOCK) {
                    System.out.println("您的账号涉嫌违规操作，已被暂停使用");
                } else {
                    System.out.println(account.getAccountType() + "登陆成功");
                    subject.setAccount(account);
                }
            } else {
                System.out.println("密码或者用户名错误");
            }
        }
    }
}

