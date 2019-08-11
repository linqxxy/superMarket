package com.linqxxy.dao;

import com.linqxxy.common.AccountStatus;
import com.linqxxy.common.AccountType;
import com.linqxxy.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends BaseDao {
    private Connection conn = null;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

    public Account login(String userName, String password) {
        Account account = null;
        try {
            conn = this.getConnection(true);
            String sql = "select id,username,password,name,account_type,account_status " +
                    "from account where username=? and password= ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, DigestUtils.md5Hex(password));
            rs = statement.executeQuery();
            if (rs.next()) {
                account = this.extractAccount(rs);

            }
            closeResource(rs,statement,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    private Account extractAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setName(rs.getString("name"));
        account.setAccountType(AccountType.valueOf(rs.getInt(
                "account_type")));
        account.setAccountStatus(AccountStatus.valueOf(rs.getInt(
                "account_status")));
        return account;
    }
    public void register(String username, String password, String password2, String name, int accountType){
        List<String> list=new ArrayList<>();
        try {
            conn=this.getConnection(true);
            if (password.equals(password2)) {
                String sql1 = "select username from account";
                statement=conn.prepareStatement(sql1);
                rs=statement.executeQuery();
                while (rs.next()){
                    list.add(rs.getString(1));
                }
                closeResource(rs,statement,conn);
                if (list.contains(username)){
                    System.out.println("用户名已被使用，请重试");
                }else {
                    String sql="insert into account (username,password,name,account_type) value(?,?,?,?)";
                    conn=this.getConnection(true);
                    statement=conn.prepareStatement(sql);
                    statement.setString(1,username);
                    statement.setString(2,DigestUtils.md5Hex(password));
                    statement.setString(3,name);
                    statement.setInt(4, accountType);
                    statement.execute();
                    System.out.println("欢迎"+username+"注册");
                    closeResource(rs,statement,conn);
                }
            }else {
                System.out.println("两次密码输入不一致，请重新输入");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally{
            this.closeResource(rs,statement,conn);
        }
    }

    public List<Account> browseAccount() {
        List<Account> accountList=new ArrayList<>();
        try{
            conn=this.getConnection(true);
            String sql="select * from account";
            statement=conn.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Account account=this.extractAccount(rs);
                accountList.add(account);
            }
            return accountList;
        }catch (Exception ex){
            ex.printStackTrace();
            return  null;
        }finally {
            this.closeResource(rs,statement,conn);
        }
    }

    public Account getAccountByPassword(String password) {
        Account account=null;
        try {
            conn=this.getConnection(true);
            String sql="select * from account where password=?";
            statement=conn.prepareStatement(sql);
            statement.setString(1,DigestUtils.md5Hex(password));
            rs=statement.executeQuery();
            while (rs.next()){
               account=this.extractAccount(rs);
            }
            return account;
        }catch (Exception ex){
            ex.printStackTrace();
            return account;
        }finally {
            this.closeResource(rs,statement,conn);
        }
    }

    public boolean updatePassword(String newPassword, Account account) {
        try {
            conn=this.getConnection(true);
            String sql="update account set password= ? where id=?";
            statement=conn.prepareStatement(sql);
            statement.setString(1,DigestUtils.md5Hex(newPassword));
            statement.setInt(2,account.getId());
            return statement.executeUpdate()==1;

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
