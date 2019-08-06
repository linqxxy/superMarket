package com.linqxxy.cmd.impl.goods;

import com.linqxxy.cmd.Subject;
import com.linqxxy.cmd.annotation.AdminCommand;
import com.linqxxy.cmd.annotation.CommandMeta;
import com.linqxxy.cmd.annotation.CustomerCommand;
import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.entity.Goods;

import java.util.List;

@CommandMeta(
        name = "LLSP",
        desc="浏览商品",
        group="【商品信息】"
)
@AdminCommand
@CustomerCommand
public class GoodBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("***********浏览商品***********");
        List<Goods> goodsList=this.goodsService.queryAllGoods();
        if (goodsList.isEmpty()){
            System.out.println("对不起，目前商品为空，请稍后再来");
        }else {
            for (Goods goods: goodsList){
                System.out.println(goods);
            }
        }
    }
}

