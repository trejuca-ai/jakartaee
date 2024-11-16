package mx.org.banxico.jakarta.service;

import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.entity.Token;

public interface StaffService {

	public void save(Staff staff);
	public void saveToken(Token token);
	public Staff findByUserName(String userName);
	public Token validateToken(String token);
	public Token findByUserId(Integer id);
	public void updateToken(Token token);
}
