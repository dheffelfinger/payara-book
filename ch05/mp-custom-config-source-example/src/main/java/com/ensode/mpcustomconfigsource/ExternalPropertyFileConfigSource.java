package com.ensode.mpcustomconfigsource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.microprofile.config.spi.ConfigSource;

public class ExternalPropertyFileConfigSource implements ConfigSource {

  private static final Logger LOGGER = Logger.getLogger(ExternalPropertyFileConfigSource.class.getName());

  private static final String CONFIG_SOURCE_NAME = "ExternalPropFile";

  private Map propertyMap;

  public ExternalPropertyFileConfigSource() throws IOException {
    Properties properties = new Properties();
    String homeDir = System.getProperty("user.home");
    String configFilePath = String.format("%s/config/config.properties", homeDir);
    InputStream inputStream;

    try {
      inputStream = new FileInputStream(configFilePath);
      properties.load(inputStream);
    } catch (FileNotFoundException ex) {
      LOGGER.log(Level.SEVERE, String.format("%s not found!", configFilePath), ex);
    }

    propertyMap = (Map) properties;

  }

  @Override
  public Set<String> getPropertyNames() {
    return propertyMap.keySet();
  }

  @Override
  public String getValue(String propName) {

    String retVal = null;

    var propVal = propertyMap.get(propName);

    if (propVal != null) {
      retVal = propVal.toString();
    }

    return retVal;
  }

  @Override
  public String getName() {
    return CONFIG_SOURCE_NAME;
  }
  
}
