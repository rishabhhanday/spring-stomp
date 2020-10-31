package com.poc.stomp.configuration.security;

import java.util.Collections;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

  private static final String PASSCODE = "rishabhhanday";

  @Override
  public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
    final StompHeaderAccessor accessor = MessageHeaderAccessor
        .getAccessor(message, StompHeaderAccessor.class);

    log.info("interceptor | preSend=" + accessor.getCommand());
    if (accessor != null && StompCommand.CONNECT == accessor.getCommand()) {
      String passcode = accessor.getFirstNativeHeader("authorization");

      if (passcode != null && passcode.equals(PASSCODE)) {
        accessor.setUser(
            new PreAuthenticatedAuthenticationToken(accessor.getSessionId(), UUID.randomUUID(),
                Collections.emptyList()));
      }
    }

    return message;
  }
}
