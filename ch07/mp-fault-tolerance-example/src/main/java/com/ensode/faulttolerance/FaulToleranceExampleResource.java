package com.ensode.faulttolerance;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@RequestScoped
@Path("faulttoleranceexample")
public class FaulToleranceExampleResource {

  private static final Logger LOGGER = Logger.getLogger(FaulToleranceExampleResource.class.getName());

  @Inject
  private ConcurrentInvocationCounter concurrentInvocationCounter;

  @Inject
  private EndpointSuccessDeterminator endpointSuccessDeterminator;

  @Asynchronous
  @GET
  @Path("async")
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<Integer> getAsynchronousValue() throws InterruptedException {
    TimeUnit.SECONDS.sleep(5);
    return CompletableFuture.completedStage(18);
  }

  @Asynchronous
  @GET
  @Path("async2")
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<Integer> getAnotherAsynchronousValue() throws InterruptedException {
    TimeUnit.SECONDS.sleep(7);
    return CompletableFuture.completedStage(24);
  }

  @POST
  @Path("semaphorebulkhead")
  @Bulkhead(3)
  @Produces(MediaType.TEXT_PLAIN)
  public String semaphoreBulkHeadDemo() throws InterruptedException {

    String retVal;
    concurrentInvocationCounter.increaseCounter();

    retVal = String.format("There are %d concurrent invocations to this endpoint \n", concurrentInvocationCounter.getCounter());

    TimeUnit.SECONDS.sleep(3);

    concurrentInvocationCounter.decreaseCounter();

    return retVal;

  }

  @POST
  @Path("threadpoolbulkhead")
  @Asynchronous
  @Bulkhead(value = 3, waitingTaskQueue = 2)
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<String> threadPoolBulkheadExample(@QueryParam("invocationNum") int invocationNum) throws InterruptedException {
    String retVal;
    retVal = String.format("Invocation number %d succeeded \n",
            invocationNum);

    TimeUnit.SECONDS.sleep(3);

    return CompletableFuture.completedStage(retVal);
  }

  @CircuitBreaker(requestVolumeThreshold = 3, delay = 1, delayUnit = ChronoUnit.SECONDS, failureRatio = .66, successThreshold = 2)
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Path("circuitbreaker")
  public String circuitBreakerExample(@QueryParam("success") boolean success) {
    if (success == false) {
      throw new RuntimeException("forcing a failure for demo purposes");
    } else {
      return "Call succeeded";
    }
  }

  @Fallback(fallbackMethod = "fallbackMethod")
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Path("fallback")
  public String fallbackMethodExample(@QueryParam("success") boolean success) {
    if (success == false) {
      throw new RuntimeException("forcing a failure for demo purposes");
    } else {
      return "Call succeeded";
    }
  }

  private String fallbackMethod(boolean success) {
    return "Something went wrong";
  }

  @Fallback(ExampleFallbackHandler.class)
  @POST
  @Produces(MediaType.TEXT_PLAIN)
  @Path("fallbackhandler")
  public String fallbackHandlerExample(@QueryParam("success") boolean success) {
    if (success == false) {
      throw new RuntimeException("forcing a failure for demo purposes");
    } else {
      return "Call succeeded";
    }
  }

  @Retry
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("retry")
  public String retryExample() {
    LOGGER.log(Level.INFO, "retryExample() invoked");
    boolean success;

    success = endpointSuccessDeterminator.allowEndpointToSucceed();

    if (!success) {
      LOGGER.log(Level.SEVERE, "retryExample() invocation failed");
      throw new RuntimeException("forcing a failure for demo purposes");
    } else {
      LOGGER.log(Level.INFO, "retryExample() invocation succeeded");
      return "Call succeeded\n";
    }
  }

  @Timeout(value = 3, unit = ChronoUnit.SECONDS)
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("timeout")
  public String timeoutExample(@QueryParam("delay") long delay) {

    try {
      TimeUnit.SECONDS.sleep(delay);
    } catch (InterruptedException ex) {
      LOGGER.log(Level.INFO, "sleep() interrupted");
    }

    return "Call returned successfully\n";
  }

}
