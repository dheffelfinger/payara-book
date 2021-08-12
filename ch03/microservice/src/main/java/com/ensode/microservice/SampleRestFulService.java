package com.ensode.microservice;

import com.ensode.pojo.DummyPojo;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 */
@Path("sample")
public class SampleRestFulService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String processGetRequest() {

    String json = "{"
            + "\"msg\":\"Service processed HTTP GET request!\""
            + "}";

    return json;
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  public String processPutRequest(DummyPojo dummyPojo) {

    String json = "{"
            + "\"msg\":\"Service processed HTTP PUT request!\""
            + "}";

    return json;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String processPostRequest() {

    String json = "{"
            + "\"msg\":\"Service processed HTTP POST request!\""
            + "}";

    return json;
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public String processDeleteRequest() {

    String json = "{"
            + "\"msg\":\"Service processed HTTP DELETE request!\""
            + "}";

    return json;
  }

  @PATCH
  @Produces(MediaType.APPLICATION_JSON)
  public String processPatchRequest(DummyPojo dummyPojo) {

    String json = "{"
            + "\"msg\":\"Service processed HTTP PATCH request!\""
            + "}";

    return json;
  }
}
