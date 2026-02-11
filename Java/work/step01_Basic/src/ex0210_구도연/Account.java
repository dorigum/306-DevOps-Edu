package ex0210_구도연;

public class Account {
	// public static final int MIN_BALANCE = 0;
    // public static final int MAX_BALANCE = 1000000;
    
	private int balance;

	// set()
	public void setBalance(int balance) {
		if(balance >= 0 && balance <= 1000000) {
			this.balance = balance;
		}
	}

	// get()
	public int getBalance() {
		return this.balance;
	}
}