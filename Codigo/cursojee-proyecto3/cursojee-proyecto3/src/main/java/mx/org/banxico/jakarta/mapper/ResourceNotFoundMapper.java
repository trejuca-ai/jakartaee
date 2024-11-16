package mx.org.banxico.jakarta.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import mx.org.banxico.jakarta.exception.ResourceNotFoundException;

@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<ResourceNotFoundException> {

	
	@Override
	public Response toResponse(ResourceNotFoundException exception) {
		
		return Response.status(Response.Status.NOT_FOUND)
				.entity(exception)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
