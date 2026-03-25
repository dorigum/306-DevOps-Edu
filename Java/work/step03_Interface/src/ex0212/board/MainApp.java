package ex0212.board;

public class MainApp {
	// 필드를 이용한 다형성
	BoardService service; // UploadServiceImpl or FreeServiceImpl or QaBoardServiceImpl
	Board board; // FreeBoard or QaBoard or UploadBoard

	// -----------------------------------------------------------------------
	public MainApp() {
		service = new FreeBoardServiceImpl();
		board = new FreeBoard(10, "제목", "작성자", "내용");
		test(service, board);
		
		service = new QaBoardServiceImpl();
		board = new QaBoard(200, "QA 제목", "QA 작성자", "QA 내용", false);
		test(service, board);
		
		service = new UploadBoardServiceImpl();
		board = new UploadBoard(300, "UP 제목", "UP 작성자", "UP 내용", "b.txt");
		test(service, board);
	}

	// -----------------------------------------------------------------------
	// 매개 변수를 이용한 다형성
	public void test(BoardService service, Board board) {
		service.insert(board);
		service.update(board);
		service.selectByNo(5);
		
		System.out.println("-------------------------------------");
	}

	// -----------------------------------------------------------------------
	public static void main(String[] args) {
		new MainApp();
	}
}
