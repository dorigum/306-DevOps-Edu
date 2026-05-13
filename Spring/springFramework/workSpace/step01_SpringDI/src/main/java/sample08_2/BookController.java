package sample08_2;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("con") //<bean class="BookController" id="con"/>
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	@Qualifier("b1")
	private BookDTO book1;

	@Autowired
	@Qualifier("bookDTO")
	private BookDTO book2;

	@PostConstruct
	public void aa() {
		System.out.println("bookService = " + bookService);
		System.out.println("book1 = " + book1);
		System.out.println("book2 = " + book2);
	}

	public void invoker() {
		bookService.save(book1, book2);
	}
}
