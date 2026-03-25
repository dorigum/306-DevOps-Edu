package ex0212.superkeyword;

class Animal {
	int age = 5;
	String bodyColor;

	public void sound() {
		System.out.println("super의 sound call...");
	}

	public void eat() {
		System.out.println("super의 eat call...");
	}
}

// --------------------------------------------------------------
class Cat extends Animal { // Cat is Animal 성립
	int age = 10;
	int weight;

	@Override
	public void sound() {
		System.out.println("Cat의 sound call...");
	}

	public void run() {
		System.out.println("Cat의 run call...");
	}

	public void test() {
		System.out.println("-----------------필드 test-----------------");
		System.out.println(age);
		System.out.println(this.age);
		System.out.println(super.age);
		System.out.println("------------------------------------------");
		
		System.out.println(bodyColor);
		System.out.println(this.bodyColor);
		System.out.println(super.bodyColor);
		System.out.println("------------------------------------------");
		
		System.out.println(bodyColor);
		System.out.println(this.bodyColor);
		// System.out.println(super.bodyColor); -> 자식에게만 있는 속성은 super 사용 X
		System.out.println("------------------------------------------");
		
		System.out.println(this);
		// System.out.println(super); // super는 단독으로 사용할 수 X
		
		
		
		System.out.println("-----------------메소드-----------------");
		sound();
		this.sound();
		super.sound();
		System.out.println("------------------------------------------");
		
		eat();
		this.eat();
		super.eat();
		System.out.println("------------------------------------------");
		
		run();
		this.run();
		// super.run();
		System.out.println("------------------------------------------");
		
	}
}

public class SuperFieldMethodExam {
	public static void main(String[] args) {
		// new Cat().test();
		
		System.out.println("-----외부에서 객체를 생성해서 접근-----");
		Cat cat = new Cat();
		System.out.println(cat.age);
		System.out.println(cat.bodyColor);
		System.out.println(cat.weight);
		
		cat.sound();
		cat.eat();
		cat.run();
		
		System.out.println("==========================================");
		Animal an = new Cat(); // 다형성, Animal 부분만 접근 가능
		System.out.println(an.age);
		System.out.println(an.bodyColor);
		// System.out.println(an.weight); //부모 타입으로 자식 부분 접근 불가
		
		an.sound(); // ★★★★★재정의된 메소드는 부모 타입으로 접근해도, 재정의된 메소드가 호출된다★★★★★
		an.eat();
		// an.run(); //부모 타입으로 자식 부분 접근 불가
		
		System.out.println("an = " + an);
		
		// an으로 접근할 수 없는 자식 부분을 접근하기 위해서
		// ObjectDownCasting이 필요
		if(an instanceof Cat) {
		
		Cat c = (Cat)an; // 부모 > 자식 관계
		System.out.println("c = " + c);
		System.out.println(c.weight);
		c.run();
		}

		System.out.println("------------------------------------------");
		
		Animal a = new Animal();
		Cat cc = (Cat)a;
		
		System.out.println("-----End-----"); // 실행 도중에 ClassCastingExcption 발생
	}
}
