package mx.org.banxico.jakarta.security;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import mx.org.banxico.jakarta.configuration.CursoJEEProyecto3Configuration.Role;
import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.service.StaffService;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	@Inject
	private StaffService staffService;
	@Inject
	@AuthenticatedUser
	private Staff authenticatedUser;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		Class<?> resourceClass = resourceInfo.getResourceClass();
		List<Role> classRoles = extractRoles(resourceClass);
	
		Method resourceMethod = resourceInfo.getResourceMethod();
		List<Role> methodRoles = extractRoles(resourceMethod);
		
		try {
			if (methodRoles.isEmpty()) {
				checkPermissions(classRoles);
			} else {
				checkPermissions(methodRoles);
			}
		} catch (Exception e) {
			requestContext.abortWith(
				Response.status(Response.Status.FORBIDDEN).build());
		}

	}
	
	private List<Role> extractRoles(AnnotatedElement annotatedElement) {
		
		if (annotatedElement == null) {
			return new ArrayList<Role>();
		} else {
			Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
		}
	}

    private void checkPermissions(List<Role> allowedRoles) throws Exception {
    	Staff staff = staffService.findByUserName(authenticatedUser.getUserName());
    	for(mx.org.banxico.jakarta.entity.Role role : staff.getRoles()) {
    		if (allowedRoles.contains(Role.valueOf(role.getRoleName()))) return;
    	}
    	throw new Exception();
    }
}
