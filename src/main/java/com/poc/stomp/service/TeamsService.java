package com.poc.stomp.service;

import java.util.List;

public interface TeamsService {

  List<String> getTeams();

  void addTeam(String teamName);
}
