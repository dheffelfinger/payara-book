package com.ensode.healthcheck;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
public class ReadinessChecker implements HealthCheck {

  HealthCheckResponseBuilder healthCheckResponseBuilder = HealthCheckResponse.builder().name(this.getClass().getSimpleName());

  @Override
  public HealthCheckResponse call() {

    boolean databaseUp = isDatabaseUp();
    boolean jmsQueuesUp = areJmsQueuesUp();

    if (databaseUp && jmsQueuesUp) {
      healthCheckResponseBuilder = healthCheckResponseBuilder.up();
    } else {
      healthCheckResponseBuilder = healthCheckResponseBuilder.down();
    }

    return healthCheckResponseBuilder.withData("Database up", databaseUp).withData("JMS queue up", jmsQueuesUp).build();
  }

  private boolean isDatabaseUp() {
    //dummy method for illustration purposes
    return new Random().nextBoolean();
  }

  private boolean areJmsQueuesUp() {
    //dummy method for illustration purposes
    return new Random().nextBoolean();
  }

}
