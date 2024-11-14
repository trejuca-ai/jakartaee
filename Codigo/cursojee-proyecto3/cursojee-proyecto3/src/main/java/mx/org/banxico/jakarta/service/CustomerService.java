package mx.org.banxico.jakarta.service;

import java.util.List;

import mx.org.banxico.jakarta.dto.CustomerDto;
import mx.org.banxico.jakarta.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll(); 
}
