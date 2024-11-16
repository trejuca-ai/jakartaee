package mx.org.banxico.jakarta.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@Column(name = "staff_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="store_id")
	private Integer storeId;
	@Column(name="address_id")
	private Integer addressId;
	@Column(name="active")
	private Boolean active;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "staff_role",
			joinColumns = @JoinColumn(name = "staff_id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private List<Role> roles;
}
