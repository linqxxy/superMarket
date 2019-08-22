package com.linqxxy.service;

import com.linqxxy.dao.AccountDao;
import com.linqxxy.entity.Account;

import java.util.List;

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

    public List<Account> browseAccount() {
        return accountDao.browseAccount();
    }

    public Account getAccountByPassword(String password) {
        return accountDao.getAccountByPassword(password);
    }

    public boolean updatePassword(String newPassword, Account account) {
        return accountDao.updatePassword(newPassword,account);
    }

    public Account getAccountById(int id) {
        return accountDao.getAccountById(id);
    }

    public boolean updateAcccount(Account account) {
        return accountDao.updateAcccount(account);
    }
}
