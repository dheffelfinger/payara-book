package com.ensode.cdiscopes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RequestScoped
@Path("cdiservice")
public class CdiService {

  private static final Logger LOGGER = Logger.getLogger(CdiService.class.getName());

  @PostConstruct
  public void init() {
    LOGGER.log(Level.INFO, "init() method called");
  }

  @PreDestroy
  public void cleanup() {
    LOGGER.log(Level.INFO, "cleanup() method called");
  }

  @POST
  public void handlePostRequest() {
    LOGGER.log(Level.INFO, "HTTP POST request received");
  }

}
