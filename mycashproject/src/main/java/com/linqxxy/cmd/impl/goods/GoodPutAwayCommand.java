package com.linqxxy.cmd.impl.goods;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Goods;

@CommandMeta(
        name = "SJSP",
        desc="上架商品",
        group="【商品信息】"
)
@AdminCommand
public class GoodPutAwayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        print("*********上架商品**********");
        print("请输入商品名称");
        String name=sc.nextLine();
        print("请输入商品简介：");
        String introduce=sc.nextLine();
        print("请输入商品库存：");
        int stock= Integer.parseInt(sc.nextLine());
        print("请输入商品单位：包，个，箱...");
        String unit=sc.nextLine();
        print("请输入商品价格：单位（分）");
        int price=new Double(100*sc.nextInt()).intValue();
        print("请输入商品折扣：");
        int discount=sc.nextInt();

        Goods goods=new Goods();
        goods.setUnit(unit);
        goods.setPrice(price);
        goods.setName(name);
        goods.setStock(stock);
        goods.setIntroduce(introduce);
        goods.setDiscount(discount);
        boolean effect= goodsService.goodPutAway(goods);
        if (effect){
            print("添加商品"+goods.getName()+"成功");
        }else {
            print("添加失败");
        }
    }
}

