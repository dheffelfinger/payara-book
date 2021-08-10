package com.ensode.jsonparsing;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("messageprocessor")
public class MessageProcessor {

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Message processMessage(Message message) {
    Message returnedMessage = new Message();
    
    returnedMessage.setMsgText(message.getMsgText().toUpperCase());
    
    return returnedMessage;
  }
}
