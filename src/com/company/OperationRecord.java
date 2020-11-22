package com.company;

import java.io.Serializable;
import java.util.Date;

public class OperationRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operationType;
    private int bankAccountID;
    private String paymentSender;
    private Date date;
    private Double sum;
    private CoinType coinType;

    OperationRecord(String sender, String otype, int id, Double sum,CoinType coinType){
        operationType = otype;
        bankAccountID = id;
        paymentSender = sender;
        date = new Date();
        this.sum = sum;
        this.coinType = coinType;
    }

    public String getOperationType() {
        return operationType;
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public Date getDate() {
        return date;
    }

    public Double getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Operation Record{" +
                "operation Type ='" + operationType +
                ", bank Account ID =" + bankAccountID +
                ", Account owner ='" + paymentSender +
                ", date =" + date +
                ", sum =" + sum +
                ", coinType =" + coinType +
                '}';
    }

    public String getCoinType() {
        return coinType.toString();
    }
}
