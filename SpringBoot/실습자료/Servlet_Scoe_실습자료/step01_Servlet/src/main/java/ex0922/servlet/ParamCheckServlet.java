package ex0922.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * ParamCheckServlet을 자바base로 생성과 매핑
 * */
@WebServlet(urlPatterns = "/param" , loadOnStartup = 1)
public class ParamCheckServlet extends HttpServlet {
	String dbId="jang";
	String dbPwd="1234";
	
   public ParamCheckServlet() {
	   System.out.println("ParamCheckServlet 생성자 호출..");
   }
   
   
   @Override
	protected void service(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
		//request로 전송되어진 정보를 가져오기
	    String userId = request.getParameter("userId");
	    String userPwd = request.getParameter("userPwd");
	    String userName = request.getParameter("userName");
	    
	    System.out.println("userId = " + userId);
	    System.out.println("userPwd = " + userPwd);
	    System.out.println("userName = " + userName);
	    
	    //db의 정보와 비교해서 일치하면 성공페이지로, 일치하지않으며
	    //에러메시지출력하고 폼으로 이동
	    
	    if(dbId.equals(userId) && dbPwd.equals(userPwd)) {
	    	//성공페이지 이동 - redirect방식(새로운 request, response생성되어 이동)
	    	//userName = URLEncoder.encode(userName, "UTF-8");
	    	//response.sendRedirect("login_ok.jsp?userName="+userName);
	    	
	    	// 성공페이지 이동 - forward방식(기존 request, response유지하면서 이동)
	    	request.getRequestDispatcher("login_ok.jsp").forward(request, response);
	    	
	    }else {
	    	response.setContentType("text/html;charset=UTF-8");
	    	//에러메시지출력
	    	//브라우져 출력 
	    	PrintWriter out = response.getWriter();
	    	out.println("<script>");
	    	out.println("alert('"+userName+"님 정보를 다시 확인해주세요.')");
	    	//out.println("location.href='LoginForm.html';");
	    	out.println("history.back();"); //뒤로가기 
	    	out.println("</script>");
	    	
	    	//폼으로 이동
	    	//response.sendRedirect("LoginForm.html");
	    }
	   
	}
	
}










