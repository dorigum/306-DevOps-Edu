package sample07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Component // 생성: <bean class="BoardDTO" id="boardDto"/>
@Scope("prototype") // 지연 초기화
public class BoardDTO {
	@Value("100")
	private int no;

	@Value("수고 많으셨어요:D")
	private String subject;

	@Value("집에 가자요~~~~")
	private String content;
}
