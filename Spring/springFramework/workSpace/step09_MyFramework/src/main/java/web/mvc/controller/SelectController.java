package web.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SelectController 실행입니다.");

		request.setAttribute("message", "select의 결과입니다."); // forward 방식으로 가야 한다.

		ModelAndView mv = new ModelAndView();

		mv.setViewName("selectResult.jsp"); // redirect를 변경하지 않았기 때문에 forward 방식으로 이동

		return mv;
	}
}