package com.poc.stomp.service.impl;

import com.poc.stomp.service.TeamsService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeamsServiceImpl implements TeamsService {

  private List<String> teams = new ArrayList<>(Arrays.asList("INDIA", "AUSTRALIA"));

  @Override
  public List<String> getTeams() {
    return teams;
  }

  @Override
  public void addTeam(String teamName) {
    teams.add(teamName);
  }
}
