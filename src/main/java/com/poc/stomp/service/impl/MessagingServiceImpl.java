package com.poc.stomp.service.impl;

import com.poc.stomp.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl implements MessagingService {

  @Autowired
  SimpMessagingTemplate template;

  private MessageHeaders createMessageHeader(String sessionId) {
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
        .create(SimpMessageType.MESSAGE);
    headerAccessor.setSessionId(sessionId);
    headerAccessor.setLeaveMutable(true);
    return headerAccessor.getMessageHeaders();
  }

  @Override
  public void sendPayload(String sessionId, String destination, Object payload) {
    template.convertAndSendToUser(sessionId, destination, payload, createMessageHeader(sessionId));
  }
}
