package com.ensode.faulttolerance;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EndpointSuccessDeterminator {

  private boolean successIndicator = true;

  public boolean allowEndpointToSucceed() {
    successIndicator = !successIndicator;

    return successIndicator;
  }

}
