package mx.org.banxico.jakarta.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
