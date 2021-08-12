package com.ensode.restclient;

import com.ensode.pojo.DummyPojo;
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
    String returnedGetData;
    String returnedPutData;
    String returnedPostData;
    String returnedDeleteData;
//    String returnedPatchData;

    LOGGER.log(Level.INFO, "--- Sending HTTP GET request ");
    returnedGetData = sampleRestfulServiceClient.processGetRequest();

    LOGGER.log(Level.INFO, "--- Sending HTTP PUT request ");
    returnedPutData = sampleRestfulServiceClient.processPutRequest(new DummyPojo());

    LOGGER.log(Level.INFO, "--- Sending HTTP POST request ");
    returnedPostData = sampleRestfulServiceClient.processPostRequest();

    LOGGER.log(Level.INFO, "--- Sending HTTP DELETE request ");
    returnedDeleteData = sampleRestfulServiceClient.processDeleteRequest();

//    LOGGER.log(Level.INFO, "--- Sending HTTP PATCH request ");
//    returnedPatchData = sampleRestfulServiceClient.processPatchRequest(new DummyPojo());

    LOGGER.log(Level.INFO, String.format("--- GET request response: %s", returnedGetData));
    LOGGER.log(Level.INFO, String.format("--- PUT request response: %s", returnedPutData));
    LOGGER.log(Level.INFO, String.format("--- POST request response: %s", returnedPostData));
    LOGGER.log(Level.INFO, String.format("--- DELETE request response: %s", returnedDeleteData));
//    LOGGER.log(Level.INFO, String.format("--- PATCH request response: %s", returnedPatchData));

  }
}
