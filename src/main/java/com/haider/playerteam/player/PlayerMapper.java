package com.haider.playerteam.player;

import com.haider.playerteam.team.Team;
import org.springframework.stereotype.Service;

@Service
public class PlayerMapper {

    public Player toPlayer(PlayerDto dto) {
        if (dto == null) {
            throw new NullPointerException("The player Dto is null");
        }
        var player = new Player();
        player.setFirstname(dto.firstname());
        player.setLastname(dto.lastname());
        player.setEmail(dto.email());

        var team = new Team();
        team.setId(dto.teamId());

        player.setTeam(team);

        return player;
    }

    public PlayerResponseDto toPlayerResponseDto(Player player) {
        return new PlayerResponseDto(
                player.getFirstname(),
                player.getLastname(),
                player.getEmail());
    }
}
