package WS_05.dto;

public abstract class AccountDto implements Comparable<AccountDto> {
	// 캡슐화: 모든 멤버 변수는 private
    private int accountId;
    private String accountNumber;
    private int balance;
    private int userId;
    
    public AccountDto() { }

    public AccountDto(int accountId, String accountNumber, int balance, int userId) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.userId = userId;
    }

    // -------------------------------------------------------
    // 추상화: 자식 클래스에서 반드시 오버라이딩 해야 하는 추상 메소드
    public abstract String getAccountType();

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
	
	// ------------------------------------------------
	@Override
	public int compareTo(AccountDto o) {
		return this.balance - o.balance;
	}
}