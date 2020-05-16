package edu.umb.cs681.hw07;

import java.util.Random;

class RandomString {
    static int strLen = 5;

    public static String generate() {
        // https://stackoverflow.com/a/20536597/3536879
        String SALTCHARS = "abcdefghijkDFUAYPUYNFDUFYlmnopqJGDSJArstuvwxyzXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < strLen) { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
