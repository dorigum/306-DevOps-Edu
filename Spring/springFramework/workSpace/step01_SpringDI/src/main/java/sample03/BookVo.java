package sample03;

public class BookVo {
	private String subject;
	private String writer;
	private int price;
	private String date;

	public BookVo(String subject, String writer, int price, String date) {
		this.subject = subject;
		this.writer = writer;
		this.price = price;
		this.date = date;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
