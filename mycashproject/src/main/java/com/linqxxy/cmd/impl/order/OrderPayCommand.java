package com.linqxxy.cmd.impl.order;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.common.OrderStatus;
import com.linqxxy.entity.Goods;
import com.linqxxy.entity.Order;
import com.linqxxy.entity.OrderItems;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CommandMeta(
        name = "ZFDD",
        desc="支付订单",
        group="【订单信息】"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("请输入你要购买的货物id以及要购买的数量，多个货物用“,”隔开。格式：1-2,3-5");
        String string=sc.nextLine();
        String[] strings=string.split(",");
        List<Goods> goodsList=new ArrayList<>();
        for(String goodsString:strings){
            String[] buyGoods=goodsString.split("-");
            Goods goods=this.goodsService.getGoods(Integer.parseInt(buyGoods[0]));
            if (goods==null){
                System.out.println("对不起，该商品不存在，请重试");
                return;
            }
            goods.setBuyGoodsNum(Integer.parseInt(buyGoods[1]));
            goodsList.add(goods);
        }
        Order order=new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_Id(subject.getAccount().getId());
        order.setAccount_name(subject.getAccount().getUsername());
        order.setCreate_time(LocalDateTime.now());
        int totalMoney=0;
        int actualMoney=0;
        for (Goods goods:goodsList){
            OrderItems orderItems=new OrderItems();
            orderItems.setOrderId(order.getId());
            orderItems.setGoodsId(goods.getId());
            orderItems.setGoodsName(goods.getName());
            orderItems.setGoodsNum(goods.getBuyGoodsNum());
            orderItems.setGoodsIntroduce(goods.getIntroduce());
            orderItems.setGoodUnit(goods.getUnit());
            orderItems.setGoodsPrice(goods.getPrice());
            orderItems.setGoodDiscount(goods.getDiscount());
            order.orderItemsList.add(orderItems);
            int currentMoney=goods.getBuyGoodsNum()*goods.getPrice();
            totalMoney+=currentMoney;
            actualMoney+=totalMoney*goods.getDiscount()/100;
        }
        order.setActual_ammount(actualMoney);
        order.setTotal_money(totalMoney);
        order.setOrderStatus(OrderStatus.PLAYING);

        //展示当前订单明细
        System.out.println(order);

        System.out.println("请输入是否支付订单: y/n");
        String confirm=sc.next();
        if ("y".equalsIgnoreCase(confirm)){
            order.setFinish_time(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.OK);
            boolean effect =this.orderService.commitOrder(order);
            if (effect){
                for (Goods goods:goodsList){
                    boolean update=this.goodsService.updateAfterPay(goods,goods.getBuyGoodsNum());
                    if (update){
                        System.out.println("库存更新成功");
                    }else {
                        System.out.println("库存更新失败，请手动更新库存");
                    }
                }
                System.out.println("订单已完成");
                System.out.println(order);
            }
        }else {
            System.out.println("订单已取消，如有需要请重新下单");
        }
    }
}

