package com.ensode.queryparamsclient;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("queryparams")
public interface QueryParamsSampleServiceClient {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String sayFormalHello(@QueryParam("title") String title, @QueryParam("lastName") String lastName);

}
