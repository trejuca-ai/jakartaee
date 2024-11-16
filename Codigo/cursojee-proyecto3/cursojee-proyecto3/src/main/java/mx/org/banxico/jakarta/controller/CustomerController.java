package mx.org.banxico.jakarta.controller;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import mx.org.banxico.jakarta.configuration.CursoJEEProyecto3Configuration.Role;
import mx.org.banxico.jakarta.dto.PaginationDto;
import mx.org.banxico.jakarta.dto.RestExceptionMessageDto;
import mx.org.banxico.jakarta.entity.Customer;
import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.exception.ResourceNotFoundException;
import mx.org.banxico.jakarta.security.AuthenticatedUser;
import mx.org.banxico.jakarta.security.Secured;
import mx.org.banxico.jakarta.service.CustomerService;


@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class CustomerController {

	@Inject
	private CustomerService customerService;
	@Inject
	private Properties msg;
	@Inject
	@AuthenticatedUser
	Staff authenticatedUser;
	
	@GET
	@Secured({Role.UsuarioRegular})
	public List<Customer> findAll() {
		return customerService.findAll();
	}
	
	@POST
	@Secured({Role.UsuarioRegular})
	public Response save(@Valid Customer customer) {
		customerService.save(customer);
		return Response.ok().build();
	}
	
	@PUT
	@Secured({Role.UsuarioRegular})
	public Response update(Customer customer) {
		customerService.update(customer);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Secured({Role.Administrador})
	public Response delete(
			@PathParam("id") Integer id) {
		customerService.delete(id);
		return Response.ok().build();
	}
	
	@GET
	@Path("/{id}")
	@Secured({Role.UsuarioRegular})
	public Response findById(
			@PathParam("id") Integer id) {
		
		 Optional<Customer> customer = customerService.findById(id);
		 
		 customer.orElseThrow(
			 () -> new ResourceNotFoundException(
				 new RestExceptionMessageDto(
					 id, msg.getProperty("exception.resource.notFound"))));
		 
//		 if (customer == null) {
//			 throw new ResourceNotFoundException(
//				 new RestExceptionMessageDto(
//					 id, "No se encontro el usuario con el id proporcionado"));
//		 }
		 
		 return Response.ok(customer.get()).build();
	}
	
	@GET
	@Path("/pagination")
	@Secured({Role.Administrador})
	public PaginationDto<Customer> findRange(
			@QueryParam("page") @DefaultValue("1") Integer pageNumber) {
		return customerService.findRange(pageNumber);
	}
	
}
