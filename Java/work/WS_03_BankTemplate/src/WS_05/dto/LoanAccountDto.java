package WS_05.dto;

public class LoanAccountDto extends AccountDto {
    private String collateral; // 대출 담보(캡슐화 적용)

    public LoanAccountDto() { }
    
    public LoanAccountDto(int accountId, String accountNumber, int balance, int userId, String collateral) {
        super(accountId, accountNumber, balance, userId); // 부모 생성자 호출
        this.collateral = collateral;
    }
    
    @Override
    public String getAccountType() {
        return "대출 계좌 (담보: " + this.collateral + ")";
    }
    // -------------------------------------------------------
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(super.toString() );
		builder.append(" | ");
		builder.append(collateral);
		
		return builder.toString();
    }
}