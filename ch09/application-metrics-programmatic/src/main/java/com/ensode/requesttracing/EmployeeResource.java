package com.ensode.requesttracing;

import io.opentracing.Tracer;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("employeeservice")
public class EmployeeResource {

  @Inject
  private Event<Employee> employeeEvent;

  @Inject
  private Tracer tracer;

  private List<Employee> employeeList = new CopyOnWriteArrayList<>(); //thread safe

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void hireEmployee(Employee employee) throws InterruptedException {
    //simulate slow processing
    Thread.sleep(100);

    tracer.activeSpan().setTag("employee", String.format("%s %s", employee.getFirstName(), employee.getLastName()));
    employeeList.add(employee);
    employeeEvent.fire(employee);
  }
}
