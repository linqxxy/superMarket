package com.linqxxy.cmd.impl;


import com.linqxxy.cmd.Command;
import com.linqxxy.service.AccountService;
import com.linqxxy.service.GoodsService;
import com.linqxxy.service.OrderService;

public abstract class AbstractCommand implements Command {
    //start all service
    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;
    public AbstractCommand(){
        this.accountService=new AccountService();
        this.goodsService =new GoodsService();
        this.orderService=new OrderService();
    }public void print(String str){
        System.out.println(str);
    }

}
