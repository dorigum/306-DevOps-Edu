package kosta.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
	private String code;
	private String name;
	private int price;
	private String detail;
}
