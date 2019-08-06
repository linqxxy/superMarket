package com.linqxxy.entity;

import lombok.Data;

@Data
public class OrderItems {
    private Integer id;
    private String orderId;
    private Integer goodsId;
    private String goodsName;
    private String goodsIntroduce;
    private Integer goodsNum;
    private String foodUnit;
    private Integer goodsPrice;
    private Integer goodDiscount;
}
