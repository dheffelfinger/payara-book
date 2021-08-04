package com.ensode.microservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 */
@Path("sample")
public class SampleRestFulService {

  @Context
  private UriInfo context;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getJson() {

    String json = "{"
            + "\"msg\":\"Hello, world!\""
            + "}";

    return json;
  }
}
