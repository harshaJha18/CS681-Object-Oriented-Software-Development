package edu.umb.cs681.hw11;

import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.Random;

public class Main {

    private static Address randomAddress() {
        RandomString rString = new RandomString();
        return new Address(rString.nextString("abcdefghijklmnopqrstuvwxyz"), rString.nextString("01234"), rString.nextString(), rString.nextString("0123456789"));
    }
    
    public static void main(String[] args) {
        System.out.printf("*************CS681 Homework 11**************\n");
        Customer user = new Customer(randomAddress());
        System.out.printf("\nMain: created customer with this random address:\n%s\n\n",
                          user.getAddress());
        int numThreads = 8;
        LinkedList<Thread> threads = new LinkedList<Thread>();
        LinkedList<GracefullyStoppingRunnable> runnables =
            new LinkedList<GracefullyStoppingRunnable>();
        IntStream.range(0, numThreads).forEach((i) -> {        
                Runnable r  = () -> {
                    user.setAddress(randomAddress());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                };
                GracefullyStoppingRunnable gsr = GracefullyStoppingRunnable.fromRunnable(r);
                Thread thread = new Thread(() -> gsr.run());
                threads.add(thread);
                runnables.add(gsr);
                thread.start();
            });
        try {
            Thread.sleep(200);
                } catch (InterruptedException e) {
            System.out.printf("HomeWork 11 interrupted");
        };
        runnables.forEach((r) -> r.gracefulStop());
    }
}
