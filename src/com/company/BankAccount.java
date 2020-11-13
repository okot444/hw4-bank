package com.company;

import java.util.Date;


public class BankAccount {

    private Date date;
    private Double balance = 0.0;
    private Integer id;
    private String coinType;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public String getCoinType() {
        return coinType;
    }

    public Integer getID() {
        return id;
    }

    BankAccount(String type){
        date = new Date();
        coinType = type;
        id = 10;
    }

    public void Deposit(Double Sum){
        setBalance(Sum + getBalance());
    }

    public void Withdraw(Double Sum){
        setBalance(getBalance() - Sum);
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "date=" + date +
                ", Balance=" + balance +
                ", ID=" + id +
                ", CoinType='" + coinType + '\'' +
                '}';
    }



}
