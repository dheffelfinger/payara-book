package com.ensode.cdi.remote.event.observer;

import com.ensode.cdievents.Country;
import fish.payara.micro.cdi.Inbound;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class CountryEventLogger {

  private static final Logger LOGGER = Logger.getLogger(CountryEventLogger.class.getName());

  public void logCountryEvent(@Observes @Inbound Country country) {
    LOGGER.log(Level.INFO, String.format("Event fired for the following country: %s", country.getName()));
  }

}
