package com.linqxxy.common;

import lombok.ToString;

@ToString
public enum AccountType {
    ADMIN(1,"管理员"),CUSTOMER(2,"顾客");
    private int flag;
    private String desc;
    AccountType(int flag,String desc){
        this.flag=flag;
        this.desc=desc;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static AccountType valueOf(int flag){
        for(AccountType accountType:values()){
            if (accountType.flag==flag){
                return  accountType;
            }
        }
        throw new RuntimeException("accountType flag"+flag+"not found");
    }
}
