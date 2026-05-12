package sample04;

public class StudentDAOImpl implements StudentDAO {
	public StudentDAOImpl() {
		System.out.println("StudentDAOImpl 생성자");
	}

	public void insert(Student student) {
		System.out.println("StudentDAOImpl의 insert call...");
		System.out.println(student);
	}
}
