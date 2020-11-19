package com.company;

import java.io.Serializable;
import java.util.Date;


public class BankAccount implements Serializable {
    private static final long serialVersionUID = 2L;
    private static int lastID = 1;

    private Date date;
    private Double balance;
    private int id;
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

    public Integer getId() {
        return id;
    }

    BankAccount(CoinType coinType){
        date = new Date();
        this.coinType = coinType;
        lastID++;
        id = lastID;

    }

    public void deposit(Double sum){
        setBalance(sum + getBalance());
    }

    public void withdraw(Double sum){
        setBalance(getBalance() - sum);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "date=" + date +
                ", balance=" + balance +
                ", id=" + id +
                ", coinType=" + coinType +
                '}';
    }
}
