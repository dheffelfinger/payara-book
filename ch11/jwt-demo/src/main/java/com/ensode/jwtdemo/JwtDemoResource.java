package com.ensode.jwtdemo;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

//need to make JAX-RS endpont a request scoped CDI bean, otherwise security will not "kick in"
@RequestScoped
@Path("jwtdemo")
public class JwtDemoResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @RolesAllowed({"chief"})
  public String secured() {
    return "Secured endpoint accessed successfully\n";
  }
}
