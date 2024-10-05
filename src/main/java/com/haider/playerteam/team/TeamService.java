package com.haider.playerteam.team;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    public TeamService(TeamMapper teamMapper, TeamRepository teamRepository) {
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
    }

    public TeamDto create(TeamDto teamDto) {
        var team = teamMapper.toTeam(teamDto);
        teamRepository.save(team);
        return teamDto;
    }

    public List<TeamDto> findAll() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toTeamDto)
                .collect(Collectors.toList());
    }

}
