package com.poc.stomp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScoreResponse {

  private int ball;
  private int runs;
}
