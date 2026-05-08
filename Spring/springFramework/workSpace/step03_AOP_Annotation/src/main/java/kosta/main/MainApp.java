package kosta.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kosta.service.GoodsService;
import kosta.service.MemberService;
import kosta.service.MemberServiceImpl;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springAop.xml");

		System.out.println("-----------------------------");
		MemberService service = context.getBean("target", MemberService.class);

		service.memberInsert("안녕");

		System.out.println("====================");
		int re = service.memberDelete(10);
		System.out.println("re = " + re);

		System.out.println("====================");
		service.memberUpdate();

		System.out.println("==================");
		String result = service.select("name", "도연");
		System.out.println("result = " + result);

		System.out.println("---------GoodsService----");
		GoodsService target2 = context.getBean("target2", GoodsService.class);
		target2.insert();
		System.out.println("----------------");
		target2.goodsSelect(10);
	}
}
