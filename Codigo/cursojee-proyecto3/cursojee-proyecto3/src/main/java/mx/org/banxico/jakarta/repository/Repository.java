package mx.org.banxico.jakarta.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

	public void save(T entity);
	public void update(T entity);
	public void delete(Integer id);
	public List<T> findAll();
	public Optional<T> findById(Integer id);
	public List<T> findRange(Integer numeroPagina, Integer tamanioPagina);
	public Long count();
	
}
