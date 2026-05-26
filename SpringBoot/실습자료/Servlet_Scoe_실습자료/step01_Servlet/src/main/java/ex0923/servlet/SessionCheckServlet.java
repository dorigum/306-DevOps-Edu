package ex0923.servlet;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/check", loadOnStartup = 1)
public class SessionCheckServlet extends HttpServlet {
	public SessionCheckServlet() {
		System.out.println("SessionCheckServlet constructor call...");
		
		
	}
	
   @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service 호출됨.....");
		
		//세션(HttpSession)객체를 가져와서 session관련된 메소드 사용해보자 
		HttpSession session = request.getSession();
		
		//세션의 유효시간을 30초
		session.setMaxInactiveInterval(30);
		
		System.out.println("session.getId() = "+session.getId());
		System.out.println("session.getMaxInactiveInterval() = "+session.getMaxInactiveInterval());
		System.out.println("session.getLastAccessedTime() = "+session.getLastAccessedTime());
		System.out.println("session.getCreationTime() = "+session.getCreationTime());
		System.out.println("session.isNew() = "+session.isNew());
		
		//세션을 특징을 이용해서 로그인 로그아웃을 만들수 있다!!
		//개발자가 직접 원하는 정보를 session scope에 저장하면 브라우져가 유지되는동안 정보를 유지시킬수 있다.
		session.setAttribute("id", "jang");
		session.setAttribute("hobbies", Arrays.asList("등산","수영","낚시","골프"));
		
		//저장된 정보 출력해보자
		System.out.println("아이디 = " + session.getAttribute("id"));
		System.out.println("취미 = " + session.getAttribute("hobbies"));
		
		//저장된 정보를 페이지가 이동해도 사용할수 있다!!!
		response.sendRedirect("session-result.jsp");
		
		
	}
}








