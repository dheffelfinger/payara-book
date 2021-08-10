package com.ensode.queryparams;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("queryparams")
public class QueryParamsSampleService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String sayHello(@QueryParam("name") String name) {
    return String.format("{\n"
            + "  \"msg\":\"Hello, %s\"\n"
            + "}", name);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("formal")
  public String sayFormalHello(@QueryParam("title") String title, @QueryParam("lastName") String lastName) {
    return String.format("{\n"
            + "  \"msg\":\"Hello, %s %s\"\n"
            + "}", title, lastName);
  }

}
