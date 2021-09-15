package com.ensode.mp.config.example;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class ConfigLogger {

  private static final Logger LOGGER = Logger.getLogger(ConfigLogger.class.getName());
  
  

  public void displayConfig(@Observes @Initialized(javax.faces.bean.ApplicationScoped.class) Object object) {
     LOGGER.info("--begin config sources");
    ConfigProvider.getConfig().getConfigSources().forEach(s -> {
      LOGGER.log(Level.INFO, s.getName());
      LOGGER.log(Level.INFO, s.getProperties().toString());
      LOGGER.log(Level.INFO, "\n");
    });
    LOGGER.info("--end config sources\n");
  }
}
