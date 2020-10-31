package com.poc.stomp.controller;

import static com.poc.stomp.configuration.Constant.SUB_TEAMS;
import static com.poc.stomp.configuration.Constant.SUB_WEATHER;

import com.poc.stomp.model.WeatherResponse;
import com.poc.stomp.model.WeatherResponse.Cloud;
import com.poc.stomp.service.TeamsService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class SubscriptionController {

  @Autowired
  private TeamsService teamsService;

  @SubscribeMapping("/reply/weather")
  @SendToUser(SUB_WEATHER)
  public WeatherResponse getWeather() {
    log.info("getting today's weather | SubscribeMapping=/reply/weather");
    return WeatherResponse.builder().cloud(Cloud.CLOUDY).build();
  }

  @SubscribeMapping("/reply/teams")
  @SendToUser(SUB_TEAMS)
  public List<String> getTeams() {
    log.info("getting today's weather | SubscribeMapping=/reply/teams");
    return teamsService.getTeams();
  }
}
