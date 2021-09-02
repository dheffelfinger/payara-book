package com.ensode.cdievents.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("cdiservice")
public interface CdiService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Country handleGetRequest(@QueryParam("countryAbbrev") String countryAbbrev);

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public void updateCountry(Country country);

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  public void deleteCountry(@QueryParam("countryAbbrev") String countryAbbrev);

}
