package com.ensode.fault.toleranceclient;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

  @POST
  @Path("semaphorebulkhead")
  public void semaphoreBulkheadClient() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    Callable<String> semaphoreBulkheadCallable = () -> client.semaphoreBulkHeadDemo();

    List<Future<String>> callResults = executorService.invokeAll(
            List.of(semaphoreBulkheadCallable, semaphoreBulkheadCallable, semaphoreBulkheadCallable, semaphoreBulkheadCallable));

    callResults.forEach(fut -> {
      try {
        LOGGER.log(Level.INFO, fut.get());
      } catch (InterruptedException | ExecutionException ex) {
        LOGGER.log(Level.SEVERE, String.format("%s caught", ex.getClass().getName()), ex);
      }
    });
  }

  @POST
  @Path("threadpoolbulkhead")
  public void threadpoolBulkheadClient() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(6);

    List<Future<CompletionStage<String>>> callResults = executorService.invokeAll(
            List.of(() -> client.threadPoolBulkheadExample(1),
                    () -> client.threadPoolBulkheadExample(2),
                    () -> client.threadPoolBulkheadExample(3),
                    () -> client.threadPoolBulkheadExample(4),
                    () -> client.threadPoolBulkheadExample(5),
                    () -> client.threadPoolBulkheadExample(6)));

    callResults.forEach(fut -> {
      try {
        LOGGER.log(Level.INFO, fut.get().toCompletableFuture().get());
      } catch (InterruptedException | ExecutionException ex) {
        LOGGER.log(Level.SEVERE, String.format("%s caught", ex.getClass().getName()), ex);
      }
    });
  }

}
