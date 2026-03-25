package chapter11_ex;

public class LoginExample {
	// chapter11_p487(확인 문제 07번)
	public static void main(String[] args) {
		try {
			login("white", "12345");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			login("blue", "54321");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ★throws 키워드로 이 메소드가 던질 수 있는 예외들을 명시해야함!!!!!
	public static void login(String id, String password) throws NotExistIDException, WrongPasswordException {
		// ★강제로 예외 발생시키기(throw)★
		// id가 blue가 아니라면 NotExistIDException을 발생시킴
		if (!id.equals("blue")) {
			throw new NotExistIDException("아이디가 존재하지 않습니다.");
		}

		// password가 12345가 아니라면 WrongPasswordException을 발생시킴
		if (!password.equals("12345")) {
			throw new NotExistIDException("패스워드가 틀립니다.");
		}
	}
}