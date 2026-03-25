package app.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.mvc.common.DBManager;
import app.mvc.dto.BoardDTO;
import app.mvc.dto.ReplyDTO;
import app.mvc.exception.DMLException;
import app.mvc.exception.SearchWrongException;

public class BoardDAOImpl implements BoardDAO {
	
	private static BoardDAO instance = new BoardDAOImpl();
	
	private BoardDAOImpl() {}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	

	// 1. 보드의 전체 데이터 조회
	@Override
	public List<BoardDTO> boardSelectAll() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<BoardDTO> list = new ArrayList<>();

		String sql = "select * from board order by board_no desc";

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			
			// 여기부터 작성하세요.
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO bd = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			
				list.add(bd);
			}

		} catch (SQLException e) {
//			e.printStackTrace();
			throw new SearchWrongException("전체 검색에서 예외가 발생했습니다. 다시 조회해주세요.");

		} finally {
			DBManager.releaseConnection(con, ps, rs);
		}
		
		return list;
	}

	// -------------------------------------------------------------------------------------
	// 2. 글 번호에 해당하는 데이터 검색
	@Override
	public BoardDTO boardSelectByNo(int board_no) throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		BoardDTO bd = null;

		String sql = "select * from board where board_no = ?";

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, board_no);

			rs = ps.executeQuery();

			// 컬럼의 정보를(컬럼 개수, 컬럼 타입, 컬럼 이름) 가져온다
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();

			for (int i = 1; i <= count; i++) {
				String columNam = rsm.getColumnName(i);
				System.out.println(columNam);
			}
			
			if (rs.next()) {
				bd = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.releaseConnection(con, ps, rs);
		}

		return bd;
	}

	@Override
	public List<BoardDTO> boardSelectBySubject(String keyWord) throws SearchWrongException {		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<BoardDTO> list = new ArrayList<BoardDTO>();

		String sql = "select * from board where upper(subject) like upper(?)";

		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, "%" + keyWord + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDTO bd = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				
				list.add(bd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBManager.releaseConnection(con, ps, rs);
		}

		return list;
	}
	
	
	@Override
	public int boardInsert(BoardDTO boardDTO) throws DMLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "insert into board(subject, writer, content, board_date)"
					 + "values(?, ?, ?, now())";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);

			// ?의 개수만큼 순서대로 setXxx 설정 필요
			ps.setString(1, boardDTO.getSubject());
			ps.setString(2, boardDTO.getWriter());
			ps.setString(3, boardDTO.getContent());
			
			result = ps.executeUpdate(); // DB쪽으로 쿼리를 전송

		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DMLException("등록 오류가 발생했습니다.");
			
		} finally {
			DBManager.releaseConnection(con, ps);
		}

		return result;
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws DMLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardDelete(int board_no) throws DMLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String sql = "delete from board where board_no = ?";
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);

			// ?의 개수만큼 순서대로 setXxx 설정 필요
			ps.setInt(1, board_no);
			
			result = ps.executeUpdate(); // DB쪽으로 쿼리를 전송

		} catch (SQLException e) {
//			e.printStackTrace();
			throw new DMLException("댓글을 먼저 삭제한 후 글을 삭제해주세요.");
			
		} finally {
			DBManager.releaseConnection(con, ps);
		}

		return result;
	}


	@Override
	public int replyInsert(ReplyDTO replyDTO) throws DMLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO replySelectByParentNo(int board_no) throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException();
		}
	}
	
	/*
	 * 부모 글에 해당하는 댓글 정보 가져오기
	 */
	private List<ReplyDTO> replySelect(Connection con ,int board_no) throws SQLException{
		
		return null;
	}
}