package mx.org.banxico.jakarta.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import mx.org.banxico.jakarta.entity.Customer;
import mx.org.banxico.jakarta.service.CustomerService;


@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class CustomerController {

	@Inject
	private CustomerService customerService;
	
	@GET
	public List<Customer> findAll() {
		return customerService.findAll();
	}
	
	@POST
	public Response save(Customer customer) {
		customerService.save(customer);
		return Response.ok().build();
	}
}
