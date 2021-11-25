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
import org.eclipse.microprofile.openapi.annotations.media.Content;
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
  @APIResponse(responseCode = "200",
          description = "Employee found",
          content = @Content(mediaType = APPLICATION_JSON))
  @APIResponse(responseCode = "404",
          description = "Employee not found ")
  public Employee findEmployee(@QueryParam("employeeId") Integer employeeID) {
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
