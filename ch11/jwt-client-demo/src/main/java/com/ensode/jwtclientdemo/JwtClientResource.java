package com.ensode.jwtclientdemo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Path("jwtclient")
public class JwtClientResource {

  @Inject
  @RestClient
  private JwtDemoResourceClient jwtDemoResourceClient;

  @Inject
  @ConfigProperty(name = "ensode.jwt.header.string")
  private String jwtHeaderString;

  @GET
  public String accessSecuredEndpoint() {
    jwtDemoResourceClient.secured("Bearer ".concat(jwtHeaderString));
    return "secured endpoint accessed successfully";
  }
}
