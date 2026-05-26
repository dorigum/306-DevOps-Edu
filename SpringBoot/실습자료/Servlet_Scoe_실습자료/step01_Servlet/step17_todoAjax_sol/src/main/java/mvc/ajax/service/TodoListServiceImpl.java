package mvc.ajax.service;

import java.util.List;

import mvc.ajax.dao.TodoListDAO;
import mvc.ajax.dao.TodoListDAOImpl;
import mvc.ajax.dto.TodoListDTO;

public class TodoListServiceImpl implements TodoListService {
   TodoListDAO todListDAO = new TodoListDAOImpl();
   
	@Override
	public List<TodoListDTO> selectAll() {
		
		return todListDAO.selectAll();
	}

	@Override
	public int insert(TodoListDTO todoListDTO) {
		// TODO Auto-generated method stub
		return todListDAO.insert(todoListDTO);
	}

	@Override
	public int update(TodoListDTO todoListDTO) {
		// TODO Auto-generated method stub
		return todListDAO.update(todoListDTO);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return todListDAO.delete(id);
	}

	@Override
	public List<TodoListDTO> selectIncludesbyWord(String word) {
		// TODO Auto-generated method stub
		return todListDAO.selectIncludesbyWord(word);
	}

}
