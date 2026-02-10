package ex0206.array.student;
/*
 * 학생의 속성을 관리하는 객체
 *  : Value Object = DTO(Data Transfer Object) = Domain
 */
public class Student {
  // 외부에서 직접 필드를 접근할 수 없다.
  private String name; // 이름은 중복 안 됨!!!
  private int age;
  private String addr;
  
  /*
  setXxx()
   : 제한자 - public
   : 리턴 타입 -  void
   : 메소드 이름 - set + 필드 이름 첫 글자 대문자
   : 인수 : 있다.
   */
  public void setName(String name) {
	  this.name = name;
  }
  public void setAge(int age) {
	  if(age > 18)
	    this.age=age;
  }
  public void setAddr(String addr) {
	  this.addr = addr;
  }
  
  /*
  getXxx()
    : 제한자 - public
    : 리턴 타입 -  있다.
    : 메소드 이름 - get + 필드 이름 첫 글자 대문자
    : 인수 : 없다.
   */
   public String getName() {
	  return name;
   }
   
   public int getAge() {
	 return age;
   }
   
   public String getAddr() {
	 return this.addr;
   }  
}