package com.company;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private OperationsHistory history = new OperationsHistory();
    private String name;

    public Owner(String name){
        this.name = name;
    } // если пользователь с таким логином уже существует?

    public void deposit(Double sum, BankAccount bankAccount){
        bankAccount.deposit(sum);
        //history.addRecord(name, "deposition", bankAccount.getId(),sum);

    }
    public void withdraw(Double sum, BankAccount bankAccount){
        bankAccount.withdraw(sum);
        //history.addRecord(name, "withdraw", bankAccount.getId(),sum);
    }

    public boolean transfer(Double sum, BankAccount fromAcc, BankAccount toAcc) {
        if (fromAcc.getCoinType() == toAcc.getCoinType()) {
            boolean f = fromAcc.withdraw(sum);
            if (f) {
                toAcc.deposit(sum);
                return true;
            } else {
                return false;
            }
        }
        return false;
        //history.addRecord(name, "transfer", fromAcc.getId(),sum);
    }

    public BankAccount openAccount(CoinType type) {
        BankAccount newBankAccount = new BankAccount(type);
        accounts.add(newBankAccount);
        //history.addRecord(name, "Account created", newBankAccount.getId(), null);

        return newBankAccount;
    }

    public void closeAccount(BankAccount bankAccount) {
        //history.addRecord(name, "Account closed", bankAccount.getId(), null);
        accounts.remove(bankAccount);

    }
    public BankAccount getBankAccountById(int id) {
        for (BankAccount bankAccount : accounts) {
            if (bankAccount.getId() == id) {
                return bankAccount;
            }
        }
        return null;
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

    public String getName() {
        return name;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }


    @Override
    public String toString() {
        return "Owner{" +
                "accounts=" + accounts +
                ", history=" + history +
                ", name='" + name + '\'' +
                '}';
    }

    public void showInfo() {
        for (BankAccount account : accounts) {
            System.out.println(account.getId());
        }
    }
}
