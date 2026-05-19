package kosta.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable // 다른 @Entity 내부에서 포함되어 질 수 있는 객체이다.
@Builder
public class ContactInfo {
	private String mobilePhone;
	private String housePhone;
	private String companyPhone;

}