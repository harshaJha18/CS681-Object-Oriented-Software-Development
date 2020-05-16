package edu.umb.cs681.hw04;

import java.io.*;
import java.util.stream.Stream;
import java.lang.InterruptedException;

public class Main {

    private static Integer[] threadNums = {1, 2, 4, 8, 16};
    private static long from = 1, to = 2000000;
    public static void main(String[] args) {
        System.out.printf("*************CS681 Homework 04*************\n");

        timeJavaPrimes();
    }

 

    private static void timeJavaPrimes() {
        System.out.println("\nRunning prime generator...");
        for (int numThreads : threadNums) {
            timeJavaPrimesForFixedThreadNum(numThreads);
        }
    }


    private static void timeJavaPrimesForFixedThreadNum(int numThreads) {
        long startTime = System.nanoTime();
        VariThreadedPrimeGenerator vtpg =
            new VariThreadedPrimeGenerator(from, to, numThreads);
        long endTime = System.nanoTime();
        long msDuration = (endTime - startTime) / 1000000;
        System.out.printf("Number of %d threads took %d ms\n",
                          numThreads, msDuration);
    }


}

