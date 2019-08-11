package com.linqxxy.entity;

import com.linqxxy.common.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String id;
    private Integer account_Id;
    private String account_name;
    private LocalDateTime create_time;
    private LocalDateTime finish_time;
    private Integer actual_ammount;
    private Integer total_money;
    private OrderStatus orderStatus;
    public List<OrderItems> orderItemsList= new ArrayList<>();
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("【订单信息】*************************************").append("\t\n");
        sb.append("\t").append("【用户名称】：").append(this.getAccount_name()).append("\t\n");
        sb.append("\t").append("【订单编号】：").append(this.getId()).append("\t\n");
        sb.append("\t").append("【订单状态】：").append(this.getOrderStatus().getDesc()).append("\t\n");
        sb.append("\t").append("【创建时间】：").append(this.timeToString(this.getCreate_time())).append("\t\n");
        if (this.getOrderStatus() == OrderStatus.OK) {
            sb.append("\t").append("【完成时间】：")
                    .append(this.timeToString(this.getFinish_time())).append("\t\n");
        }
        sb.append("【订单明细】*************************************").append("\n");
        sb.append("\t编号   名称     数量     单位     单价（元）").append("\n");
        int i = 0;
        for (OrderItems orderItem : orderItemsList) {
            sb.append("\t").append(++i).append(".\t")
                    .append(orderItem.getGoodsName()).append("\t  ")
                    .append(orderItem.getGoodsNum()).append("      ")
                    .append(orderItem.getGoodUnit()).append("        ")
                    .append(this.moneyToString(orderItem.getGoodsPrice())).append("\t")
                    .append("\n");
        }
        sb.append("【订单金额】*************************************").append("\n");
        sb.append("\t").append("【总金额】：").append(this.moneyToString(this.getTotal_money()))
                .append(" 元 ").append("\n");
        sb.append("\t").append("【优惠金额】：").append(this.moneyToString(this.getDiscount()))
                .append(" 元 ").append("\n");
        sb.append("\t").append("【应支付金额】：").append(this.moneyToString(this.getActual_ammount()))
                .append(" 元 ").append("\n");
        return sb.toString();
    }

    private String moneyToString(int money) {
        return String.format("%.2f", 1.00D * money / 100);
    }

    private String timeToString(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(time);
    }

    //优惠
    public Integer getDiscount() {
        return this.getTotal_money() - this.getActual_ammount();
    }

}
