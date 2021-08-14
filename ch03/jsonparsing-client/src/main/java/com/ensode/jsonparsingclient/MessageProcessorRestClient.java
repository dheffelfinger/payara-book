package com.ensode.jsonparsingclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MessageProcessorRestClient {

  private static final Logger LOGGER = Logger.getLogger(MessageProcessorRestClient.class.getName());

  @Inject
  @RestClient
  private MessageProcessorClient messageProcessorClient;

  public void init(@Observes @Initialized(ApplicationScoped.class) Object object) {

    Message message = new Message();
    Message returnedMessage;

    message.setMsgText("Hello from the MicroProfile REST client!");

    returnedMessage = messageProcessorClient.processMessage(message);

    LOGGER.log(Level.INFO, String.format("Server returned the following message: %s", returnedMessage.getMsgText()));
  }
}
