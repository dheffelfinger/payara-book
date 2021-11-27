package com.ensode.openapiexample;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@ApplicationScoped
@Path("employeeservice")
public class EmployeeResource {

  private List<Employee> employeeList = new CopyOnWriteArrayList<>(); //thread safe

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void hireEmployee(Employee employee) {
    employeeList.add(employee);
  }

  @GET
  @Operation(operationId = "Find Employee", summary = "Finds an employee", description = "Finds an employee from the given employee ID, returns an HTTP code 404 if the employee is not found")
  @APIResponse(responseCode = "200",
          description = "Employee found",
          content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Employee.class)))
  @APIResponse(responseCode = "404",
          description = "Employee not found ")
  public Employee findEmployee(@QueryParam("employeeID") @Parameter(description = "Employee ID", required = true) Integer employeeID) {
    Optional<Employee> employeeToFind = employeeList.stream().filter(emp -> emp.getEmployeeId().equals(employeeID)
    ).findFirst();

    return employeeToFind.orElseThrow(() -> new NotFoundException());
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  public void fireEmployee(@QueryParam("employeeId") Integer employeeID) {

    Optional<Employee> employeeToFire = employeeList.stream().filter(emp -> emp.getEmployeeId().equals(employeeID)
    ).findAny();
    employeeToFire.ifPresent(
            emp -> {
              employeeList.remove(emp);
            });
  }
}
