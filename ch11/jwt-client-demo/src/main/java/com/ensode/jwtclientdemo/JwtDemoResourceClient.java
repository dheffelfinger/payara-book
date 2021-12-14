package com.ensode.jwtclientdemo;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("jwtdemo")
public interface JwtDemoResourceClient {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String secured(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader);
}
