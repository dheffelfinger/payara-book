package com.ensode.faulttolerance;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

@Dependent
public class ExampleFallbackHandler implements FallbackHandler<String> {

  private static final Logger LOGGER = Logger.getLogger(ExampleFallbackHandler.class.getName());

  @Override
  public String handle(ExecutionContext ec) {
    Throwable throwable = ec.getFailure();
    Method buggyMethod = ec.getMethod();
    Object[] parameters = ec.getParameters();

    LOGGER.log(Level.SEVERE, String.format("%s thrown when invoking %s method parameters: %s",
            throwable.getClass().getName(), buggyMethod.getName(), Arrays.asList(parameters)));

    return "Something went wrong, check the logs\n";
  }

}
