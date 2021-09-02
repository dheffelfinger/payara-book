package com.ensode.cdievents;

import com.ensode.cdievents.qualifier.Deleted;
import com.ensode.cdievents.qualifier.Updated;
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

  public void updateCountry(@Observes @Updated Country country) {
    LOGGER.log(Level.INFO, String.format("Updating the following country: %s", country.getName()));
    countryLookup.updateCountry(country);
  }

  public void deleteCountry(@Observes @Deleted Country country) {
    LOGGER.log(Level.INFO, String.format("Deleting the following country: %s", country.getName()));
    countryLookup.deleteCountry(country);
  }
}
