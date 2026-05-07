package sample03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	// 1. 기존 방식
	//	BookDAO bookDao = new BookDAOImpl() ;
	//	BookVo bookVo = new BookVo();

	//	BookController controller = new BookController(bookDao, bookVo);
	//	controller.bookInsert();

	// 2. Spring DI 적용
	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("sample03/bookConstructor.xml");
		BookController controller = application.getBean("controller", BookController.class);

		System.out.println("----------------------------------");
//		BookController controller = new BookController();
		controller.bookInsert();
	}
}
