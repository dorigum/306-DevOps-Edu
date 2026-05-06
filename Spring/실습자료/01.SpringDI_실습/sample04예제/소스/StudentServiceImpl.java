package sample04;

public class StudentServiceImpl implements StudentService {
    
	private StudentDAO studentDAO;//null
    
	public StudentServiceImpl() {
		System.out.println("StudentServiceImpl construcor call..");
	}
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		System.out.println("setStudentDAO(StudentDAO studentDAO) call = "+ studentDAO);
	}
	
	@Override
	public void insert(Student student) {
		studentDAO.insert(student);

	}

}
