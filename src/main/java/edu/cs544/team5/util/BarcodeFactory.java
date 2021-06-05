package edu.cs544.team5.util;

import java.util.UUID;

public class BarcodeFactory {

    public static String getBarcore(){
        return UUID.randomUUID().toString();
    }
}
