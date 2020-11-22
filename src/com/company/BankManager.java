package com.company;

import java.io.*;

public final class BankManager {

    private BankManager(){}
    private static BankManager instance;
    public static synchronized BankManager getInstance(){
        if (instance == null){
            instance = new BankManager();
        }
        return instance;
    }


}
