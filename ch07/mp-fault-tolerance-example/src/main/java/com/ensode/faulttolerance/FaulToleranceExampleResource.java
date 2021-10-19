package com.ensode.faulttolerance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
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

@RequestScoped
@Path("faulttoleranceexample")
public class FaulToleranceExampleResource {

  @Inject
  private ConcurrentInvocationCounter concurrentInvocationCounter;

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

}
