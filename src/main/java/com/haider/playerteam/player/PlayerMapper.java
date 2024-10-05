package com.haider.playerteam.player;

import com.haider.playerteam.team.Team;
import org.springframework.stereotype.Service;

@Service
public class PlayerMapper {

    public PlayerResponseDto toPlayerResponseDto(Player player) {
        return new PlayerResponseDto(
                player.getFirstname(),
                player.getLastname(),
                player.getEmail());
    }

    public Player toPlayer(PlayerDto dto) {
        var player = new Player();
        player.setFirstname(dto.firstname());
        player.setLastname(dto.lastname());
        player.setEmail(dto.email());

        var team = new Team();
        team.setId(dto.teamId());

        player.setTeam(team);

        return player;
    }
}
