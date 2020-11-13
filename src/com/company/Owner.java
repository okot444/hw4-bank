package com.company;

import java.util.List;

public class Owner {

    private List<BankAccount> accounts ;
    private OperationsHistory history;
    private String name;

    public void deposit(Double sum, BankAccount bankAccount){
        bankAccount.Deposit(sum);
    }
    public void withdraw(Double sum, BankAccount bankAccount){
        bankAccount.withdraw(sum);
    }

    public void transfer(Double sum, BankAccount fromAcc, BankAccount toAcc){
        fromAcc.withdraw(sum);
        toAcc.Deposit(sum);
    }

    public void openAccount(CoinType type){
        BankAccount newBankAccount = new BankAccount(type);
        accounts.add(newBankAccount);
    }
    public void closeAccount()
    {
        accounts.indexOf()
    }
}
