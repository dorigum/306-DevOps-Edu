package kosta.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "team") // exclude가 빠지면 무한 루프 발생
@Getter
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memberId;
	private String name;
	private Integer age;

	// 한 명의 Member는 하나의 Team에 참여할 수 있다.
	// @ManyToOne // Team 입장에서 여러 명의 멤버 존재
	// Default: fetch = FetchType.EAGER(즉시 로딩) -> left join
	@ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
	@JoinColumn(name = "tid") // FK 컬럼 이름
	private Team team;
}