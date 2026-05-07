package sample08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("bookController")
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private BookDTO bookDto;

	@Autowired
	private BookDTO bookDto2;

//	public BookController() {
//		System.out.println("BookController 생성자");
//	}

	public void invoker() {
		bookService.save(bookDto, bookDto2);
	}
}
