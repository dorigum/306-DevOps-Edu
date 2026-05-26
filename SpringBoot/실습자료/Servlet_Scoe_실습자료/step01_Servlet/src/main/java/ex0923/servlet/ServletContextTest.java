package ex0923.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/context",loadOnStartup = 1 )
public class ServletContextTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletContextTest() {
       System.out.println("ServletContextTest 생성자..");
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletContextTest service 입니다...");
		
		//ServletConext의 관련 메소드 사용해보자.
		ServletContext application = request.getServletContext();
		
		System.out.println("application.getContextPath() = "+application.getContextPath());
		System.out.println("application.getRealPath('/') = "+application.getRealPath("/"));
		
		System.out.println("application.getMajorVersion() = "+application.getMajorVersion());
		System.out.println("application.getMinorVersion() = "+application.getMinorVersion());
		
	    //정보의 영속성!!!
		application.setAttribute("message", "졸지말아요~~");
		application.setAttribute("id", "kim");
		
		//저장된 정보 조회!!!
		System.out.println("메시지 = "+application.getAttribute("message"));
		
		
		//request Scope 에 데이터 저장하기
		request.setAttribute("info", "requestScope에 저장된 정보!!");
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h3>");
		out.print("메시지 = " + application.getAttribute("message")+"<br>");
		out.print("아이디  = " + application.getAttribute("id")+"<br>");
		out.print("<a href='applicationGet.jsp'>정보확인하러가자</a>");
		out.print("</h3>");
		
		//response.sendRedirect("applicationGet.jsp");
		request
		.getRequestDispatcher("applicationGet.jsp")
		.forward(request, response);
		
		
	}

	

}









