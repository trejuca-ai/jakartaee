package mx.org.banxico.jakarta.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "token")
public class Token {

	@Id
	@Column(name = "token_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="token")
	private String token;
	@Column(name="expiration_date")
	private Date expirationDate;
	@OneToOne
	@JoinColumn(name = "staff_id", unique = true)
	private Staff staff;
}
