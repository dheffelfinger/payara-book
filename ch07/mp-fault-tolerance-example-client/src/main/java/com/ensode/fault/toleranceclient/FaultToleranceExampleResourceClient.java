package com.ensode.fault.toleranceclient;

import java.util.concurrent.CompletionStage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("faulttoleranceexample")
public interface FaultToleranceExampleResourceClient {

  @Asynchronous
  @GET
  @Path("async")
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<Integer> getAsynchronousValue() throws InterruptedException;

  @Asynchronous
  @GET
  @Path("async2")
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<Integer> getAnotherAsynchronousValue() throws InterruptedException;

  @POST
  @Path("semaphorebulkhead")
  @Bulkhead(3)
  @Produces(MediaType.TEXT_PLAIN)
  public String semaphoreBulkHeadDemo() throws InterruptedException;

  @POST
  @Path("threadpoolbulkhead")
  @Bulkhead(value = 3, waitingTaskQueue = 2)
  @Produces(MediaType.TEXT_PLAIN)
  public CompletionStage<String> threadPoolBulkheadExample(@QueryParam("invocationNum") int invocationNum) throws InterruptedException;
}