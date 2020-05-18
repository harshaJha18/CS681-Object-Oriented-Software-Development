package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


public class TwoStepBankAccount implements BankAccount {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    private static int UPPER_LIMIT = 300;

    public void deposit(double amount) {
        lock.lock();
        System.out.println("\tTwoStepBankAccount.deposit: secured lock");
        try {
            while (balance >= UPPER_LIMIT) {
                try {
                    System.out.println("\tTwoStepBankAccount.deposit: awaiting withdrawal");                    
                    belowUpperLimitFundsCondition.await();
                } catch (InterruptedException e) {
                    System.out.println("\tTwoStepBankAccount.deposit: interrupted!");
                    return;
                }
            }
            balance += amount;
            System.out.println("\tTwoStepBankAccount.deposit: completed!  new balance is " + balance);
            sufficientFundsCondition.signalAll();
        } finally {
            lock.unlock();
            System.out.println("\tTwoStepBankAccount.deposit: lock released!");
        }
    }

    
    public void withdraw(double amount) {
        lock.lock();
        System.out.println("\tTwoStepBankAccount.withdraw: secured lock");
        try {
            while (balance <= 0) {
                try {
                    System.out.println("\tTwoStepBankAccount.withdraw: awaiting deposit");
                    sufficientFundsCondition.await();
                } catch (InterruptedException e) {
                    System.out.println("\tTwoStepBankAccount.withdraw: interrupted!");
                    return;
                }
            }
            balance -= amount;
            System.out.println("\tTwoStepBankAccount.withdraw: completed! new balance is" + balance);
            belowUpperLimitFundsCondition.signalAll();
        } finally {
            lock.unlock();
            System.out.println("\tTwoStepBankAccount.withdraw: lock released!");
        }
    }

        
}

