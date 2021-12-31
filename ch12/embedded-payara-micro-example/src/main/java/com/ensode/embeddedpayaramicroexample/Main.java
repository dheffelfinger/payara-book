package com.ensode.embeddedpayaramicroexample;

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;
import java.io.File;

public class Main {

  public void startPayaraMicro() throws BootstrapException {
    File warFile = new File("/path/to/some.war");
    PayaraMicro.getInstance().setHttpAutoBind(true).bootStrap().deploy("applicationname", "contextroot", warFile);
  }

  public static void main(String[] args) throws BootstrapException {
    new Main().startPayaraMicro();
  }

}
