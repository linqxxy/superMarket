package com.linqxxy.cmd.impl.order;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Order;

import java.util.List;

@CommandMeta(
        name = "LLDD",
        desc="浏览订单",
        group="【订单信息】"
)
@CustomerCommand
public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("我的订单列表");
        List<Order> list=this.orderService.queryOrderByAccount(subject.getAccount().getId());
        if (list.isEmpty()){
            System.out.println("您还没有购买过东西呢！");
        }else {
            for (Order order:list){
                System.out.println("**********************************************************");
                System.out.println(order);
                System.out.println("**********************************************************");
            }
        }
    }
}

