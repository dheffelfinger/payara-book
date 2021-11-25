package com.ensode.openapiexample.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("employeeservice")
public interface EmployeeResourceClient {

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void hireEmployee(Employee employee);

  @GET
  public Employee findEmployee(@QueryParam("employeeId") Integer employeeID);
}
