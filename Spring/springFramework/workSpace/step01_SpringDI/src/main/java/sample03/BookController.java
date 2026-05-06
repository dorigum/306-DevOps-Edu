package sample03;

public class BookController {
	private BookDAOImpl bookDaoImpl;
	private BookVo bookVo;
	
	public BookController() {
		System.out.println("BookController");
	}
	
	public BookController(BookDAOImpl bookDaoImpl, BookVo bookvo) {
		this.bookDaoImpl = bookDaoImpl;
		this.bookVo = bookvo;
	}
	public void bookInsert() {
		System.out.println("BookController bookInsert call");
		bookDaoImpl.insert(bookVo);
	}
}
