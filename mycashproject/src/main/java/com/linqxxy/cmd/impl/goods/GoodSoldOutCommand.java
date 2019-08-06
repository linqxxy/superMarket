package com.linqxxy.cmd.impl.goods;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Goods;

@CommandMeta(
        name = "XJSP",
        desc = "下架商品",
        group = "【商品信息】"
)
@AdminCommand
public class GoodSoldOutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        print("请输入要删除的商品编号");
        int goodsId = sc.nextInt();
        Goods goods = goodsService.getGoods(goodsId);
        if (goods==null){
            print("编号为"+goodsId+"的商品不存在");
        }else {
            System.out.println(goods);
        }
        print("是否确认删除：y / n");
        String confirm = sc.next();
        if ("y".equals(confirm)) {
            boolean effect = this.goodsService.deleteGoods(goodsId);
            if (effect) {
                print("商品编号为" + goodsId + "的商品删除成功");
            } else {
                print("删除失败，请重试");
            }
        } else {
            print("删除已取消");
        }
    }
}
