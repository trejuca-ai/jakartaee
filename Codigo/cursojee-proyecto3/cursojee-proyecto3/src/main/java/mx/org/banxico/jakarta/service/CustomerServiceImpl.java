package mx.org.banxico.jakarta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.dto.CustomerDto;
import mx.org.banxico.jakarta.entity.Customer;
import mx.org.banxico.jakarta.repository.CustomerRepository;
import mx.org.banxico.jakarta.repository.CustomerRepositoryImpl;
import mx.org.banxico.jakarta.repository.Repository;

@Model
@Transactional
public class CustomerServiceImpl implements CustomerService {

	//@EJB(beanName = "customerRepository")
	@Inject
	//private CustomerRepository customerRepository;
	private Repository<Customer> customerRepository;
	@Inject
	private ModelMapper modelMapper;
	
	@Override
	public List<Customer> findAll() {	
		return customerRepository.findAll();
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}
	
	

}
