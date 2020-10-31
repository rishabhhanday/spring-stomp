package com.poc.stomp.configuration.security;

import static com.poc.stomp.configuration.Constant.PUB_TEAMS_CREATE;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketAuthorizationSecurityConfig extends
    AbstractSecurityWebSocketMessageBrokerConfigurer {

  @Override
  protected void configureInbound(final MessageSecurityMetadataSourceRegistry messages) {
    messages
        .simpMessageDestMatchers("/game/" + PUB_TEAMS_CREATE)
        .authenticated()
        .anyMessage()
        .permitAll();
  }

  @Override
  protected boolean sameOriginDisabled() {
    return true;
  }
}


