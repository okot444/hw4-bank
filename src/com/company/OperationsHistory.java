package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class OperationsHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<OperationRecord> operationsHistory = new ArrayList<>();

    public void getRecords(){
        for (OperationRecord str : operationsHistory)
        {
            System.out.println(str);
        }
    }
    public void addRecord(String name, String otype, int id, Double sum){
        OperationRecord record = new OperationRecord(name, otype, id, sum);
        operationsHistory.add(record);
    }


    @Override
    public String toString() {
        return "OperationsHistory{" +
                "operationsHistory=" + operationsHistory +
                '}';
    }
}
