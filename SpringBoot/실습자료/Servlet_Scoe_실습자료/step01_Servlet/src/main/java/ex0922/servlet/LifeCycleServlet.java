package ex0922.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet 작성법
 * 1) HttpServlet 상속받는다.
 * 2) public class이다. 
 * 3) 요청을 처리할 메소드 재정의한다.
 * 
 * 4) 브라우져에서 어떻게 요청하면 서블릿을 실행할지를 결정하는 등록절차필수
 *       - WEB-INF/web.xml설정
 *       - @annotation설정
 * 
 * */
public class LifeCycleServlet extends HttpServlet {
	
	public LifeCycleServlet() {
		System.out.println("LifeCycleServlet 생성자 호출됨...");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("LifeCycleServlet init call....");
	}
	
//	@Override
//	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
//		System.out.println("LifeCycleServlet service call....");
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet doGet  call.....");//서버 콘솔출력
		
		//html코딩
		//브라우져에 출력
		PrintWriter out = response.getWriter();
		
		String name="장희정";
		
		//브라우져로 응답되어지는 한글 인코딩설정
		response.setContentType("text/html; charset=UTF-8");
		out.print("<!doctype html>");
		out.println("<html><head><title>Servlet시작</title></head>");
		out.println("<body>");
		out.print("<h1 style='color:red'>Servlet시작합니다~~ -"+name+"</h1>");
		
		out.print("<script>");
		out.print("alert('안녕하세요');");
		out.print("alert('반가워요')");
		out.print("</script>");
		
		out.print("</body>");
		out.print("</html>");
		
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	   System.out.println("LifeCycleServlet doPost call...");	
//	}




	@Override
	public void destroy() {
		 System.out.println("LifeCycleServlet destroy call....");
	}
   
}
