package com.ensode.dependencyinjection;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("cdiservice")
public class CdiService {

  @Inject
  private CountryLookup countryLookup;

  private static final Logger LOGGER = Logger.getLogger(CdiService.class.getName());

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Country handleGetRequest(@QueryParam("countryAbbrev") String countryAbbrev) {
    return countryLookup.getCountry(countryAbbrev);
  }

}
