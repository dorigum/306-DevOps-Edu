package sample08;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
//@ToString(exclude = {"writer", "date"}) // StackOverflow 방지를 위해서 객체를 제외 시킴.
//@Data // Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class BookDTO {
//	@Value("springDI")
	private String subject;

//	@Value("도연")
//	@Getter // 원하는 필드 위에 부분적으로도 사용 가능
	private String writer;

//	@Value("25000")
	private int price;

//	@Value("2026-05-08")
	private String date;
}
