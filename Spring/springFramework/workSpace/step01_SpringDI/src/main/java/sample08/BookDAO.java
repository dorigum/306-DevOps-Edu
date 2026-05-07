package sample08;

public interface BookDAO {
	void save(EmailSender emailSender, MessageSender messageSender, BookDTO bookDto, BookDTO bookDto2);
}
