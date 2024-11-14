package mx.org.banxico.jakarta.service;

import java.util.List;

import mx.org.banxico.jakarta.dto.CustomerDto;

public interface CustomerService {

	public List<CustomerDto> findAll(); 
}
