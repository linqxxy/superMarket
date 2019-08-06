package com.linqxxy.cmd.impl.goods;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Goods;

@CommandMeta(
        name = "GXSP",
        desc="更新商品",
        group="【商品信息】"
)
@AdminCommand
public class GoodUpdateCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("更新商品");
        print("请输入更新商品的编号");
        int goodsId=sc.nextInt();
        Goods goods=this.goodsService.getGoods(goodsId);
        if (goods==null){
            print("此编号商品不存在");
            return;
        }else {
            print("商品原信息如下");
            System.out.println(goods);
            print("请输入更新的商品简介");
            String introduce=sc.next();
            print("请输入商品库存");
            int stock=sc.nextInt();
            print("商品单位：包，个，箱...");
            String unit=sc.next();
            print("请输入商品价格：单位（分）");
            int price=new Double(100*sc.nextInt()).intValue();
            print("请输入商品折扣");
            int discount=sc.nextInt();
            print("是否确认更新 y/n");
            String confirm=sc.next();
            if ("y".equals(confirm)){
                goods.setDiscount(discount);
                goods.setIntroduce(introduce);
                goods.setPrice(price);
                goods.setDiscount(discount);
                goods.setUnit(unit);
                boolean effect=this.goodsService.modifyGood(goods);
                if (effect){
                    print("商品更新成功");
                }else {
                    print("商品更新失败");
                }
            }else {
                print("退出更新");
            }
        }
    }
}

