package com.linqxxy.common;

public enum OrderStatus {
    PLAYING(1,"待支付"),OK(2,"支付完成");
    private int flg;
    private String desc;
    OrderStatus(int flg,String desc) {
        this.flg = flg;
        this.desc = desc;
    }
    public OrderStatus valueOf(int flg) {
        for (OrderStatus orderStatus : values()) {
            if(orderStatus.flg == flg) {
                return orderStatus;
            }
        }
        throw new RuntimeException("orderStatus flg"
                + flg + "not fount!");
    }

}
