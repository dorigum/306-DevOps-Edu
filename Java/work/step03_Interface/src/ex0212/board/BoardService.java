package ex0212.board;

import java.util.List;

// 모든 게시판 유형이 공통으로 사용할 메소드 정의(규격서 역할)
public interface BoardService {
	/*
	 * 등록 
	 * @param: Board
	 * @return: int(0이면 실패, 1이면 성공)
	 */
	int insert(Board board);// QA or Upload or Free...(다형성)
	
	/*
	 * 수정
	 * @param: Board
	 * @return: boolean
	 */
	boolean update(Board board);
	
	/*
	 * 글번호에 해당하는 게시물 검색
	 * @param: int
	 * @return: Board
	 */
	Board selectByNo(int no);
	
	// ------------------------------------------------------------------
	/*
	 * java 1.8 version 이후 추가
	 * 
	 * : interface 안에 있는 메소드에 static or default 제한자를 추가하면 body가 있는 메소드를 만들 수 있다.
	 * body가 있는 메소드는 구현 객체들이 재정의를 선택적으로 할 수 있다.
	 * 
	 * 1) static : 구현 객체없이 바로 interface이름.메소드이름() 호출
	 * 2) default : 반드시 구현 객체가 있어야 호출할 수 있다 .
	 */
	
	// 삭제 기능
	default int delete(int no) {
		System.out.println("삭제 기능입니다~~~");

		return 3;
	}
	
	// 전체 검색
	static List<Board> selectAll() {
		System.out.println("전체 검색 기능~~~");
		return null;
	}
}