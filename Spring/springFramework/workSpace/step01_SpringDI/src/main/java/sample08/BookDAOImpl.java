package sample08;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bookDaoImpl")
public class BookDAOImpl implements BookDAO {
	@Autowired
	private DBUtil dbUtil;
	// @Autowired를 사용하거나, 혹은
	// private final DBUtil dbUtil; 로 주입

	@Autowired // byType을 찾아서 주입
	private List<BookDTO> list; // BookDTO 정보를 DB처럼 사용하기 위한 collection

	@PostConstruct
	public void test() {
		System.out.println("list = " + list);
	}

	public void save(EmailSender emailSender, MessageSender messageSender, BookDTO bookDto, BookDTO bookDto2) {
		System.out.println("dbUtil = " + dbUtil);
	}
}
