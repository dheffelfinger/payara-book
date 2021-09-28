package com.ensode.mpconfigcustomconverters;

import org.eclipse.microprofile.config.spi.Converter;

public class PersonConverter implements Converter<Person> {

  @Override
  public Person convert(String value) throws IllegalArgumentException, NullPointerException {
    Person person = new Person();
    String[] nameArr = value.split(" ");

    person.setFirstName(nameArr[0]);
    person.setLastName(nameArr[1]);

    return person;
  }

}
