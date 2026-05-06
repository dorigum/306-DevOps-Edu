package sample03;

public class BookController {
	private BookDAO bookDao;
	private BookVo bookVo;
	
	public BookController() {
		System.out.println("BookController");
	}
	
	public BookController(BookDAO bookDao, BookVo bookvo) {
		this.bookDao = bookDao;
		this.bookVo = bookvo;
	}

	public void bookInsert() {
		System.out.println("BookController bookInsert call");
		bookDao.insert(bookVo);
	}
}
