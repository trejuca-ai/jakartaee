package mx.org.banxico.jakarta.repository;

import java.util.List;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;

@Local
public interface Repository<T> {

	public void save(T entity);
	public void update(T entity);
	public void delete(Integer id);
	public List<T> findAll();
	public T findById(Integer id);
	public List<T> findRange(Integer numeroPagina, Integer tamanioPagina);
	public Integer count();
	
}
