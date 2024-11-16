package mx.org.banxico.jakarta.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ResourceNotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2662597833094904095L;

	public ResourceNotFoundException(String message) {
		super(message, Response.Status.NOT_FOUND);
	}

	public ResourceNotFoundException(Object object) {
		super(Response.status(Status.NOT_FOUND)
				.entity(object)
				.build());
	}
}
