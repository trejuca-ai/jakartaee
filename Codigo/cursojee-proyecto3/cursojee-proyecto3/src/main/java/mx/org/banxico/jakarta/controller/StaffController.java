package mx.org.banxico.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.helper.HashHelper;
import mx.org.banxico.jakarta.service.StaffService;

@Path("/staff")
@Produces("application/json")
@Consumes("application/json")
public class StaffController {

	@Inject
	private StaffService staffService;
	
	@GET
	public Response findAll() {
		System.out.println("entra al controler");
		return Response.ok().build();
	}
	
	@POST
	public Response save(Staff staff) {
		System.out.println(staff);
		staff.setPassword(
			HashHelper.hashPassword(staff.getPassword()));
		staffService.save(staff);
		return Response.ok().build();
	}
}
