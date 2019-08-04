package com.linqxxy.entity;

import com.linqxxy.common.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private String id;
    private Integer account_Id;
    private String account_name;
    private LocalDateTime create_time;
    private LocalDateTime finish_time;
    private Integer actual_ammount;
    private Integer total_money;
    private OrderStatus orderStatus;
}
