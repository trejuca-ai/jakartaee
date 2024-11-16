package mx.org.banxico.jakarta.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.entity.Staff;

@Transactional(Transactional.TxType.REQUIRED)
@RequestScoped
public class StaffRepositoryImpl extends AbstractRepository<Staff> 
	implements StaffRepository<Staff> {

	public StaffRepositoryImpl() {
		super(Staff.class);
	}

	@Override
	public Staff findByUserName(String userName) {
		
		Query query  = getEntityManager().createQuery(
				"FROM Staff sf WHERE sf.userName = ?1", Staff.class);
		
		query.setParameter(1, userName);
		return (Staff) query.getSingleResult();
	}
}
