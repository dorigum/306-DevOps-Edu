package WS_05.dto;

public class InstallAccountDto extends AccountDto {
	private int period; // 예치 기간
	private int amount; // 월 납입 금액

	public InstallAccountDto() { }
	
	public InstallAccountDto(int accountId, String accountNumber, int balance, int userId, int period, int amount) {
		super(accountId, accountNumber, balance, userId); // 부모 클래스(AccountDto_05)의 생성자 호출
		this.period = period;
		this.amount = amount;
	}
	
	@Override
	public String getAccountType() {
		return "자유 입출금 계좌";
	}
	
	// -------------------------------------------------------
	// 캡슐화를 위한 getter, setter
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	// -------------------------------------------------------
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(super.toString());
		builder.append(" | ");
		builder.append(period);
		builder.append(" | ");
		builder.append(amount);
	
		return builder.toString();
	}
}