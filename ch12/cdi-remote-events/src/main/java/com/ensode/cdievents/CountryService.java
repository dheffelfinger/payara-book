package com.ensode.cdievents;

import com.ensode.cdievents.qualifier.Deleted;
import com.ensode.cdievents.qualifier.Updated;
import fish.payara.micro.cdi.Outbound;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("countryservice")
public class CountryService {

  @Inject
  private @Updated @Outbound
  Event<Country> countryEvent;

  @Inject
  private @Deleted @Outbound
  Event<Country> countryDeletedEvent;

  @Inject
  private CountryLookup countryLookup;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Country handleGetRequest(@QueryParam("countryAbbrev") String countryAbbrev) {
    return countryLookup.getCountry(countryAbbrev);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateCountry(Country country) {
    countryEvent.fire(country);
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  public void deleteCountry(@QueryParam("countryAbbrev") String countryAbbrev) {
    Country country=countryLookup.getCountry(countryAbbrev);
    countryDeletedEvent.fire(country);
  }

}
