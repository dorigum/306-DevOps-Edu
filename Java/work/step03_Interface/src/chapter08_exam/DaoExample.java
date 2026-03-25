package chapter08_exam;

public class DaoExample {
	// chapter08_p392(확인 문제_07번)
	public static void dbWork(DataAccessObject dao) {
		dao.select();
		dao.insert();
		dao.update();
		dao.delete();
	}
	
	public static void main(String[] args) {
		dbWork(new OracleDao());
		System.out.println("-----------------------");
		dbWork(new MySqlDao());
	}
}