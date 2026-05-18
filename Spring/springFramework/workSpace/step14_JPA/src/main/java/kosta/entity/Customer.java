package kosta.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// JPA가 관리하는 객체
@Entity
@Table(name = "cus")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@DynamicUpdate // SQL 캐싱 지원 X
@DynamicInsert // SQL 캐싱 지원 X
public class Customer extends BaseEntity {
	@Id // PK 대상
	// @GeneratedValue(strategy = GenerationType.AUTO) // MySQL의 Auto_increment, Oracle의 Sequence으로 자동 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL의 Auto_increment: IDENTITY는 insert가 실행되지 않으면 -> 쓰기 지연 안 됨!!!
	
	private Long id; // 불변하는 값 형식, int보다 범위가 넓음
	// JPARepository<객체> -> long이 아닌 Long으로 들어가야 함

	@Column(name = "user_name", unique = true, length = 30)
	private String userName;

	@Column(name = "user_age", nullable = true)
	private Integer age; // int: 숫자 0이 들어감 / Integer: 객체여서 null로 인식
	
	// @Transient // DB에 컬럼으로 관리되지 않는다.
	private String addr;
	
	@Temporal(TemporalType.TIMESTAMP) // DB에 datetime 설정
	private Date birthDay; // DB에 date or datetime
	
	private String etc;
	
	// 등록일
	// @CreationTimestamp
	// private LocalDateTime insertDate;
	
	// 수정일
	// @UpdateTimestamp
	// private LocalDateTime updateDate;
}