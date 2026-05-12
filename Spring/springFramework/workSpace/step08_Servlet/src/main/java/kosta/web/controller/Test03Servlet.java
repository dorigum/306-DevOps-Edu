package kosta.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 이 클래스가 웹 브라우저에서 동작(실행)할 수 있게 하고 싶다!
 * => 자바 중심의 코드에서 마크업을 섞어서 사용할 수 있다.
 * 
 * 조건 : 
 *    1) 반드시 public 클래스로 선언
 *    2) HttpServlet을 상속받는다.
 *    3) 필요한 메소드(servlet의 lifecycle에 관련된 메소드)를 
 *       재정의해서 기능을 작성한다. 
 *    4) 브라우저에서 어떻게 요청하면 현재 서블릿이 실행될 지에 해당하는 설정
 *    (web.xml or @annotation)이 필요하다!!
 */

public class Test03Servlet extends HttpServlet {
	public Test03Servlet() {
		System.out.println("Test03Servlet 생성자 호출됨!!");
	}

	/*
	 * 사용자 요청이 될 때마다 호출되는 메소드
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=EUC-KR");

		System.out.println("요청이 들어왔어요~~~");

		// 요청이 들어온 param 정보를 받기
		String id = request.getParameter("id");
		System.out.println("전송된 id = " + id);

		// service 호출 -> dao 호출-> 그 결과를 받아서 처리
		if (id == null || !id.equals("jang")) {
			// 실패 페이지 이동

			// 이동 전에 뷰 쪽으로 전달될 데이터를 설정
			request.setAttribute("message", "id가 없거나 잘못된 정보입니다."); // 뷰에서 ${requestScope.message}

			// 1) redirect방식
			// response.sendRedirect("fail.jsp");

			// 2) forward방식
			request.getRequestDispatcher("fail.jsp").forward(request, response);

		} else {
			// 성공 페이지 이동
			// 이동 전에 뷰 쪽으로 전달될 데이터를 설정
			request.setAttribute("hobbys", Arrays.asList("등산", "수영", "낚시")); // 뷰에서 ${requestScope.hobbys}

			// 1) redirect방식
			// response.sendRedirect("ok.jsp");

			// 2) forward방식
			request.getRequestDispatcher("ok.jsp").forward(request, response);
		}

		/*
		 * String name = "heejung";
		 *
		 * // 브라우저에 출력 PrintWriter out = response.getWriter();
		 * out.println("<h1>Servlet 잘 되니??</h1>");
		 * out.println("<h2 style = 'color:red'> 배고프다. </h2>");
		 * out.println("이름은 = " + name);
		 */
	}
}