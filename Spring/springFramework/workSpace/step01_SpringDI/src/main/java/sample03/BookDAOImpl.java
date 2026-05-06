package sample03;

public class BookDAOImpl implements BookDAO {

	public BookDAOImpl() {
		System.out.println("BookDAOImpl constructor call");
	}

	@Override
	public void insert(BookVo bookvo) {
		System.out.println("BookDAOImpl insert call");
		System.out.println("book = " + bookvo);
	}
}
