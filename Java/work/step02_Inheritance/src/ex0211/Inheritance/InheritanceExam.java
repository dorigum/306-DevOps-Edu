package ex0211.Inheritance;

class Car { // Object 상속
	public String carname;
	public int cost;

	protected void printAttributes() {
		System.out.println("carname=" + carname + "\tcost=" + cost);
	}
}

// ---------------------------------------------------------
// Car를 상속받는 EfSonata, Excel, Carnival 3개 클래스 작성
// 각 클래스에 인수를 받지않는 생성자 작성
// 각 클래스의 생성자의 구현부에서 carname과 cost에 적당한 값 할당
class EfSonata extends Car {
	int i = 100;

	EfSonata() { // 생성자
		carname = "EfSonata";
		this.cost = 3500;
	}
}

class Excel extends Car {
	Excel() {
		this.carname = "Excel";
		super.cost = 4000;
	}
}

class Carnival extends Car {
	Carnival() {
		super.carname = "Carnival";
		this.cost = 5000;
	}
}

// 하위 객체가 상속을 받을 때 같은 부모 클래스를 '공유'받는 것이 아님!!!
public class InheritanceExam {
	// 메인 메소드에서
	// Car, EfSonata, Excel, Carnival 네개의 객체를 생성
	// 각 클래스에서 Car class에 있는 printAttributes()메소드를 호출할 수 있다.
	public static void main(String[] args) {
		Car car = new Car();
		EfSonata ef = new EfSonata();
		Excel ex = new Excel();
		Carnival ca = new Carnival();

		System.out.println("---------------------------------");
		System.out.println("car = " + car);
		System.out.println("ef = " + ef);
		System.out.println("ex = " + ex);
		System.out.println("cn = " + ca);

		System.out.println("---------------------------------");
		car.printAttributes();
		ef.printAttributes();
		ex.printAttributes();
		ca.printAttributes();
	}
}