package mvc.ajax.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mvc.ajax.dto.TodoListDTO;
import mvc.ajax.service.TodoListService;
import mvc.ajax.service.TodoListServiceImpl;


public class TodoListController implements RestController {
   private TodoListService todoListService = new TodoListServiceImpl();
   
   
   /**
	 * 전체검색
	 * */
	public Object selectAll(HttpServletRequest request, HttpServletResponse response){
		System.out.println("오니?");
        List<TodoListDTO> list = todoListService.selectAll();
	    System.out.println("list = " + list);
		return list;
	}
	
	/**
	 * todo 추가
	 * */
	public Object insert(HttpServletRequest request, HttpServletResponse response) {
		
		String content = request.getParameter("content");
		
		TodoListDTO dto = new TodoListDTO(0, false, content, null);
		int result = todoListService.insert(dto);
		
		return result;
	}
	 
	/**
	 * todo 상태 수정
	 * */
	public Object update(HttpServletRequest request, HttpServletResponse response) {
        String done = request.getParameter("done");
        String id = request.getParameter("id");
        
        System.out.println("done = " + done);
        System.out.println("id = " + id);
		
		TodoListDTO dto = new TodoListDTO(Integer.parseInt(id) , done.equals("1") , null, null);
		
		int result = todoListService.update(dto);
		
		return result;
	}
	
	/**
	 * todo 삭제
	 * */
	public Object delete(HttpServletRequest request, HttpServletResponse response) {
       String id = request.getParameter("targetId");
        	
		int result = todoListService.delete( Integer.parseInt(id) );
		
		return result;
	}
	
	/**
	 * 키워드 검색
	 * */
	public Object selectIncludesbyWord(HttpServletRequest request, HttpServletResponse response){
		System.out.println("키워드 검색?");
		 String word = request.getParameter("word");
        List<TodoListDTO> list = todoListService.selectIncludesbyWord(word);
	    System.out.println("list = " + list);
		return list;
	}
  
	
}











