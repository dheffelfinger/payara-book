package com.ensode.openapiexample.client;

import java.util.Objects;

public class Employee {

  public Employee() {
  }

  public Employee(Integer employeeId, String firstName, String lastName) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private Integer employeeId;
  private String firstName;
  private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 47 * hash + Objects.hashCode(this.employeeId);
    hash = 47 * hash + Objects.hashCode(this.firstName);
    hash = 47 * hash + Objects.hashCode(this.lastName);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Employee other = (Employee) obj;
    if (!Objects.equals(this.firstName, other.firstName)) {
      return false;
    }
    if (!Objects.equals(this.lastName, other.lastName)) {
      return false;
    }
    if (!Objects.equals(this.employeeId, other.employeeId)) {
      return false;
    }
    return true;
  }

}
