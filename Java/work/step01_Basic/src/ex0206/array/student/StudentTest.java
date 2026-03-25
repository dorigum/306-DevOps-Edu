package ex0206.array.student;

public class StudentTest {
	public static void main(String[] args) {
		System.out.println("***학생 관리 프로그램 시작합니다.***");
		
	    String[][] data = new String[][] {
	    	{"도연","20","서울"},
	    	{"나영","25","대구"},
	    	{"미미","30","부산"},
	    	{"효리","32","서울"},
	    	{"재석","27","제주도"},
	    };
		
		int a = 100;
		StudentService service = new StudentService(); // StudentService의 전역 변수들이 초기화 
		service.init(data); // 초기 데이터 세팅
		
		// 전체 학생 정보 출력
		Student[] stArr = service.selectAll();
		StudentEndView.printSelectAll(stArr);
		
		// 학생 정보 등록하기
		System.out.println("---1. 등록하기---");
		Student student = new Student();
		student.setName("미미");
		student.setAge(25);
		student.setAddr("오리역");
		
		int result = service.insert(student);
		
		if(result == 0) // 0: 데이터가 중복일 때
			StudentEndView.printMessage(student.getName() + " 중복된 이름입니다. 등록 실패");
		else if(result == -1) // -1: 배열 범위 초과
			StudentEndView.printMessage("인원을 초과하여 등록할 수 없습니다.");
		else // 1: 등록 성공
			StudentEndView.printMessage("등록되었습니다.");
		
		System.out.println("---2. 전체검색---");
		StudentEndView.printSelectAll(service.selectAll());
		
		// 학생 이름으로 검색하기
		System.out.println("---3. 이름으로 검색---");
		Student st2 = service.selectByName("도연");
		
		// 결과가 있는지/없는지 확인
		if(st2 == null) { // 없는 이름이라면
			StudentEndView.printMessage("찾는 정보가 없습니다.");
		} else { // 있는 이름이라면
			StudentEndView.printSelectByName(st2);
		}
		
		System.out.println("---이름이 없는 경우---");
		Student st3 = service.selectByName("삼순이");
		
		if(st3 == null) {
			StudentEndView.printMessage("찾는 정보가 없습니다.");
		} else { // 있는 이름이라면
			StudentEndView.printSelectByName(st3);
		}
		
		// 학생 정보 수정하기 
		System.out.println("---4. 수정하기---");
		Student stUpdate = new Student();
		stUpdate.setName("나영"); // 조건
		stUpdate.setAge(23);
		stUpdate.setAddr("대전");
		
		// update 메소드 호출하고 결과 받기
		if(service.update(stUpdate)) {
			StudentEndView.printMessage(stUpdate.getName() + "님의 정보가 수정되었습니다.");
		} else {
			StudentEndView.printMessage("이름이 존재하지 않아 수정할 수 없습니다.");
		}
		
		// 학생 정보 삭제하기
		System.out.println("---5. 삭제하기(미미)---");
		
		// "미미"를 삭제해본다.
		if(service.delete("미미")) {
			StudentEndView.printMessage("삭제되었습니다.");
		} else {
			StudentEndView.printMessage("삭제 실패: 해당하는 이름이 없습니다.");
		}
		
		System.out.println("---삭제 후 전체 확인---");
		StudentEndView.printSelectAll(service.selectAll());
		
		// 없는 이름 삭제해보기 테스트
		System.out.println("---삭제하기(없는 이름: 짱구)---");
		if(service.delete("짱구")) {
			StudentEndView.printMessage("삭제되었습니다.");
		} else {
			StudentEndView.printMessage("삭제 실패: 해당하는 이름이 없습니다.");
		}
		
		System.out.println("---변경 후 확인---");
		stArr = service.selectAll();
		StudentEndView.printSelectAll(service.selectAll());
	}
}