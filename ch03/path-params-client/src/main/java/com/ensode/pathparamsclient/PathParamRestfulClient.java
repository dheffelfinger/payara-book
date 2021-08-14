package com.ensode.pathparamsclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PathParamRestfulClient {

  @Inject
  @RestClient
  private PathParamsSampleServiceClient pathParamsSampleServiceClient;
  private static final Logger LOGGER = Logger.getLogger(PathParamRestfulClient.class.getName());

  public void init(@Observes @Initialized(ApplicationScoped.class) Object object) {
    String informalResponse;
    String formalResponse;

    informalResponse = pathParamsSampleServiceClient.sayHello("David");
    formalResponse = pathParamsSampleServiceClient.sayFormalHello("Mr", "Heffelfinger");
    
    LOGGER.log(Level.INFO, String.format("Received the following informal response: %s", informalResponse));
    LOGGER.log(Level.INFO, String.format("Received the following formal response: %s", formalResponse));
  }
}
