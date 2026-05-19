package kosta.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // JPA 관리 대상(CRUD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Getter
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teamId;
	private String teamName;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER,
			cascade = CascadeType.REMOVE, orphanRemoval = true)
//	@OneToMany(mappedBy = "team") // fetchType: 기본 LAZY
	private List<Member> memberList;
}