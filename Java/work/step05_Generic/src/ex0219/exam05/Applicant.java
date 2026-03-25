package ex0219.exam05;

// 누가 신청을 하는지?
public class Applicant<T> {
	private T kind; // 모든 사람, 직장인, 학생, 고등학생, 중학생

	public Applicant(T kind) {
		this.kind = kind;
	}
	
	public T getKind() {
		return kind;
	}

	public void setKind(T kind) {
		this.kind = kind;
	}
}