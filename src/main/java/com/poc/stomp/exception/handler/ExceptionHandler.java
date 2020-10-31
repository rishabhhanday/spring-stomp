package com.poc.stomp.exception.handler;


import static com.poc.stomp.configuration.Constant.SUB_ERROR;

import com.poc.stomp.exception.reponse.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

  @MessageExceptionHandler
  @SendToUser(SUB_ERROR)
  public ErrorResponse handleException(Exception exception) {

    log.error(exception.getMessage() + " | messageExceptionHandler | controllerAdvice");
    return ErrorResponse.builder().errorMessage(exception.getMessage()).build();
  }
}
