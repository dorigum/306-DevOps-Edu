package ex0212.board;

/*
 * QA 게시판 비즈니스 로직을 담당하는 클래스
 */
public class QaBoardServiceImpl implements BoardService {

	@Override
	public int insert(Board board) {
		System.out.println("qa = " + board);
		System.out.println("QaBoardServiceImpl의 insert call");
		
		return 1;
	}

	@Override
	public boolean update(Board board) {
		System.out.println("qa = " + board);
		System.out.println("QaBoardServiceImpl의 update call");
		
		return true;
	}

	@Override
	public Board selectByNo(int no) {
		System.out.println("QaBoardServiceImpl의 selectByNo call");
		
		return new QaBoard(10, "연습중", "도연", "재미있다.", false);
	}
}