package com.ensode.pathparamsclient;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("pathparams")
public interface PathParamsSampleServiceClient {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{name}")
  public String sayHello(@PathParam("name") String name);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{title}/{lastName}")
  public String sayFormalHello(@PathParam("title") String title, @PathParam("lastName") String lastName);

}
