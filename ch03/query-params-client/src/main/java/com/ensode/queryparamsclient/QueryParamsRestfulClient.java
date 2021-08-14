package com.ensode.queryparamsclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class QueryParamsRestfulClient {

  private static final Logger LOGGER = Logger.getLogger(QueryParamsRestfulClient.class.getName());

  @Inject
  @RestClient
  private QueryParamsSampleServiceClient queryParamsSampleServiceClient;

  public void init(@Observes @Initialized(ApplicationScoped.class) Object object) {

    String serverResponse;

    serverResponse = queryParamsSampleServiceClient.sayFormalHello("Mr", "Heffelfinger");

    LOGGER.log(Level.INFO, String.format("Received the following response: %s", serverResponse));
  }
}
