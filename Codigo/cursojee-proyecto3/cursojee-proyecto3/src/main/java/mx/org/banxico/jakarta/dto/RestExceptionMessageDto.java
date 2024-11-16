package mx.org.banxico.jakarta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestExceptionMessageDto {

	private Integer id;
	private String message;
	
	public RestExceptionMessageDto(Integer id, String message) {
		this.id = id;
		this.message = message;
	}
}
