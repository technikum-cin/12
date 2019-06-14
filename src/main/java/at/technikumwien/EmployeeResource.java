package at.technikumwien;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
	@Inject
	private EmployeeService employeeService;
	@Context
	private UriInfo uriInfo;
	
	@POST
	public Response create(Employee employee) {
		employee.setId(null);
		employee = employeeService.save(employee);
		
		var uri = uriInfo.getAbsolutePathBuilder().path(employee.getId().toString()).build();
		return Response.created(uri).build();
	}
	
	@GET
	@Path("/{id}")
	public Employee retrieve(@PathParam("id") Long id) {
		return employeeService.findById(id).orElse(null);
	}
	
	@PUT
	@Path("/{id}")
	public void update(@PathParam("id") Long id, Employee employee) {
		employee.setId(id);
		employeeService.save(employee);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		employeeService.deleteById(id);
	}
		
	@GET
	public List<Employee> retrieveAll() {
		return employeeService.findAll();
	}
}