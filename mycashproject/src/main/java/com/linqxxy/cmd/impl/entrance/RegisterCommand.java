package com.linqxxy.cmd.impl.entrance;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.EntranceCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.common.AccountType;
import com.linqxxy.entity.Account;

@CommandMeta(
        name = "ZC",
        desc="注册",
        group="【入口命令】"
)
@EntranceCommand
public class RegisterCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account=subject.getAccount();
        if(account!=null){
            System.out.println("您已经登录过");
        }else {
            System.out.println("请输入用户名");
            String username=sc.nextLine();
            System.out.println("请输入密码");
            String password1=sc.nextLine();
            System.out.println("请再次输入密码");
            String password2=sc.nextLine();
            System.out.println("请输入您的姓名");
            String name=sc.nextLine();
            System.out.println("请输入账户类型:1 管理员 2 用户");
            int accountType=sc.nextInt();
            this.accountService.register(username,password1,password2,name,accountType);
        }
    }
}

