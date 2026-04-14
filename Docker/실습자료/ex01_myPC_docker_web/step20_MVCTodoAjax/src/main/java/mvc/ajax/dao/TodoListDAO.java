package mvc.ajax.dao;

import java.util.List;

import mvc.ajax.dto.TodoListDTO;

public interface TodoListDAO {
	/**
	 * 전체검색
	 * */
	List<TodoListDTO> selectAll();
	
	/**
	 * todo 추가
	 * */
	 int insert(TodoListDTO todoListDTO);
	 
	/**
	 * todo 상태 수정
	 * */
	 int update(TodoListDTO todoListDTO);
	
	/**
	 * todo 삭제
	 * */
	 int delete(int id);
	
	/**
	 * 키워드 검색
	 * */
	 List<TodoListDTO> selectIncludesbyWord(String word);
	
	
	
	

}
