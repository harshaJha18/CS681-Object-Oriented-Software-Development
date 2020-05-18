package edu.umb.cs681.hw13;

public class DepositRunnable implements Runnable {
	private BankAccount account;
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
				account.deposit(100);
				Thread.sleep(2);
			}
		}catch(InterruptedException exception){}
	}
}
