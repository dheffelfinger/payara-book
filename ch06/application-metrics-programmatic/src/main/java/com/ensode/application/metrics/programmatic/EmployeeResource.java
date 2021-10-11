package com.ensode.application.metrics.programmatic;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

@ApplicationScoped
@Path("employeeservice")
public class EmployeeResource {

  @Inject
  @Metric
  private Counter employeeCounter;

  private List<Employee> employeeList = new CopyOnWriteArrayList<>(); //thread safe

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void hireEmployee(Employee employee) {
    employeeList.add(employee);
    employeeCounter.inc();
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  public void fireEmployee(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {

    Optional<Employee> employeeToFire = employeeList.stream().filter(emp -> emp.getFirstName().equals(firstName) && emp.getLastName().equals(lastName)).findAny();
    employeeToFire.ifPresent(
            emp -> {
              employeeList.remove(emp);
              employeeCounter.inc(-1);
            });
  }
}
