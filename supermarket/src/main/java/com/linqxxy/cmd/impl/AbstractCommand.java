package com.linqxxy.cmd.impl;


import com.linqxxy.cmd.Command;
import com.linqxxy.service.AccountService;

public abstract class AbstractCommand implements Command {
    //start all service
    public AccountService accountService;
    public AbstractCommand(){
        this.accountService=new AccountService();
    }
}
