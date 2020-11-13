package com.company;

import java.util.Date;
enum CoinType{
    Dollar,
    Ruble,
    Euro
}

public class BankAccount {

    private Date date;
    private Double Balance = 0.0;
    private Integer ID;
    private String CoinType;

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public String getCoinType() {
        return CoinType;
    }

    public Integer getID() {
        return ID;
    }

    BankAccount(String type){
        date = new Date();
        CoinType = type;
        ID =10;
    }

    public void Deposit(Double Sum){
        setBalance(Sum + getBalance());
    }




}
