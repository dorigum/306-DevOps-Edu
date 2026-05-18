package kosta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp")
public class Employee extends BaseEntity {
	@Id // PK
	private String empno;

	private String job;
}
