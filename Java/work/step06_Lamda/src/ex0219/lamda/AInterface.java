package ex0219.lamda;

@FunctionalInterface // 메소드가 단 1개만 존재
public interface AInterface {
	void aa();
	default void bb() { }
}