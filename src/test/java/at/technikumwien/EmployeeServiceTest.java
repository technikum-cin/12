package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	@Mock
	private EntityManager em;
	@InjectMocks
	private EmployeeService employeeService;
	
	private Employee emp;
	
	@BeforeEach
	public void setUp() {
		emp = new Employee();
	}
	
	@Test
	public void testMocks() {
		assertNotNull(em);
		assertNotNull(employeeService);
		assertSame(em, employeeService.getEm());
    	assertNull(em.merge(null));
	}
	
	public void testSaveWithCreate() {
		employeeService.save(emp);
		
		verify(em, times(1)).persist(emp);
		verify(em, never()).merge(any(Employee.class));   // redundant
		verifyNoMoreInteractions(em);
	}
	
	public void testSaveWithUpdate() {
		emp.setId(1L);

		// variant 1
		when(em.merge(emp)).thenReturn(emp);
		// variant 2
		// doReturn(emp).when(em).merge(emp);
		
		var empSaved = employeeService.save(emp);
		
		assertSame(emp, empSaved);
		verify(em).merge(emp);   // ... times(1) not necessary since default
		verifyNoMoreInteractions(em);
	}
	
	// TODO add more tests here ;-)	
}