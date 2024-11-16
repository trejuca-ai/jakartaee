package mx.org.banxico.jakarta.service;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.entity.Staff;
import mx.org.banxico.jakarta.entity.Token;
import mx.org.banxico.jakarta.repository.StaffRepository;
import mx.org.banxico.jakarta.repository.TokenRepository;

@Model
@Transactional
public class StaffServiceImpl implements StaffService {

	@Inject
	private StaffRepository<Staff> staffRepository;
	@Inject
	private TokenRepository<Token> tokenRepository;
	
	@Override
	public void save(Staff staff) {
		staffRepository.save(staff);
	}

	@Override
	public void saveToken(Token token) {
		tokenRepository.save(token);
	}

	@Override
	public Staff findByUserName(String userName) {
		return staffRepository.findByUserName(userName);
	}

	@Override
	public Token validateToken(String token) {
		return tokenRepository.findByToken(token);
	}

	@Override
	public Token findByUserId(Integer id) {
		return tokenRepository.findTokenByUserId(id);
	}

	@Override
	public void updateToken(Token token) {
		tokenRepository.update(token);
	}

}
