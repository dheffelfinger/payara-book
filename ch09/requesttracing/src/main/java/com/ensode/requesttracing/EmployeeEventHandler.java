package com.ensode.requesttracing;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.eclipse.microprofile.opentracing.Traced;

@ApplicationScoped
public class EmployeeEventHandler {

  private static final Logger LOGGER = Logger.getLogger(EmployeeEventHandler.class.getName());

  @Traced
  public void handleEmployeeEvent(@Observes Employee employee) {
    try {
      Thread.sleep(100);
    } catch (InterruptedException ex) {
      Logger.getLogger(EmployeeEventHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
    LOGGER.log(Level.INFO, String.format("Hired %s %s", employee.getFirstName(), employee.getLastName()));
  }
}
