package com.ensode.fault.toleranceclient;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/faulttoleranceclient")
public class FaultToleranceClientService {

  private static final Logger LOGGER = Logger.getLogger(FaultToleranceClientService.class.getName());

  @Inject
  @RestClient
  private FaultToleranceExampleResourceClient client;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() throws InterruptedException, ExecutionException {
    Integer answer;
    Integer value1;
    Integer value2;
    String retVal;

    LOGGER.log(Level.INFO, "Invoking slow service endpoint 1");
    CompletionStage<Integer> asynchronousValue = client.getAsynchronousValue();
    LOGGER.log(Level.INFO, "Back from invocation");

    LOGGER.log(Level.INFO, "Invoking slow service endpoint 2");
    CompletionStage<Integer> asynchronousValue2 = client.getAnotherAsynchronousValue();
    LOGGER.log(Level.INFO, "Back from invocation");

    LOGGER.log(Level.INFO, "Getting values, we may have to block");
    value1 = asynchronousValue.toCompletableFuture().get();
    value2 = asynchronousValue2.toCompletableFuture().get();

    answer = value1 + value2;
    retVal = String.format("The answer is %d\n", answer);

    return retVal;
  }

}
