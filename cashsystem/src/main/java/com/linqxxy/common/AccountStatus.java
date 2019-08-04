package com.linqxxy.common;

import jdk.nashorn.internal.objects.annotations.Getter;

@Getter
@ToString
public enum AccountStatus {
    UNLOCK(1,"启用"),LOCK(2,"停用");
    private int flag;
    private String desc;
    AccountStatus(int flag,String desc){
        this.flag=flag;
        this.desc=desc;
    }
    public AccountStatus valueOf(int flag){
        for(AccountStatus accountStatus:values()){
            if (accountStatus.flag==flag){
                return  accountStatus;
            }
        }
        throw new RuntimeException("accountStatus flag"+flag+"not found");
    }
}
