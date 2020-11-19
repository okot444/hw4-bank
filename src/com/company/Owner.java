package com.company;

import java.io.Serializable;
import java.util.List;

public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<BankAccount> accounts ;
    private OperationsHistory history;
    private String name;

    Owner(String name){
        this.name = name;
    }

    public void deposit(Double sum, BankAccount bankAccount){
        bankAccount.deposit(sum);
        history.addRecord(name, "deposition", bankAccount.getId(),sum);

    }
    public void withdraw(Double sum, BankAccount bankAccount){
        bankAccount.withdraw(sum);
        history.addRecord(name, "withdraw", bankAccount.getId(),sum);
    }

    public void transfer(Double sum, BankAccount fromAcc, BankAccount toAcc){
        fromAcc.withdraw(sum);
        toAcc.deposit(sum);
        history.addRecord(name, "transfer", fromAcc.getId(),sum);
    }

    public void openAccount(CoinType type){
        BankAccount newBankAccount = new BankAccount(type);
        accounts.add(newBankAccount);
    }
    public void closeAccount(BankAccount bankAccount)
    {
        accounts.remove(bankAccount);
    }
    public void getOperationHistory(){
        System.out.println(history);
    }

    public void getBalance(BankAccount bankAccount){
        bankAccount.getBalance();
    }
    public void getBankAccountInfo(BankAccount bankAccount){
        System.out.println(bankAccount);
    }
}
