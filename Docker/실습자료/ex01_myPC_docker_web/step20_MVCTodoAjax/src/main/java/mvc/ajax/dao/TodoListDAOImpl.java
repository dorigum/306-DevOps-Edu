package mvc.ajax.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.ajax.dto.TodoListDTO;
import mvc.ajax.util.DbUtil;

public class TodoListDAOImpl implements TodoListDAO {

	@Override
	public List<TodoListDTO> selectAll() {
		Connection con = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  List<TodoListDTO> list = new ArrayList<TodoListDTO>();
		  try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement("select id,done, content, reg_date from todo_list order by id");
		   rs  = ps.executeQuery();
		   while(rs.next()){
		    list.add(new TodoListDTO(rs.getInt(1), 
		    		rs.getString(2).equals("1") ? true : false , 
		    		rs.getString(3),  rs.getString(4)));
		   }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    DbUtil.dbClose(rs, ps, con);
		  }
		  return list;
	}
	

	@Override
	public int insert(TodoListDTO todoListDTO) {
		 PreparedStatement ps = null;
		  Connection con = null;
		  int result=0;
		  try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement("insert into todo_list(content) value(?)");
		   ps.setString(1, todoListDTO.getContent());
		   
		   result = ps.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   DbUtil.dbClose( ps, con);
		  }
		  return result;
	}

	@Override
	public int update(TodoListDTO todoListDTO) {
		PreparedStatement ps = null;
		  Connection con =null;
		  int result=0;
		  try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement("update todo_list set done=? where id=?");
		   
		   ps.setString(1, todoListDTO.isDone() ? "1" : "0" );
		   ps.setInt(2, todoListDTO.getId());
		  
		   
		   result = ps.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   DbUtil.dbClose( ps , con);
		  }
		  return result;
	}

	@Override
	public int delete(int id) {
		PreparedStatement ps = null;
		  Connection con =null;
		  int result=0;
		  try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement("delete from todo_list where id=?");
		   ps.setInt(1, id);
		   result = ps.executeUpdate();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  } finally {
		   DbUtil.dbClose( ps, con);
		  }
		  return result;
	}

	@Override
	public List<TodoListDTO> selectIncludesbyWord(String word) {
		Connection con = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  List<TodoListDTO> list = new ArrayList<TodoListDTO>();
		  try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement("select id,done, content, reg_date from todo_list where upper(content) like upper(?) order by id");
		   ps.setString(1, "%"+word+"%");
		   
		   rs  = ps.executeQuery();
		   while(rs.next()){
		    list.add(new TodoListDTO(rs.getInt(1), 
		    		rs.getString(2).equals("1") ? true : false , 
		    		rs.getString(3),  rs.getString(4)));
		   }

		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    DbUtil.dbClose(rs, ps, con);
		  }
		  return list;
	}
	

}
