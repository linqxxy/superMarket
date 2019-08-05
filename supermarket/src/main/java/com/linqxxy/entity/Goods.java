package com.linqxxy.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer id;
    private String name;
    private String introduce;
    private String stock;
    private String unit;
    private Integer price;
    private Integer discount;
}
