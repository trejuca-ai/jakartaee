package mx.org.banxico.jakarta.mapper;

import java.util.Set;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationMessagesMapper 
	implements ExceptionMapper<ConstraintViolationException> {

	@Context
	UriInfo uriInfo;
//	@Inject
//	private Properties msg;
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		return Response
				.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(prepararMensaje(exception))
				.build();
	}

	private JsonObject prepararMensaje(ConstraintViolationException e) {
		
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		jsonObject.add("host", uriInfo.getAbsolutePath().getHost());
		jsonObject.add("recurso", uriInfo.getAbsolutePath().getPath());
		jsonObject.add("titulo", "Errores de validacion");
		
		JsonArrayBuilder jsonArray =  Json.createArrayBuilder();
		
		for (ConstraintViolation<?> constraint : violations) {
			
			// Validacion tomando la llave y leyendo directamente del archivo
			// properties
//			String mensaje = msg.getProperty(constraint.getMessage()) == null
//					? ""
//					: msg.getProperty(constraint.getMessage());
			
			JsonObject error = Json.createObjectBuilder()
					.add("campo", constraint.getPropertyPath().toString())
					.add("clase", constraint.getLeafBean().toString())
					//.add("mensaje1", mensaje)
					.add("mensaje", constraint.getMessage())
					.build();
			
			jsonArray.add(error);
		}
		
		return jsonObject.add("errores", jsonArray.build()).build();
	}
}
