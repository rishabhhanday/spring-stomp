package com.poc.stomp.exception.reponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ErrorResponse {

  private String errorMessage;
}
