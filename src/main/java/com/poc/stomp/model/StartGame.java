package com.poc.stomp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StartGame {

  private String teamName;
  private String playerName;
}
