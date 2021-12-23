package com.ensode.cdi.remote.event.observer;

import com.ensode.cdievents.Country;
import com.ensode.cdievents.CountryLookup;
import com.ensode.cdievents.qualifier.Deleted;
import com.ensode.cdievents.qualifier.Updated;
import fish.payara.micro.cdi.Inbound;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class CountryManager {

  private static final Logger LOGGER = Logger.getLogger(CountryManager.class.getName());
  
  @Inject
  private CountryLookup countryLookup;

  public void updateCountry(@Observes @Inbound @Updated Country country) {
    LOGGER.log(Level.INFO, String.format("Updating the following country: %s", country.getName()));
    countryLookup.updateCountry(country);
  }

  public void deleteCountry(@Observes @Inbound @Deleted Country country) {
    LOGGER.log(Level.INFO, String.format("Deleting the following country: %s", country.getName()));
    countryLookup.deleteCountry(country);
  }
}
