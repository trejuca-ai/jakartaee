package mx.org.banxico.jakarta.repository;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.entity.Customer;

//@Stateless(name = "customerRepository")
@Transactional(Transactional.TxType.REQUIRED)
@RequestScoped
public class CustomerRepositoryImpl extends AbstractRepository<Customer> 
	implements Repository<Customer> {
	
	public CustomerRepositoryImpl() {
		super(Customer.class);
	}
}
