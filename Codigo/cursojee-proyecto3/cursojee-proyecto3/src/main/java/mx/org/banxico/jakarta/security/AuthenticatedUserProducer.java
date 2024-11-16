package mx.org.banxico.jakarta.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.service.StaffService;

@Model
public class AuthenticatedUserProducer {

	@Produces
	@RequestScoped
	@AuthenticatedUser
	private Staff authenticatedUser;
	
	@Inject
	private StaffService staffService;
	
    public void handleAuthenticationEvent(@Observes @AuthenticatedUser String username) {
        this.authenticatedUser = findUser(username);
    }

    private Staff findUser(String username) {
    	return staffService.findByUserName(username);
    }
}
