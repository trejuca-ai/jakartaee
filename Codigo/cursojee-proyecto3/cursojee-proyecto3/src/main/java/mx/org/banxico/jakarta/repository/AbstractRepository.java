package mx.org.banxico.jakarta.repository;

import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class AbstractRepository<T> {

	private Class<T> entity;

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
	
	public Optional<T> findById(Integer id) {
		return Optional.ofNullable(getEntityManager().find(entity, id));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery criteria = getEntityManager().getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));
		
		return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(Integer numeroPagina, Integer tamanioPagina) {
		
		CriteriaQuery criteria = getEntityManager().getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entity));
		
		Query query = getEntityManager().createQuery(criteria);
		query.setMaxResults(tamanioPagina);
		query.setFirstResult(numeroPagina);
		
		return query.getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Long count() {
		
		CriteriaQuery criteria = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> root = criteria.from(entity);
		criteria.select(getEntityManager().getCriteriaBuilder().count(root));
		
		Query query = getEntityManager().createQuery(criteria);
		
		return (Long) query.getSingleResult();
	}
	
	public Integer findMax(String namedQuery) {
		return getEntityManager().createNamedQuery(
				namedQuery, Integer.class).getSingleResult();
	}
}
