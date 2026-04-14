package mvc.ajax.dto;

public class TodoListDTO {
  private int id;
  private boolean done;
  private String content;
  private String date;
  
  public TodoListDTO() {}

	public TodoListDTO(int id, boolean done, String content, String date) {
		super();
		this.id = id;
		this.done = done;
		this.content = content;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
  
  
}

