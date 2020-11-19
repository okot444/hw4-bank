package com.company;

import java.awt.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Owner o1 = new Owner("sam");
        String fileName = "data.txt";
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(o1);
            outputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Input Output Exception");
            e.printStackTrace();
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            Owner ownerRead = (Owner) objectInputStream.readObject();

            System.out.println(ownerRead);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Input Output Exception");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }

    }
}
