package mx.org.banxico.jakarta.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.entity.Role;

@Transactional(Transactional.TxType.REQUIRED)
@RequestScoped
public class RoleRepositoryImpl extends AbstractRepository<Role> 
	implements Repository<Role> {
	
	public RoleRepositoryImpl() {
		super(Role.class);
	}
}
