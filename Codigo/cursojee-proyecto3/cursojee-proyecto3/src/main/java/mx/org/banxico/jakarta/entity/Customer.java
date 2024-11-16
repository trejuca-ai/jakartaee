package mx.org.banxico.jakarta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@Setter
@Getter
@ToString
public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "store_id")
	@NotNull(message = "El storeId no puede ser vacio")
	private Integer storeId;
	
	@Column(name = "first_name")
	@NotEmpty(message = "{validation.customer.firstName.notEmpty}")
	@Size(min = 3, message = "{validation.customer.firstName.size.min}")
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty(message = "El nombre no puedes ser vacio")
	@Size(min = 3, message = "El tamaño minio para el apellido es de 3 caracteres")
	private String lastName;
	
	@Column
	@Email(message = "El campo email debe tener el formato de correo electronico")
	private String email;
	
	@Column(name = "address_id")
	@NotNull(message = "El addressId no puede ser vacio")
	private Integer addressId;
	
	
}
