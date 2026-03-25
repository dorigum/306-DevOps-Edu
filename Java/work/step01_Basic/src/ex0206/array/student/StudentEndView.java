package ex0206.array.student;

// 사용자 요청의 결과를 모니터에 출력하는 클래스
public class StudentEndView {
	// 전체 검색 결과 출력
	public static void printSelectAll(Student[] stArr) {
		// StudentService.count: static 타입
		// -> 추가 생성 없이 어디서든지 데이터를 사용할 수 있도록 설정 
		System.out.println("***학생 정보 " + "(" + StudentService.count + ")명***");
		
		for(int i=0; i<StudentService.count; i++) {
			System.out.printf("이름 : %s | ", stArr[i].getName());
			System.out.printf("나이 : %d | ", stArr[i].getAge());
			System.out.printf("주소 : %s%n", stArr[i].getAddr());
		}
	}
	// 이름에 해당하는 학생 정보 출력
	public static void printSelectByName(Student student) {
		System.out.printf("이름 : %s | ", student.getName());
		System.out.printf("나이 : %d | ", student.getAge());
		System.out.printf("주소 : %s%n", student.getAddr());
	}
	// 요청 결과의 성공 여부 메시지 출력
	public static void printMessage(String message) {
		System.out.println(message);
	}
}