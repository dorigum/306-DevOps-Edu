package sample07;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("controller") // <bean class="BoardController" id="controller"/>
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardDTO boardDto;

	@Autowired
	private BoardDTO boardDto2;

	public BoardController() {
		System.out.println("BoardController 생성자");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("boardDto = " + boardDto);
		System.out.println("boardDto2 = " + boardDto2);
	}

	public void test() {
		System.out.println("BoardController test call");

		boardService.select();
	}
}
