package ex0210_구도연;

public class MemberService {
	public boolean login(String id, String password) {
		// 권장하는 코드 작성
		// NullPoint일 가능성 X
		if("hong".equals(id) && "12345".equals(password)) {
			return true;
		} else {
			return false;
		}
	
		// NullPoint일 가능성 O
//		if(id.equals("hong") && password.equals("12345")) {
//			return true;
//		} else {
//			return false;
//		}
	}
}
