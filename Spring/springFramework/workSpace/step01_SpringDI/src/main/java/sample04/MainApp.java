package sample04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// 1. 기존 방식
		/* Student student = new Student();
		student.setNo(1);
		student.setAddr("서울");
		student.setName("도연");
		student.setPhone("1111-1111");

		StudentService service = new StudentServiceImpl();

		StudentDAO dao = new StudentDAOImpl();
		StudentServiceImpl serviceImpl = (StudentServiceImpl) service;
		serviceImpl.setStudentDAO(dao);

		StudentController controller = new StudentController();

		// 주입
		controller.setStudent(student);
		controller.setStudentService(service);

		controller.insert(); */
		
		// --------------------------------------------------------
		ApplicationContext application = new ClassPathXmlApplicationContext("sample04/springDI_Setter.xml");
	
		System.out.println("---호출---");
		StudentController controller = application.getBean("controller", StudentController.class);
		
		controller.insert();
	}
}
