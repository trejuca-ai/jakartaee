package org.eclipse.jax.completo.security;

import java.io.IOException;

import jakarta.annotation.Priority;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import mx.org.banxico.jakarta.entity.Token;
import mx.org.banxico.jakarta.service.StaffService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	private StaffService staffService;
	@Inject
	@AuthenticatedUser
	Event<String> userAuthenticatedEvent;
	
	@Override
	public void filter(ContainerRequestContext requestContext) 
			throws IOException {
		
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Token desconocido");
		}
		
		String token = authorizationHeader.substring("Bearer".length()).trim();
		
		try {
			validateToken(token);
		} catch (Exception e) {
			requestContext.abortWith(
					Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private void validateToken(String token) 
		throws Exception {
		
		Token tokenUser = staffService.validateToken(token);
		userAuthenticatedEvent.fire(tokenUser.getStaff().getUserName());
		
	}
}
