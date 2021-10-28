package com.ensode.healthcheck;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
@Liveness
public class LivenessChecker implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.builder().name(this.getClass().getSimpleName()).up().build();
  }

}
