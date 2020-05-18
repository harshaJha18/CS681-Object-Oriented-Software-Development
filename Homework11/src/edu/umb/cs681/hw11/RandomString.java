package edu.umb.cs681.hw11;

import java.util.Random;

public class RandomString extends Random {
    static int strLen = 5;

    public static String nextString() {
        // https://stackoverflow.com/a/20536597/3536879
        String CHARS = "abcdefgSADFAGhijklmnopDGFGGFSABrstuvwxyzXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < strLen) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            salt.append(CHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String nextString(String SALTCHARS) {
      
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < strLen) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
