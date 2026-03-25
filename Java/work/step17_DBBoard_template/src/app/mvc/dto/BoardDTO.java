package app.mvc.dto;

import java.util.List;

public class BoardDTO {
	private int board_no; // 글번호
	private String subject; // 제목
	private String writer; // 작성자
	private String content; // 내용
	private String board_date; // 등록일
	
	// 댓글 정보
	private List<ReplyDTO> repliesList ;
	
	public BoardDTO() {}

	public BoardDTO(int board_no, String subject, String writer, String content, String board_date) {
		super();
		this.board_no = board_no;
		this.subject = subject;
		this.writer = writer;
		this.content = content;
		this.board_date = board_date;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	
	public List<ReplyDTO> getRepliesList() {
		return repliesList;
	}

	public void setRepliesList(List<ReplyDTO> repliesList) {
		this.repliesList = repliesList;
	}

	@Override
	public String toString() {
		return board_no + " | " + writer + "|" + subject + "|" + "|" + content + "|" + board_date;
	}
}