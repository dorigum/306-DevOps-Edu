package ex0219.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListExam03 extends ArrayList<Emp> {

	// 초기 데이터 사원 정보 5명 추가
	public ListExam03() {
		// 저장하기
		// super, this, 생략 모두 가능
		super.add(new Emp(50, "도연", 32, "경기"));
		this.add(new Emp(10, "미미", 28, "서울"));
		add(new Emp(30, "라라", 24, "제주"));
		add(new Emp(20, "영희", 35, "부산"));
		add(new Emp(40, "철수", 30, "부산"));
	}
	
	// 저장된 모든 사원 정보 리턴
	// 인터페이스 기반의 타입으로 선언할 것!!!!!!
	public List<Emp> selectAll() {
		return this;
	}
	
	// 사원 번호에 해당하는 사원 정보 리턴
	public Emp selectByEmpno(int empno) {
		for(Emp e : this) {
			if(e.getEmpno() == empno) {
				return e;
			}
		}
		return null;
	}
	
	// 주소를 인수로 전달받아 동일한 주소인 모든 사원 정보 리턴
	public List<Emp> selectByAddr(String addr) {
		// 리턴 값을 저장할 리스트 생성
		List<Emp> findList = new ArrayList<Emp>();
		
		for(Emp e : this) {
			if(e.getAddr().equals(addr)) {
				// 같은 주소가 있다면 findList에 추가
				findList.add(e);
			}
		}
		return findList;
	}
	
	// 사원 번호를 기준으로 정렬한 정보 리턴
	public List<Emp> sortByEmpno() {
		// 정렬한 대상을 관리할 리스트 생성
		List<Emp> shallowCopy = new ArrayList<Emp>(this); // Emp 리스트 주소가 copy됨
		
		// ★sort는 반드시 Comparable을 구현한 객체여야 사용 가능
		Collections.sort(shallowCopy);
		
		return shallowCopy;
	}
	
	// 나이를 기준으로 정렬한 정보 리턴
	public List<Emp> sortByAge() {
		List<Emp> shallowCopy = new ArrayList<Emp>(this);
		
//		Collections.sort(shallowCopy, new Test());
		
		// 익명 타입
//		Collections.sort(shallowCopy, new Comparator<Emp>() {
//			@Override
//			public int compare(Emp o1, Emp o2) {
//				return o1.getAge() - o2.getAge();
//			}
//		});
		
		// -------------------------------------------------
		// 람다식 변경
		Collections.sort(shallowCopy, (e1, e2) -> e1.getAge() - e2.getAge());
		return shallowCopy;
	}
	
	public static void main(String[] args) {
		ListExam03 e = new ListExam03();
		
		System.out.println("1. 전체 검색");
		
		List<Emp> list = e.selectAll();
		
		for(Emp emp : list) {
			System.out.println(emp); // emp.toString();
		}
		
		// -------------------------------------------------
		System.out.println("---------------------------------------------------");
		System.out.println("2. 사원 번호 검색");
		
		Emp emp = e.selectByEmpno(50);
		System.out.println(emp);
		
		// -------------------------------------------------
		System.out.println("---------------------------------------------------");
		System.out.println("3. 주소로 검색");
		list = e.selectByAddr("부산");
		
		if(list.size() == 0) {
			System.out.println("주소에 해당하는 사원이 없습니다.");
		
		} else {
			System.out.println(list);
		}
		
		// -------------------------------------------------
		System.out.println("4. 사원 번호 정렬 검색");
		list = e.sortByEmpno();
		
		for(Emp em : list) {
			System.out.println(em);
		}
		
		System.out.println("1. 전체 검색");
		list = e.selectAll();
		for(Emp emp2 : list) {
			System.out.println(emp2); // emp.toString();
		}
		
		// -------------------------------------------------
		System.out.println("5. 나이 정렬 검색");
		list = e.sortByAge();
		for(Emp em : list) {
			System.out.println(em);
		}
	}
}

// -------------------------------------------------
class Test implements Comparator<Emp> {
	@Override
	public int compare(Emp e1, Emp e2) {
//		return e1.getAge() - e2.getAge(); // 음수 or 0 or 양수
		return e2.getAge() - e1.getAge();
	}
}