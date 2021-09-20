package com.ensode.mp.application.config.example;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;

@RequestScoped
@Path("appconfig")
public class AppconfigResource {

  @Inject
  private Config config;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String processGetRequest() {
    ConfigValue configValue = config.getConfigValue("my.config.property");
    return String.format("Property value is %s", configValue.getValue());
  }

}
