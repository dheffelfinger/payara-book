package com.ensode.jwtdemo;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
@Path("jwtdemo")
public class JwtDemoResource {

  private static final Logger LOGGER = Logger.getLogger(JwtDemoResource.class.getName());

  @Inject
  private JsonWebToken jsonWebToken;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @RolesAllowed({"chief"})
  public String secured() {

    LOGGER.log(Level.INFO, String.format("Audience: %s", jsonWebToken.getAudience()));
    LOGGER.log(Level.INFO, String.format("Expiration Time: %s", jsonWebToken.getExpirationTime()));
    LOGGER.log(Level.INFO, String.format("Groups: %s", jsonWebToken.getGroups()));
    LOGGER.log(Level.INFO, String.format("Issued at time: %s", jsonWebToken.getIssuedAtTime()));
    LOGGER.log(Level.INFO, String.format("Issuer: %s", jsonWebToken.getIssuer()));
    LOGGER.log(Level.INFO, String.format("Name: %s", jsonWebToken.getName()));
    LOGGER.log(Level.INFO, String.format("Raw Token: %s", jsonWebToken.getRawToken()));
    LOGGER.log(Level.INFO, String.format("Subject: %s", jsonWebToken.getSubject()));
    LOGGER.log(Level.INFO, String.format("Token ID: %s", jsonWebToken.getTokenID()));

    Set<String> claimNames = jsonWebToken.getClaimNames();
    
    LOGGER.log(Level.INFO, "--- Begin Token Claims");
    claimNames.forEach(claimName -> LOGGER.log(Level.INFO,
            String.format("%s: %s", claimName,
                    jsonWebToken.claim(claimName).orElse(""))));
    LOGGER.log(Level.INFO, "--- End Token Claims");

    return "Secured endpoint accessed successfully\n";
  }
}
