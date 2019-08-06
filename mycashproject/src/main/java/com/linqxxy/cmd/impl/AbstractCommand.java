package com.linqxxy.cmd.impl;


import com.linqxxy.cmd.Command;
import com.linqxxy.service.AccountService;
import com.linqxxy.service.GoodsService;

public abstract class AbstractCommand implements Command {
    //start all service
    public AccountService accountService;
    public GoodsService goodsService;
    public AbstractCommand(){
        this.accountService=new AccountService();
        this.goodsService =new GoodsService();
    }public void print(String str){
        System.out.println(str);
    }

}
