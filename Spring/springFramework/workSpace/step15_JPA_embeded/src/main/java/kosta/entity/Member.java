
package kosta.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // JPA 관리 객체 - CRUD 가능
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Embedded // 클래스 위에 @Embeddable가 선언된 객체만 가져다가 쓸 수 있다.
	private ContactInfo contactInfo;
	
	@ElementCollection // Member_hobbies 테이블 생성
    @Column(name = "hobby")
	@CollectionTable(name = "member_hobby", joinColumns = @JoinColumn(name = "member_id"))
    private List<String> hobbies = new ArrayList<>(); // 취미
	
	// 주소
	@ElementCollection // 테이블 생성 -> 일대다 관계
	@CollectionTable(name = "member_address", joinColumns = @JoinColumn(name = "member_id"))
	private List<Address> addressHistory;
}