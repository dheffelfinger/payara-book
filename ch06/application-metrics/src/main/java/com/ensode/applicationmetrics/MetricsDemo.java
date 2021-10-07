package com.ensode.applicationmetrics;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.annotation.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
@Path("metricsdemo")
public class MetricsDemo {

  int gaugeVal = 0;

  /**
   * Creates a new instance of MetricsDemo
   */
  public MetricsDemo() {
  }

  @GET
  @Counted
  @Path("counted")
  @Produces(MediaType.TEXT_PLAIN)
  public String countedExample() {
    return "Counter was just increased\n";
  }

  @GET
  @Gauge(unit = "some unit")
  @Path("gauge")
  @Produces(MediaType.TEXT_PLAIN)
  public int gaugeExample() {
    gaugeVal += 2;
    return gaugeVal;
  }

  @GET
  @ConcurrentGauge
  @Path("concurrentGauge")
  @Produces(MediaType.TEXT_PLAIN)
  public void concurrentGaugeExample() {
    try {
      Thread.sleep(10_000);
    } catch (InterruptedException ex) {
      Logger.getLogger(MetricsDemo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @GET
  @Metered
  @Path("metered")
  @Produces(MediaType.TEXT_PLAIN)
  public String meteredExample() {
    return "Metered method invoked\n";
  }
  
  @GET
  @Timed
  @Path("timed")
  @Produces(MediaType.TEXT_PLAIN)
  public void timedExample() {
    try {
      Thread.sleep(2_000);
    } catch (InterruptedException ex) {
      Logger.getLogger(MetricsDemo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @GET
  @SimplyTimed
  @Path("simplytimed")
  @Produces(MediaType.TEXT_PLAIN)
  public void simplyTimedExample() {
    try {
      Thread.sleep(2_000);
    } catch (InterruptedException ex) {
      Logger.getLogger(MetricsDemo.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
