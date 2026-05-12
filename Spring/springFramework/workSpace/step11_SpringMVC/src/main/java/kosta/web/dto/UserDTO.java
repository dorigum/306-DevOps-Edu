package kosta.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {
	private String id;
	private String name;
	private int age;
	private String addr;
}
