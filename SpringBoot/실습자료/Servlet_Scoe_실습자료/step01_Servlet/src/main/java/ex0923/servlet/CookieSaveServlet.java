package ex0923.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CookieSaveServlet
 */
@WebServlet(urlPatterns = "/cookie/cookieSave", loadOnStartup = 1)
public class CookieSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CookieSaveServlet doGet call..");
		
		//쿠키를 생성해서 저장
		Cookie co1 = new Cookie("id", "jang");
		Cookie co2 = new Cookie("age", "20");
		
		//쿠키에 대한 옵션 설정
		co1.setMaxAge(60*60*24);//1일
		co2.setMaxAge(60*60*24*365);// 365일
		
		co1.setPath("/"); //co1는 /이하의 모든 영역에서 사용할수 있다.
		
		//클라이언트쪽으로 보낸다.
		response.addCookie(co1);
		response.addCookie(co2);
		
		//이동
		response.sendRedirect("cookieGet.jsp");
		
	}

}








