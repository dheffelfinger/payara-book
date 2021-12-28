package com.ensode.clusteredcdiappscopedbeans;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("cachedvalueaccessor")
public class CachedValueAccessorResource {

  @Inject
  private DataCache dataCache;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getCachedValue(@QueryParam("key") String key) {
    return dataCache.retrieveValue(key);
  }

  @PUT
  @Consumes(MediaType.TEXT_PLAIN)
  public void addCachedValue(@QueryParam("key") String key, @QueryParam("value") String value) {
    dataCache.addMapEntry(key, value);
  }
}
