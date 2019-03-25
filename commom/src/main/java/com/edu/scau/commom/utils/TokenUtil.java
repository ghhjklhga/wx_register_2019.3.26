package com.edu.scau.commom.utils;

import java.util.Random;

public class TokenUtil {

    public static String getToken(){
        return randomCode("1234567890abcdefghijklmnopqrstuvwxyz", 32);
    }

    private static String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<size; i++){
            int index = random.nextInt(s.length());
            result.append(s.charAt(index));
        }
        return result.toString();
    }
}
