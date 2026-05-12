package sample08;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("service") //<bean class="BookServiceImpl" id="service"/> 
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookDAO bookDao;
	private final EmailSender emailSender;
	private final MessageSender messageSender;
	
	@PostConstruct
	public void test() {
		System.out.println("bookDao = " + bookDao);
		System.out.println("emailSender = " + emailSender);
		System.out.println("messageSender = " + messageSender);
	}
	
	
	@Override
	public void save(BookDTO book1, BookDTO book2) {
		bookDao.save(emailSender, messageSender, book1, book2);

	}

}
