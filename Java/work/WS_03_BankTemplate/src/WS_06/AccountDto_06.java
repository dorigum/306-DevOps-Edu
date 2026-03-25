package WS_06;

public class AccountDto_06 {
	private int accountId;
	private String accountNumber;
	private int balance;
	private int userId;

	public AccountDto_06(int accountId, String accountNumber, int balance, int userId) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.userId = userId;
	}

	// getter, setter
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "계좌일련번호: " + accountId + ", 계좌번호: " + accountNumber + ", 잔액: " + balance + ", 고객일련번호: " + userId;
	}
}