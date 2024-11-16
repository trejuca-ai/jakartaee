package mx.org.banxico.jakarta.service;

import java.util.List;
import java.util.Optional;

import mx.org.banxico.jakarta.dto.PaginationDto;
import mx.org.banxico.jakarta.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll(); 
	public void save(Customer customer);
	public void update(Customer customer);
	public void delete(Integer id);
	public Optional<Customer> findById(Integer id);
	public PaginationDto<Customer> findRange(Integer pageNumber);
}
