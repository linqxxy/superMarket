package com.linqxxy.service;

import com.linqxxy.dao.AccountDao;
import com.linqxxy.entity.Account;

public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public Account Login(String userName, String password) {

        return this.accountDao.login(userName, password);
    }
    public void register(String userName, String password, String password2, String name, int accountType) {
        this.accountDao.register(userName,password,password2,name,accountType);
    }
}
