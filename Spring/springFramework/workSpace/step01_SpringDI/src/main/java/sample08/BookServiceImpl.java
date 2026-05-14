package sample08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("service")
public class BookServiceImpl implements BookService {
	@Autowired
	private EmailSender emailSender;

	@Autowired
	private MessageSender messageSender;

	@Autowired
	@Qualifier("bookDaoImpl")
	private BookDAO bookDao;

//	public BookServiceImpl() {
//		System.out.println("BookServiceImpl 생성자");
//	}

	public void save(BookDTO bookDto, BookDTO bookDto2) {
		System.out.println("emailSender = " + emailSender);
		System.out.println("messageSender = " + messageSender);
		System.out.println("book1 = " + bookDto);
		System.out.println("book2 = " + bookDto2);
		
		bookDao.save(emailSender, messageSender, bookDto, bookDto2);
	}
}
