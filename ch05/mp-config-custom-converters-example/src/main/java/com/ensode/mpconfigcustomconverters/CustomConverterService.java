package com.ensode.mpconfigcustomconverters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
@Path("customconverter")
public class CustomConverterService {

  @Inject
  @ConfigProperty(name = "customer1")
  private Person person;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Person getCustomer() {
    return person;
  }

}
