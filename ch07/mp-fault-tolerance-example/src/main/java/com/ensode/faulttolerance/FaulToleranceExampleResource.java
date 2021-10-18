package com.ensode.faulttolerance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Asynchronous;


@RequestScoped
@Path("faulttoleranceexample")
public class FaulToleranceExampleResource {

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

}
