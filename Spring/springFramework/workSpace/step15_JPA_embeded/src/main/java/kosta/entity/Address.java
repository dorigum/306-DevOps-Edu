package kosta.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable// // @Entity 클래스 안
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String city;
	private String street;

	// Getter/Setter
}
