package com.ensode.clusteredcdiappscopedbeans;

import javax.enterprise.context.ApplicationScoped;
import fish.payara.cluster.Clustered;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//need to add payara-api as a dependency
@Clustered
@ApplicationScoped
//Clustered application scoped bean must be serializable
public class DataCache implements Serializable {

  private Map<String, String> cachedValueMap = new HashMap<>();

  public void addMapEntry(String key, String value) {
    cachedValueMap.put(key, value);
  }

  public String retrieveValue(String key) {
    return cachedValueMap.get(key);
  }

  public Map<String, String> getCachedValueMap() {
    return cachedValueMap;
  }

  public void setCachedValueMap(Map<String, String> cachedValueMap) {
    this.cachedValueMap = cachedValueMap;
  }

}
