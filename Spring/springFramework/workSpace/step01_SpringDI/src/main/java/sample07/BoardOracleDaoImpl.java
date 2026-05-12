package sample07;

import org.springframework.stereotype.Repository;

@Repository("boardOracleDaoImpl") // 생성: <bean class="BoardOracleDaoImpl" id="boardOracleDaoImpl"/> 동일
public class BoardOracleDaoImpl implements BoardDAO {

	public BoardOracleDaoImpl() {
		System.out.println("BoardOracleDaoImpl 생성자 호출");
	}

	public void select() {
		System.out.println("BoardOracleDaoImpl select 호출");
	}
}
