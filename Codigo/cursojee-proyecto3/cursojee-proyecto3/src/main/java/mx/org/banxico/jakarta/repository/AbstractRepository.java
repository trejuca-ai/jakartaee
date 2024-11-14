package mx.org.banxico.jakarta.repository;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class AbstractRepository<T> {

	private Class<T> entity;

	//@PersistenceContext(unitName = "default-pu")
	@Inject
	private EntityManager em;
	
	public AbstractRepository(Class<T> entity) {
		this.entity = entity;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public void save(T entity) {
		getEntityManager().persist(entity);
	}
	
	public void update(T entity) {
		getEntityManager().merge(entity);
	}
	
	public void delete(Integer id) {
		getEntityManager().remove(getEntityManager().find(entity, id));
	}
	
	public T findById(Integer id) {
		return getEntityManager().find(entity, id);
	}
	
	public List<T> findAll() {
		CriteriaQuery criteria = getEntityManager().getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));
		
		return getEntityManager().createQuery(criteria).getResultList();
	}
	
	public List<T> findRange(Integer numeroPagina, Integer tamanioPagina) {
		
		CriteriaQuery criteria = getEntityManager().getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));
		
		Query query = getEntityManager().createQuery(criteria);
		query.setMaxResults(tamanioPagina);
		query.setFirstResult(numeroPagina);
		
		return query.getResultList();
	}
	
	public Integer count() {
		
		CriteriaQuery criteria = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> root = criteria.from(entity);
		criteria.select(getEntityManager().getCriteriaBuilder().count(root));
		
		Query query = getEntityManager().createQuery(criteria);
		
		return (Integer) query.getSingleResult();
	}
	
	public Integer findMax(String namedQuery) {
		return getEntityManager().createNamedQuery(
				namedQuery, Integer.class).getSingleResult();
	}
}
