package mx.org.banxico.jakarta.controller;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import mx.org.banxico.jakarta.dto.CredentialsDto;
import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.entity.Token;
import mx.org.banxico.jakarta.helper.HashHelper;
import mx.org.banxico.jakarta.service.StaffService;

@Path("/auth")
@Transactional
public class AuthController {

	@Inject
	private StaffService staffService;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticateUser(CredentialsDto credentialsDto) {
		
		try {
			// 1. Autenticar usuario
			Staff staff = authenticate(credentialsDto);
			// 2. Emitir token
			String token = issueToken(staff);
			// 3. Devolver token
			return Response.ok(token).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	private Staff authenticate(CredentialsDto credentialsDto) {
		
		Staff staff = staffService.findByUserName(credentialsDto.getUserName());

		if (staff == null || !HashHelper.verifyPassword(
				staff.getPassword(), credentialsDto.getPassword())) {
			throw new IllegalArgumentException("Ususario invalido");
		}
		
		return staff;
	}
	
	private String issueToken(Staff staff) {

		Date expirationDate = new Date(System.currentTimeMillis() + 3600000);
		String token = Jwts.builder()
			.setSubject(staff.getUserName())
			.setExpiration(expirationDate)
			.compact();
		
		Token tokenEntity = new Token();
		tokenEntity.setToken(token);
		tokenEntity.setExpirationDate(expirationDate);
		tokenEntity.setStaff(staff);
		
		Token previousToken = staffService.findByUserId(staff.getId());

		if (previousToken != null) {
			
			Date previousTokenDate = previousToken.getExpirationDate();
			Date currentDate = new Date(System.currentTimeMillis());
			
			if (currentDate.after(previousTokenDate)) {
				previousToken.setToken(token);
				previousToken.setExpirationDate(expirationDate);
				
				staffService.updateToken(previousToken);
			} else {
				return previousToken.getToken();
			}
		} else {
			staffService.saveToken(tokenEntity);
		}
		return token;
	}
}
