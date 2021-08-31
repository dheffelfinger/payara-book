package com.ensode.dependencyinjection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("cdiservice")
public class CdiService {

  @Inject
  private CountryLookup countryLookup;

  private static final Logger LOGGER = Logger.getLogger(CdiService.class.getName());

  @PostConstruct
  public void init() {
    LOGGER.log(Level.INFO, "init() method called");
  }

  @PreDestroy
  public void cleanup() {
    LOGGER.log(Level.INFO, "cleanup() method called");
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Country handlePostRequest(@QueryParam("countryAbbrev") String countryAbbrev) {
    return countryLookup.getCountry(countryAbbrev);
  }

}
