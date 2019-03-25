package com.edu.scau.commom.utils;

import java.util.Random;

public class KeyUtil {

    public static String getUniqueKey(){
        Random random = new Random(System.currentTimeMillis());
        Integer key = random.nextInt(900000) + 100000;
        return String.valueOf(key) + System.currentTimeMillis();
    }
}
