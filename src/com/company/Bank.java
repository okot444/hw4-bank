package com.company;


import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Bank {
    private static ArrayList<Owner> ownersList = new ArrayList<>();

    public Bank (String filenameIn) throws IOException, ClassNotFoundException {
        load(filenameIn);
    }

    public Bank() {
        super();
    }

    public void load(String fileName) throws IOException, ClassNotFoundException {
        try  (FileInputStream fileInput = new FileInputStream(fileName);
              ObjectInputStream objInput = new ObjectInputStream(fileInput)){
            ownersList = (ArrayList<Owner>)objInput.readObject();
        }
    }

    public void save(String fileIn) throws IOException {
        try  (FileOutputStream fileOut = new FileOutputStream(fileIn);
              ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
            objOut.writeObject(ownersList);
        }
    }

    public void addOwner(Owner owner) {
        ownersList.add(owner);
    }

    public void showOwners() {
        for (Owner owner : ownersList) {
            owner.showInfo();
        }
    }

    public Owner checkOwner(String name) {
        for (Owner owner : ownersList) {
            if (owner.getName().equals(name)) {
                return owner;
            }
        }
        return null;
    }
}
