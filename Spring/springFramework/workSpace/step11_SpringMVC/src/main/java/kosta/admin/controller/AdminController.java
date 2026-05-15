package kosta.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final UserService service;
	
	@RequestMapping("/test.admin")
	public String test() {
		return "admin-test"; // WEB-INF/admin/admin.test.jsp ¿Ãµø 
	}
}
