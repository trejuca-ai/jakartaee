package mx.org.banxico.jakarta.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import mx.org.banxico.jakarta.dto.CustomerDto;
import mx.org.banxico.jakarta.entity.Customer;
import mx.org.banxico.jakarta.repository.Repository;

public class CustomerServiceImpl implements CustomerService {

	@EJB(beanName = "customerRepository")
	private Repository<Customer> customerRepository;
	@Inject
	private ModelMapper modelMapper;
	
	@Override
	public List<CustomerDto> findAll() {
		
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDto> customersDto = customers.stream()
				.map(customer -> modelMapper.map(customer, CustomerDto.class))
				.collect(Collectors.toList());
		
		return customersDto;
	}

}
