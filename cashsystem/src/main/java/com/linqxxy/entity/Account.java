package com.linqxxy.entity;

import com.linqxxy.common.AccountStatus;
import com.linqxxy.common.AccountType;

@Data
public class Account {
    private  Integer id;
    private String username;
    private  String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
