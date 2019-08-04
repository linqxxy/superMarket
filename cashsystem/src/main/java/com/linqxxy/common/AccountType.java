package com.linqxxy.common;

public enum AccountType {
    ADMIN(1,"管理员"),CUSTOMER(2,"顾客");
    private int flag;
    private String desc;
    AccountType(int flag,String desc){
        this.flag=flag;
        this.desc=desc;
    }
    public AccountType valueOf(int flag){
        for(AccountType accountStatus:values()){
            if (accountStatus.flag==flag){
                return  accountStatus;
            }
        }
        throw new RuntimeException("accountStatus flag"+flag+"not found");
    }
}
