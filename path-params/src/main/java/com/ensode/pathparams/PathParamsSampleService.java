package com.ensode.pathparams;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("pathparams")
public class PathParamsSampleService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{name}")
  public String sayHello(@PathParam("name") String name) {
    return String.format("{\n"
            + "  \"msg\":\"Hello, %s\"\n"
            + "}", name);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{title}/{lastName}")
  public String sayFormalHello(@PathParam("title") String title, @PathParam("lastName") String lastName) {
    return String.format("{\n"
            + "  \"msg\":\"Hello, %s %s\"\n"
            + "}", title, lastName);
  }

}
