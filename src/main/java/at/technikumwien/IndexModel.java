package at.technikumwien;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Named("index")
@RequestScoped
public class IndexModel {
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Inject
	private EmployeeService employeeService;
	
	private boolean showActive = true;
	private boolean showNonActive = true;
		
	public List<Employee> getEmployees() {
		if (showActive && showNonActive) {
			return employeeService.findAll();
		}
		else {
			return employeeService.findAll(
				employee -> (employee.isActive() && showActive) || (!employee.isActive() && showNonActive)
			);
		}
	}
}