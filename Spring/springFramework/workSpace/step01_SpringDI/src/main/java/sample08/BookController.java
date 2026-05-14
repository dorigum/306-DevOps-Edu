package sample08;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("bookController")
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
//	@Qualifier("b1")
	private BookDTO bookDto;

	@Autowired
//	@Qualifier("bookDTO")
	private BookDTO bookDto2;

//@PostConstruct
//	public void aa() {
//		System.out.println("bookService = " + bookSetvice);
//		System.out.println("book1 = " + bookDto);
//		System.out.println("book2 = " + bookDto2);
//	}

	public void invoker() {
		bookService.save(bookDto, bookDto2);
	}
}
