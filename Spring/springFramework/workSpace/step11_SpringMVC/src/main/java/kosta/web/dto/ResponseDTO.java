package kosta.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO {
	private int status;
	private String msg;
}
