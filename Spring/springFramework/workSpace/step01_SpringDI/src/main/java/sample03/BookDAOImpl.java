package sample03;

public class BookDAOImpl implements BookDAO {

	public BookDAOImpl() {
		System.out.println("BookDAOImpl insert call");
	}

	@Override
	public void insert(BookVo bookvo) {
		System.out.println("Book subject : " + bookvo);
	}
}
