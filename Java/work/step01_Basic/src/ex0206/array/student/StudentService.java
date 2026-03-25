package ex0206.array.student;

// 학생의 정보를 관리하는 서비스(등록, 수정, 검색,....)
public class StudentService {
	//학생의 정보를 저장할 배열 선언
	Student stArr[] = new Student [10]; // 최대 10명
	public static int count;// 배열에 저장된 객체의 개수를 체크하는 필드
	
	 // 초기치 데이터 3명 정도 세팅하기
	 public void init(String[][] data) {
		 for(int i=0; i<data.length ; i++) {
		  stArr[count++] = this.create(data[i]);
		 }
		 
		 System.out.println("***초기화 완료***");
		 /*for(int i=0; i<count;i++) {
			 System.out.println(stArr[i]);
		 }*/
	 }

	 // Student 객체를 생성해서 리턴해주는 메소드 작성
	 private Student create(String[] row) { // {"도연","20","서울"}
		 Student st = new Student();
		 st.setName(row[0]);
		 st.setAge(Integer.parseInt(row[1]));
		 st.setAddr(row[2]);
		 
		 return st;
	 }

	/*
	 학생의 정보 등록하기 
	 : 배열의 경계를 벗어나면 더이상 추가할 수 없습니다. 메시지 출력.
	 이름이 중복되면 등록 X
	 추가가 성공하면 "등록되었습니다" 메시지를 출력 -> View 에서 출력
	*/
	 
	 // 3가지 경우의 수 가능
	 // 1. 성공 2. 중복 값 존재 3. 배열의 경계가 벗어날 때
	 // @return: 1이면 성공, 0이면 중복, -1이면 배열의 경계 벗어남
	 public int insert(Student student) {
		 // 배열 크기 체크
		 if(stArr.length == count) return -1;
		 
		 // 중복 체크
		 if(this.selectByName(student.getName()) != null) return 0;
		 
		 stArr[count++] = student;
		 return 1;
	 }
	
	/*
	 * 전체 학생의 정보 조회하기
	 * 리턴 타입: Student[]
	 */
	 public Student[] selectAll() {
		 return stArr;
	 }
	
	/*
	 * 이름에 해당하는 학생의 정보 검색하기
	 *  : 이름에 해당하는 학생이 있으면 학생의 이름, 나이, 주소를 출력하고
	 *  없으면 "찾는 정보가 없습니다." 출력
	 */
	 // @return: 찾은 학생의 정보(이름, 나이, 주소) Student 리턴 / 없으면 null 리턴
	 public Student selectByName(String name) {
		 for(int i=0; i<count; i++) {
			 Student searchedSt = stArr[i];
			 
			 if(searchedSt.getName().equals(name)) {
				 return searchedSt;
			 }
		 }
		 // 못 찾았으면
		 return null;
	 }
	
	
	/*
	 * 이름에 해당하는 학생의 나이와 주소 변경하기 
	 *  : 이름에 해당하는 학생이 있는지 찾아서 없으면 "수정할 수 없습니다." 출력
	 *   있으면  setAge() , setAddr() 이용해서 전달된 인수의 값으로 변경하고
	 *   "수정되었습니다" 출력
	 *   @param : Student
	 *   @return : boolean
	 */
	 public boolean update(Student student) {
		 for(int i=0; i<count; i++) {
			 if(stArr[i].getName().equals(student.getName())) {
				 stArr[i].setAge(student.getAge());
				 stArr[i].setAddr(student.getAddr());
				 
				 return true; // 수정 완료!
			 }
		 }
		 return false; // 수정 실패(찾지 못함)
	 }
	 
	 /*
	 * 이름으로 학생 정보 삭제하기
	 * 1. 삭제할 학생의 위치(인덱스)를 찾는다.
	 * 2. 찾았으면, 그 뒤에 있는 학생들을 한 칸씩 앞으로 당긴다.
	 * 3. 마지막 칸을 비우고(null), 전체 개수(count)를 1 줄인다.
	 * * @return : 삭제 성공 시 true, 실패 시(이름 없음) false
	 */
	 public boolean delete(String name) {
		 int index = -1; // 찾은 위치를 저장할 변수
		 
		 // 1. 삭제할 이름이 몇 번째 방에 있는지 찾기
		 for(int i=0; i<count; i++) {
			 if(stArr[i].getName().equals(name)) {
				 index = i;
				 break; // 찾았으면 반복 종료
			 }
		 }
		 // 못 찾았으면 false 리턴
		 if(index == -1) return false;
		 
		 // 2. 삭제할 위치부터 뒤에 있는 데이터를 앞으로 당기기 (Shift)
		 for(int i=index; i<count-1; i++) {
			 stArr[i] = stArr[i+1];
		 }
		 
		 // 3. 마지막 데이터 정리 (개수 줄이고 null 처리)
		 count--;
		 stArr[count] = null;
		 
		 return true; // 삭제 성공
	 }
}