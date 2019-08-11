package com.linqxxy.entity;

import com.linqxxy.common.AccountStatus;
import com.linqxxy.common.AccountType;
import lombok.Data;

@Data
public class Account {
    private  Integer id;
    private String username;
    private  String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("【账户信息】:").append("\n")
                .append("【账户编号】").append(this.getId()).append("\n")
                .append("【账户名称】").append(this.getUsername()).append("\n")
                .append("【用户姓名】").append(this.getName()).append("\n")
                .append("【账户类型】").append(this.getAccountType()).append("\n")
                .append("【账户状态】").append(this.getAccountStatus()).append("\n");
        sb.append("======================================================");
        return sb.toString();
    }
}
