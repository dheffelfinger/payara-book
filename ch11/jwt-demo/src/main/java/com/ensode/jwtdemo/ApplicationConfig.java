package com.ensode.jwtdemo;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

/**
 *
 * @author heffel
 */
//Application class needs to be made a singleton for @LoginConfig to work
@Singleton
@ApplicationPath("webresources")
//pom.xml dependencies need to be modified to gain access to this annotation
@LoginConfig(authMethod = "MP-JWT")
public class ApplicationConfig extends Application {

}
