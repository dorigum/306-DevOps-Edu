package sample07;

import org.springframework.stereotype.Repository;

@Repository // <bean class="BoardMyBatisDaoImpl" id="boardMyBatisDaoImpl"/>
public class BoardMyBatisDaoImpl implements BoardDAO {

	public BoardMyBatisDaoImpl() {
		System.out.println("BoardMyBatisDaoImpl 생성자 호출");
	}

	public void select() {
		System.out.println("BoardMyBatisDaoImpl select 호출");
	}
}
