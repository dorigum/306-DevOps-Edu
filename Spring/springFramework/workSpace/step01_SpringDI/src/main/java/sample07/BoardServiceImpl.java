package sample07;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service // <bean class="BoardServiceImpl" id="boardServiceImpl"/>
public class BoardServiceImpl implements BoardService {
	@Autowired // byType -> byName 기준
	@Qualifier("boardMyBatisDaoImpl")
	private BoardDAO boardMyBatisDaoImpl;

	@Autowired
	private BoardDAO boardOracleDaoImpl;

	public BoardServiceImpl() {
		System.out.println("BoardServiceImpl 생성자");
	}

	@PostConstruct
	public void init() {
		System.out.println("boardMyBatisDaoImpl = " + boardMyBatisDaoImpl);
		System.out.println("boardOracleDaoImpl = " + boardOracleDaoImpl);
	}

	public void select() {
		System.out.println("BoardServiceImpl select call");

		boardMyBatisDaoImpl.select();
		boardOracleDaoImpl.select();
	}
}
