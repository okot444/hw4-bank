package com.company;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;


public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    //private static int lastID = 1;
    private static Random rand = new Random();

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

    public BankAccount(CoinType coinType){
        date = new Date();
        this.coinType = coinType;
        id = Math.abs((int)System.currentTimeMillis()) + rand.nextInt(100);
        //lastID++;
        balance = 0.0;
    }

    public void deposit(Double sum){
        setBalance(sum + getBalance());
    }

    public boolean withdraw(Double sum){

        if (balance - sum > 0) {
            setBalance(getBalance() - sum);
            return true;
        }
        else {
            return false;
        }
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
