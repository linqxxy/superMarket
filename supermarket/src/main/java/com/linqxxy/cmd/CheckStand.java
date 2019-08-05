package com.linqxxy.cmd;

import com.linqxxy.cmd.impl.AbstractCommand;
import com.linqxxy.cmd.impl.Commands;
import com.linqxxy.entity.Account;


public class CheckStand extends AbstractCommand {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new CheckStand().execute(subject);
    }

    @Override
    public void execute(Subject subject) {
        Commands.getCachedHelpCommands().execute(subject);
        while (true){
            System.out.println(">>>");
            String line=sc.nextLine();
            String commandCode=line.trim().toUpperCase();
            Account account=subject.getAccount();
            if (account==null){
                Commands.getEntranceCommand(commandCode).execute(subject);
                execute(subject);
            }else {
                switch (account.getAccountType()){
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).execute(subject);
                        break;
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).execute(subject);
                        break;
                }
            }
        }
    }
}
