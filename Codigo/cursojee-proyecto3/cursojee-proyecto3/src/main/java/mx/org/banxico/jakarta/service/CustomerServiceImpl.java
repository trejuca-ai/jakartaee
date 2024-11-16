package mx.org.banxico.jakarta.service;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.dto.PaginationDto;
import mx.org.banxico.jakarta.entity.Customer;
import mx.org.banxico.jakarta.helper.PaginationHelper;
import mx.org.banxico.jakarta.repository.Repository;

@Model
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private Repository<Customer> customerRepository;
	@Inject
	private PaginationHelper<Customer> paginationHelper;
	
	@Override
	public List<Customer> findAll() {	
		return customerRepository.findAll();
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void update(Customer customer) {
		customerRepository.update(customer);
		
	}

	@Override
	public void delete(Integer id) {
		customerRepository.delete(id);
	}

	@Override
	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	public PaginationDto<Customer> findRange(Integer pageNumber) {
		
		pageNumber = paginationHelper.getMinPageNumber(pageNumber);
		List<Customer> customers = customerRepository.findRange(
				paginationHelper.getPageNumber(pageNumber),
				PaginationHelper.PAGE_SIZE);
		
		Long total = customerRepository.count();
		
		return paginationHelper.getPaginatedData(customers, total, pageNumber);
	}
}
