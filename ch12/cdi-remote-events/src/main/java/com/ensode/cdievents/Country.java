package com.ensode.cdievents;

import java.io.Serializable;

public class Country implements Serializable{

  public Country() {
  }

  public Country(String abbreviation, String name) {
    this.abbreviation = abbreviation;
    this.name = name;
  }

  private String abbreviation;
  private String name;

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
