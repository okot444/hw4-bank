package com.company;

import java.io.Serializable;
import java.util.Date;


public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
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

        if (balance - sum >0)
            setBalance(getBalance() - sum);
        else
            System.out.println("Not enough money");
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
