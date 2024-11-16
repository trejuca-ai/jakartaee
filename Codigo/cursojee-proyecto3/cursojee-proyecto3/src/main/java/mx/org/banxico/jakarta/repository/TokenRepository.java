package mx.org.banxico.jakarta.repository;

import mx.org.banxico.jakarta.entity.Token;


public interface TokenRepository<T> extends Repository<T> {

	public Token findByToken(String token);
	public Token findTokenByUserId(Integer id);
}
