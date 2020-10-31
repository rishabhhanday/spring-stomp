package com.poc.stomp.controller;

import static com.poc.stomp.configuration.Constant.PUB_START;
import static com.poc.stomp.configuration.Constant.PUB_TEAMS_CREATE;
import static com.poc.stomp.configuration.Constant.SUB_OVER;
import static com.poc.stomp.configuration.Constant.SUB_SCORE;
import static com.poc.stomp.configuration.Constant.SUB_TEAMS;
import static com.poc.stomp.model.OverResponse.SUMMARY;
import static java.lang.String.format;

import com.poc.stomp.exception.InvalidTeamException;
import com.poc.stomp.model.CreateTeamRequest;
import com.poc.stomp.model.OverResponse;
import com.poc.stomp.model.ScoreResponse;
import com.poc.stomp.model.StartGame;
import com.poc.stomp.service.GameService;
import com.poc.stomp.service.MessagingService;
import com.poc.stomp.service.TeamsService;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@MessageMapping("/game")
public class StompController {

  private final MessagingService messagingService;
  private final GameService gameService;
  private final TeamsService teamsService;

  @Autowired
  public StompController(MessagingService messagingService, TeamsService teamsService) {
    this.messagingService = messagingService;
    this.gameService = () -> ThreadLocalRandom.current().nextInt(0, 7);
    this.teamsService = teamsService;
  }

  @MessageMapping(PUB_START)
  void startGame(StartGame startGame, SimpMessageHeaderAccessor accessor, Principal principal)
      throws InterruptedException {
    log.info("Game begin | MessageMapping=/game/start");

    if (!teamsService.getTeams().contains(startGame.getTeamName())) {
      throw new InvalidTeamException(startGame.getTeamName() + " is not a from allowed teams");
    }

    int totalScore = 0;
    for (int ball = 1; ball <= 6; ball++) {
      int runs = gameService.generateRandomRun();
      totalScore += runs;

      Thread.sleep(2000);
      messagingService.sendPayload(
          accessor.getSessionId(),
          SUB_SCORE,
          ScoreResponse.builder().ball(ball).runs(runs).build());
    }

    messagingService.sendPayload(
        accessor.getSessionId(),
        SUB_OVER,
        OverResponse
            .builder()
            .totalRuns(totalScore)
            .summary(format(SUMMARY, startGame.getPlayerName(), startGame.getTeamName()))
            .build());
  }

  @MessageMapping(PUB_TEAMS_CREATE)
  @SendToUser(SUB_TEAMS)
  List<String> createTeam(CreateTeamRequest createTeamRequest) {
    log.info("Game begin | MessageMapping=/game/teams/create");
    teamsService.addTeam(createTeamRequest.getTeamName());
    return teamsService.getTeams();
  }
}
