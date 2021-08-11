package com.ensode.restclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class RestfulClient {

  private static final Logger LOGGER = Logger.getLogger(RestfulClient.class.getName());

  @Inject
  @RestClient
  private SampleRestfulServiceClient sampleRestfulServiceClient;

  public void init(@Observes @Initialized(ApplicationScoped.class) Object object) {
    String returnVal = sampleRestfulServiceClient.processPostRequest();

    LOGGER.log(Level.INFO, String.format("Received the following value from the service: %s", returnVal));
  }
}
