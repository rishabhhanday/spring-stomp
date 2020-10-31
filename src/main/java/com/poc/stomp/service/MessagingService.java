package com.poc.stomp.service;

public interface MessagingService {

  void sendPayload(String sessionId, String destination, Object payload);
}
