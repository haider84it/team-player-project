package com.haider.playerteam.team;

import org.springframework.stereotype.Service;

@Service
public class TeamMapper {

    public Team toTeam(TeamDto dto) {
        return new Team(dto.name());
    }

    public TeamDto toTeamDto(Team team) {
        return new TeamDto(team.getName());
    }
}
