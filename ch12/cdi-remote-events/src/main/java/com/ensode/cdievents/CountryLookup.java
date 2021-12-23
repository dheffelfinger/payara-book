package com.ensode.cdievents;

import fish.payara.cluster.Clustered;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@Clustered
@ApplicationScoped
public class CountryLookup implements Serializable {

  private static final Logger LOGGER = Logger.getLogger(CountryLookup.class.getName());

  private List<Country> countryList;
  private Map<String, Country> countryMap;

  public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
    countryList = new ArrayList<>();
    countryMap = new HashMap<>();

    LOGGER.log(Level.INFO, "Adding countries to the map");
    countryList.add(new Country("AU", "Australia"));
    countryList.add(new Country("UK", "United Kingdom"));
    countryList.add(new Country("IN", "India"));
    countryList.add(new Country("US", "United States"));
    countryList.add(new Country("MX", "Mexico"));
    countryList.add(new Country("EG", "Egypt"));

    countryList.forEach(c -> countryMap.put(c.getAbbreviation(), c));
  }

  public Country getCountry(String countryAbbrev) {
    return countryMap.get(countryAbbrev);
  }

  public void updateCountry(Country country) {
    countryMap.put(country.getAbbreviation(), country);
  }

  public void deleteCountry(Country country) {
    countryMap.remove(country.getAbbreviation());
  }

}
