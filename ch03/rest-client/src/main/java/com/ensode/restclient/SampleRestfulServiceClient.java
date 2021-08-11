package com.ensode.restclient;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("sample")
@RegisterRestClient
public interface SampleRestfulServiceClient {

  @DELETE
  @Produces(value = MediaType.APPLICATION_JSON)
  String processDeleteRequest();

  @GET
  @Produces(value = MediaType.APPLICATION_JSON)
  String processGetRequest();

  @PATCH
  @Produces(value = MediaType.APPLICATION_JSON)
  String processPatchRequest();

  @POST
  @Produces(value = MediaType.APPLICATION_JSON)
  String processPostRequest();

  @PUT
  @Produces(value = MediaType.APPLICATION_JSON)
  String processPutRequest();

}
