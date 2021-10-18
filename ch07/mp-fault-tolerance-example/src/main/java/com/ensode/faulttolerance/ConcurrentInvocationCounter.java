package com.ensode.faulttolerance;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConcurrentInvocationCounter {

  int counter = 0;

  public void increaseCounter() {
    counter++;
  }

  public void decreaseCounter() {
    counter--;
  }

  public int getCounter() {
    return counter;
  }

}
