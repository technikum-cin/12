package at.technikumwien;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Getter;
import lombok.NonNull;

@Stateless
public class EmployeeService {
	@Getter
	private EntityManager em;
	
	@PersistenceContext
	private void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public long count() {
		return findAll().size();
	}
	
	public void deleteById(long id) {
		findById(id).ifPresent(employee -> em.remove(employee));
	}
	
    public List<Employee> findAll() {
   		return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
    
    public List<Employee> findAll(@NonNull Predicate<Employee> predicate) {
   		return findAll().stream()
   			.filter(predicate)
   			.collect(Collectors.toList());
    }    
	
	public Optional<Employee> findById(long id) {
		return Optional.ofNullable(em.find(Employee.class, id));
	}
	
	public Employee save(@NonNull Employee employee) {
    	if (employee.getId() == null) {
    		em.persist(employee);
    		return employee;
    	}
    	else {
    		return em.merge(employee);
    	}
    }
    
    public List<Employee> saveAll(@NonNull List<Employee> employees) {
    	return employees.stream()
    		.map(employee -> save(employee))
    		.collect(Collectors.toList());
    }
}