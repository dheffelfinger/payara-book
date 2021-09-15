package com.ensode.mp.config.example;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
@Path("mpconfigexample")
public class MpConfigDemoService {

  private static final Logger LOGGER = Logger.getLogger(MpConfigDemoService.class.getName());

  @Inject
  @ConfigProperty(name = "project.stage")
  private String projectStage;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public void processPostRequest() {
    LOGGER.log(Level.INFO, String.format("Project stage is: %s", projectStage));

    ProjectStageEnum projectStageEnum = ProjectStageEnum.valueOf(projectStage.toUpperCase());

    if (ProjectStageEnum.DEVELOPMENT.equals(projectStageEnum)) {
      LOGGER.log(Level.INFO, "processPostRequest() method invoked");
    }

    if (ProjectStageEnum.DEVELOPMENT.equals(projectStageEnum)) {
      LOGGER.log(Level.INFO, "leaving processPostRequest() method");
    }

  }

}
