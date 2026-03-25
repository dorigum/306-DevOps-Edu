package WS_05.dto;

public class SavingAccountDto extends AccountDto {
	private int transferFee; // 이체 수수료

	public SavingAccountDto() { }
	
	public SavingAccountDto(int accountId, String accountNumber, int balance, int userId, int transferFee) {
		super(accountId, accountNumber, balance, userId);
		this.transferFee = transferFee;
	}

	@Override
	public String getAccountType() {
		return "정기 적금 계좌";
	}
	
	// ---------------------------------------------
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(super.toString());
		builder.append(" | ");
		builder.append(transferFee);
		
		return builder.toString();
	}
	
	// ---------------------------------------------
	// 캡슐화를 위한 getter, setter
	public int getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(int transferFee) {
		this.transferFee = transferFee;
	}
}