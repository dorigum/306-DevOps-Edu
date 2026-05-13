package sample08_2;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository //<bean class="BookDAOImpl" id="bookDAOImpl" />
@RequiredArgsConstructor
public class BookDAOImpl implements BookDAO {

	private final DbUtil dbUtil;

	@Autowired
	private List<BookDTO> list;

	@PostConstruct
	public void test() {
		System.out.println("dbUtil = " + dbUtil);
		System.out.println("list = " + list);
	}

	@Override
	public void save(EmailSender emailSender, MessageSender messageSender, BookDTO book1, BookDTO book2) {
		System.out.println("--------------------");
		System.out.println(emailSender);
		System.out.println(messageSender);
		System.out.println(book1);
		System.out.println(book2);
		System.out.println(dbUtil);
	}
}
