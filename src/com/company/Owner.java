package com.company;

import java.util.List;

public class Owner {

    private List<BankAccount> accounts ;
    private OperationsHistory history;
    private String name;

    public void deposit(Double sum, BankAccount bAcc){
        bAcc.Deposit(sum);
    }
    public void withdraw(Double sum, BankAccount bAcc){
        bAcc.Withdraw(sum);
    }

    public void transfer(Double sum, BankAccount fromAcc, BankAccount toAcc){
        fromAcc.Withdraw(sum);
        toAcc.Deposit(sum);
    }

    public void openAccount(String type){
        BankAccount newBAcc = new BankAccount(type);
        accounts.add(newBAcc);
    }
    public void closeAccount()
    {
        accounts.indexOf()
    }
}
