package com.ensode.cdievents.client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CdiServiceClient {
  
  @Inject
  @RestClient
  private CdiService cdiService;
  
  public void updateCountries(@Observes @Initialized(ApplicationScoped.class) Object object){
    Country testCountry = new Country("TS", "Test");
    Country albanya = new Country("AL", "Albanya");
    Country albania = new Country("AL", "Albania");
    
    cdiService.updateCountry(testCountry);
    cdiService.deleteCountry(testCountry.getAbbreviation());
    cdiService.updateCountry(albanya);
    cdiService.updateCountry(albania);
    
  }
}
