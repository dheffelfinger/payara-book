package com.ensode.mpcustomconfigsource;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
@Path("mpcustomconfigsource")
public class CustomConfigSourceService {

  private static final Logger LOGGER = Logger.getLogger(CustomConfigSourceService.class.getName());

//  @Inject
//  private Config config;
  @Inject
  @ConfigProperty(name = "sample.property1")
  private String prop1;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getText() {

    //begin temp code
//    StringBuilder configSourceInfoBuilder = new StringBuilder();
//    config.getConfigSources().forEach(cs -> {
//     configSourceInfoBuilder.append(String.format("--- %s ---\n", cs.getName()));
//     configSourceInfoBuilder.append(cs.getProperties().toString());
//     configSourceInfoBuilder.append("\n");
//    });
    //end temp code
//    LOGGER.log(Level.INFO, configSourceInfoBuilder.toString());
    return String.format("\nProperty value is: %s\n", prop1);
  }

}
