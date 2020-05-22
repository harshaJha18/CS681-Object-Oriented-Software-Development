package edu.umb.cs681.hw06;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        System.out.printf("*************CS681 Homework 06*************\n");
        int dividend = 3 * 11 * 31 * 79;
        run(dividend, 1);
        runStop(dividend, 2, 1, 0);
        runStop(dividend, 2, 1, 5);
        runStop(dividend, 2, 1, 10);
        runStop(dividend, 2, 1, 30);
        runStop(dividend, 2, 1, 60);

        dividend = 2 * 17;
        run(dividend, 1);
        runStop(dividend, 2, 1, 100);

    }

    private static void run(long dividend, int numThreads) {
        Factorizer f = new Factorizer(dividend, numThreads, 0);
        f.run();
        f.finish();
        f.printResults();
    }

    private static void runStop(long dividend, int numThreads, int latency, int patience) {
        Factorizer f = new Factorizer(dividend, numThreads, latency);
        f.run();
        try {
            Thread.sleep(patience);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        f.stop();
        f.printResults();
    }

}
