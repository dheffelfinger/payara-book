package com.ensode.cdievents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryLookup {

  private List<Country> countryList;
  private Map<String, Country> countryMap;

  @PostConstruct
  public void init() {
    countryList = new ArrayList<>();
    countryMap = new HashMap<>();

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
