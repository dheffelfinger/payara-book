package com.ensode.faulttolerance;

import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConcurrentInvocationCounter {

  private AtomicInteger counter = new AtomicInteger(0);

  private AtomicInteger threadPoolInvocationCounter = new AtomicInteger(0);

  public void increaseCounter() {
    counter.incrementAndGet();
  }

  public void decreaseCounter() {
    counter.decrementAndGet();
  }

  public void increaseThreadPoolCounter() {
    threadPoolInvocationCounter.incrementAndGet();
  }

  public void decreaseThreadPoolCounter() {
    threadPoolInvocationCounter.decrementAndGet();
  }

  public int getCounter() {
    return counter.get();
  }

  public int getThradPoolInvocationCounter() {
    return threadPoolInvocationCounter.get();
  }

}
