package com.ensode.application.metrics.programmatic.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Path("employeeclient")
public class EmployeeClientResource {

  private static final Logger LOGGER = Logger.getLogger(EmployeeClientResource.class.getName());

  @Inject
  @RestClient
  private EmployeeResourceClient employeeResourceClient;

  private List<Employee> employeesToHire;
  private List<Employee> employeesToFire;

  @PostConstruct
  public void init() {
    Employee employee1 = new Employee("Jose", "Jimenez");
    Employee employee2 = new Employee("Meera", "Patel");
    Employee employee3 = new Employee("David", "Heffelfinger");

    employeesToHire = List.of(employee1, employee2, employee3);
    employeesToFire = List.of(employee3);

    LOGGER.log(Level.INFO, JsonbBuilder.create().toJson(employee3));

  }

  @Path("hire")
  @POST
  public void hireEmployees() {
    employeesToHire.forEach(emp -> employeeResourceClient.hireEmployee(emp));
  }

  @Path("fire")
  @POST
  public void fireEmployees(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
    employeesToFire.forEach(emp -> employeeResourceClient.fireEmployee(firstName, lastName));
  }

}
