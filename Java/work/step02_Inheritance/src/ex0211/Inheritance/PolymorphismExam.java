package ex0211.Inheritance;

class CarCenter {
	// 매개변수를 이용한 다형성
	// -> 접근 범위 제약, Car 타입 부분만 접근 가능
	public void engineer(Car cd) { // cd = ca;
		System.out.println("cd 주소 = " + cd);
//		System.out.println("cd.i = " + cd.i); // 부모 타입으로는 자식 부분 접근 불가

		// 접근이 가능하도록 부모 타입을 자식 타입으로 형 변환!!
//		EfSonata efs = cd; // 부모 > 자식

		// ObjectDownCasting을 한다.
		if (cd instanceof EfSonata) {
			System.out.println("-----------------------");
			EfSonata efs = (EfSonata) cd; // ClassCastException 예외 가능성
			System.out.println("efs 주소 = " + efs);
			System.out.println("efs.i = " + efs.i);
			System.out.println("-----------------------");
		}

		System.out.print(cd.carname + " 수리완료!\t");
		System.out.println("청구비용 " + cd.cost + "원");
	}
}

// -----------------------------------------------------------
public class PolymorphismExam {
	public static void main(String[] args) {
		CarCenter cc = new CarCenter();

		EfSonata ef = new EfSonata();
		Carnival ca = new Carnival();
		Excel ex = new Excel();
		Car c = new Car();

		cc.engineer(c); // c의 주소값이 전달

		System.out.println("ef 주소 = " + ef);
		System.out.println("ef.i = " + ef.i);

		cc.engineer(ef);
		cc.engineer(ca);
		cc.engineer(ex);

	}
}