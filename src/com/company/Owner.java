package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<BankAccount> accounts;
    private ArrayList<OperationRecord> history;
    private String name;

    public Owner(String name){
        history = new ArrayList<>();
        this.name = name;
        accounts = new ArrayList<>();
    }

    public void deposit(Double sum, BankAccount bankAccount){
        bankAccount.deposit(sum);
        history.add(new OperationRecord(name, "deposition             ", bankAccount.getId(),sum,bankAccount.getCoinType()));
    }
    public boolean withdraw(Double sum, BankAccount bankAccount){
        boolean f = bankAccount.withdraw(sum);
        if (f)
            history.add(new OperationRecord(name, "withdraw                ", bankAccount.getId(),-sum,bankAccount.getCoinType()));
        else
            history.add(new OperationRecord(name, "withdraw failed   ", bankAccount.getId(),0.0,bankAccount.getCoinType()));
        return f;
    }

    public boolean transfer(Double sum, BankAccount fromAcc, BankAccount toAcc) {
        if (accounts.contains(fromAcc) && accounts.contains(toAcc)){
            if (fromAcc != toAcc) {
                if (fromAcc.getCoinType() == toAcc.getCoinType()) {
                    boolean f = fromAcc.withdraw(sum);
                    if (f) {
                        toAcc.deposit(sum);
                        history.add(new OperationRecord(name, "transfer from        ", fromAcc.getId(), sum,fromAcc.getCoinType()));
                        return true;
                    } else {
                        history.add(new OperationRecord(name, "transfer failed      ", fromAcc.getId(), 0.0,fromAcc.getCoinType()));
                    }
                }
            }
        }
        return false;
    }

    public BankAccount openAccount(CoinType type) {
        BankAccount newBankAccount = new BankAccount(type);
        accounts.add(newBankAccount);
        history.add(new OperationRecord(name, "Account created", newBankAccount.getId(), null,type));
        return newBankAccount;
    }

    public void closeAccount(BankAccount bankAccount) {
        history.add(new OperationRecord(name, "Account closed ", bankAccount.getId(), null,bankAccount.getCoinType()));
        accounts.remove(bankAccount);
    }
    public BankAccount getBankAccountById(int id) {
       for (BankAccount bankAccount : accounts) {
            if (bankAccount.getId() == id) {
                return bankAccount;
            }
        }
        return null;
       //accounts.get(id);  ошибка, если не существует такого id
    }

    public ArrayList<OperationRecord> getOperationHistory(){
        return history;
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
                ", name='" + name +
                '}';
    }

    public String printInfo() {
        String forPrint = "Владелец: " + getName() + "\nКоличество счетов: " + accounts.size() + "\n";
        for (BankAccount account : accounts) {
            forPrint += "счёт № "+ account.getId()+": " + account.printAmountMoney() + "\n";
        }

        return forPrint;
    }
}
