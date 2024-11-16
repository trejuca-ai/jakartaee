package mx.org.banxico.jakarta.repository;


import mx.org.banxico.jakarta.entity.Staff;

public interface StaffRepository<T> extends Repository<T> {
	public Staff findByUserName(String userName);
}
