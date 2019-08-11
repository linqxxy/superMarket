package com.linqxxy.cmd.impl.account;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Account;

@CommandMeta(
        name = "CZMM",
        desc="重置密码",
        group="【账号信息】"
)
@AdminCommand
@CustomerCommand
public class AccountPasswordCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("输入原密码");
        String oldPassword=sc.next();
        Account account=accountService.getAccountByPassword(oldPassword);
        if (account!=null){
            System.out.println("请输入新密码");
            String newPassword=sc.next();
            System.out.println("请再次输入");
            String newPassword2=sc.next();
            if (newPassword.equals(newPassword2)){
                boolean effect=this.accountService.updatePassword(newPassword,subject.getAccount());
                if (effect){
                    subject.setAccount(null);
                    System.out.println("密码更改成功，请重新登录");
                }else {
                    System.out.println("密码更改失败，请联系管理员");
                }
            }else {
                System.out.println("两次密码不一致，请重试");
            }
        }else {
            System.out.println("密码输入错误，请重新尝试");
        }
    }
}
