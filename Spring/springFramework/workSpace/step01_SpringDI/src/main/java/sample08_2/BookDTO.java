package sample08_2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"writer", "date"})
@Component
@Scope("prototype")
public class BookDTO {

	@Value("springDI")
	private String subject;

	@Value("writer")
	private String writer;

	@Value("25000")
	private int price;

	@Value("2026-05-08")
	private String date;
}
