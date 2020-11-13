package com.company;

import java.util.Date;


public class BankAccount {

    private Date date;
    private Double balance;
    private Integer ID;
    private CoinType coinType;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public Integer getID() {
        return ID;
    }

    BankAccount(CoinType coinType){
        date = new Date();
        this.coinType = coinType;
        ID = 10;
    }

    public void Deposit(Double sum){
        setBalance(sum + getBalance());
    }

}
