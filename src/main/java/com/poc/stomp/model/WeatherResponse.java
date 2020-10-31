package com.poc.stomp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeatherResponse {

  private Cloud cloud;

  public enum Cloud {
    RAINY, SUNNY, CLOUDY
  }
}
