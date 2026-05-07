package sample08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bookDaoImpl")
public class BookDAOImpl implements BookDAO {
	@Autowired
	private DBUtil dbUtil;

//	public BookDAOImpl() {
//		System.out.println("BookDAOImpl 생성자 호출!!!");
//	}

	public void save(EmailSender emailSender, MessageSender messageSender, BookDTO bookDto, BookDTO bookDto2) {
		System.out.println("dbUtil = " + dbUtil);
	}
}
