package mx.org.banxico.jakarta.helper;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@PostConstruct
	public void init() {
		if (this.emf == null) {
			emf = Persistence.createEntityManagerFactory("default-pu");
		}
	}
	
	@Produces
	@Default
	@Dependent
	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager em) {
		if(em.isOpen()) {
			em.close();
		}
	}
}
