package edu.umb.cs681.hw05;

public class Main {

    public static void main(String[] args) {
        System.out.printf("*************CS681 Homework 05*************\n");
        System.out.println("\nRunning RunnableCancellablePrimeGenerator for 1 ms...\n");
        RunnableCancellablePrimeGenerator gen =
            new RunnableCancellablePrimeGenerator(1, 160);
        try {
            Thread thread = new Thread(gen);
            thread.start();
            Thread.sleep(1);
            gen.setDone();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n\n" + gen.getPrimes().size() + " prime numbers are found.");
    }
}
