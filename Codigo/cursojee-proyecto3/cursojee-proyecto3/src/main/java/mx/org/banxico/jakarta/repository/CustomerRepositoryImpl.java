package mx.org.banxico.jakarta.repository;

import jakarta.ejb.Stateless;
import mx.org.banxico.jakarta.entity.Customer;

@Stateless(name = "customerRepository")
public class CustomerRepositoryImpl extends AbstractRepository<Customer> 
	implements Repository<Customer> {

	public CustomerRepositoryImpl() {
		super(Customer.class);
	}	
}
