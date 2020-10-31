package com.poc.stomp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Builder
public class OverResponse {

  public static final String SUMMARY = "Player %s from team %s played well.";
  private int totalRuns;
  private String summary;

}
