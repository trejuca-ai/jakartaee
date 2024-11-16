package mx.org.banxico.jakarta.repository;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.entity.Token;

@Transactional(Transactional.TxType.REQUIRED)
@RequestScoped
public class TokenRepositoryImpl extends AbstractRepository<Token>
	implements TokenRepository<Token> {

	public TokenRepositoryImpl() {
		super(Token.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Token findByToken(String token) {
		Query query  = getEntityManager().createQuery(
				"FROM Token t WHERE t.token = ?1", Token.class);
		
		query.setParameter(1, token);
		
		List<Token> tokens = query.getResultList();
		
		return  !tokens.isEmpty()
				? tokens.get(0)
				: null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Token findTokenByUserId(Integer id) {
		Query query  = getEntityManager().createQuery(
				"FROM Token t WHERE t.staff.id = ?1", Token.class);
		
		query.setParameter(1, id);
		List<Token> tokens = query.getResultList();
		
		return  !tokens.isEmpty()
				? tokens.get(0)
				: null;
	}

}
