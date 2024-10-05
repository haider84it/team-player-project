package com.haider.playerteam.team;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {


  private final TeamService teamService;

  public TeamController(TeamService teamService){
      this.teamService = teamService;
  }


    @PostMapping("/teams")
    public TeamDto create(@RequestBody TeamDto teamDto) {
      return teamService.create(teamDto);
    }

    @GetMapping("/teams")
    public List<TeamDto> findAll() {
        return teamService.findAll();
    }


}
