package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

public class OperationsHistory implements Serializable {
    private ArrayList<OperationRecord> operationsHistory;

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
