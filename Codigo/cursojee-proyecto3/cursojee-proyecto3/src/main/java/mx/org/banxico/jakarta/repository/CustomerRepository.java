package mx.org.banxico.jakarta.repository;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import mx.org.banxico.jakarta.entity.Customer;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CustomerRepository {

	@Inject
	private EntityManager em;

	
	public void save(Customer entity) {
		em.persist(entity);
	}
	
	public void update(Customer entity) {
		em.merge(entity);
	}
	
	public void delete(Integer id) {
		em.remove(em.find(Customer.class, id));
	}
	
	public Customer findById(Integer id) {
		return em.find(Customer.class, id);
	}
	
	public List<Customer> findAll() {
		CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(Customer.class));
		
		return em.createQuery(criteria).getResultList();
	}
	
	public List<Customer> findRange(Integer numeroPagina, Integer tamanioPagina) {
		
		CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(Customer.class));
		
		Query query = em.createQuery(criteria);
		query.setMaxResults(tamanioPagina);
		query.setFirstResult(numeroPagina);
		
		return query.getResultList();
	}
	
	public Integer count() {
		
		CriteriaQuery criteria =em.getCriteriaBuilder().createQuery();
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(em.getCriteriaBuilder().count(root));
		
		Query query = em.createQuery(criteria);
		
		return (Integer) query.getSingleResult();
	}
	
	public Integer findMax(String namedQuery) {
		return em.createNamedQuery(
				namedQuery, Integer.class).getSingleResult();
	}
}
