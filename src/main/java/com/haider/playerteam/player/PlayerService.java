package com.haider.playerteam.player;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;


    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerResponseDto savePlayer(
            PlayerDto dto
    ) {
        var player = playerMapper.toPlayer(dto);
        var savedPlayer = playerRepository.save(player);

        return playerMapper.toPlayerResponseDto(savedPlayer);
    }

    public List<PlayerResponseDto> findAllPlayer() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toPlayerResponseDto)
                .collect(Collectors.toList());
    }

    public PlayerResponseDto findPlayerById(Integer id) {
        return playerRepository.findById(id)
                .map(playerMapper::toPlayerResponseDto)
                .orElse(null);
    }

    public List<PlayerResponseDto> findPlayerByName(String name) {
        return this.playerRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(playerMapper::toPlayerResponseDto)
                .collect(Collectors.toList());
    }


    public void delete(Integer id) {
        playerRepository.deleteById(id);

    }


}
