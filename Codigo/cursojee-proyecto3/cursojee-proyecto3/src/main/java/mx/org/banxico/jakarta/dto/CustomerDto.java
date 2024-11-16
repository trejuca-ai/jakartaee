package mx.org.banxico.jakarta.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CustomerDto {

	private Integer id;
	private Integer storeId;
	private String firstName;
	private String lastName;
	private String email;
	private Integer addressId;
	
}
