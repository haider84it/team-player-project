package com.haider.playerteam.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;


    @Mock
    private PlayerRepository repository;

    @Mock
    private PlayerMapper playerMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldSuccessfullySaveAPlayer() {

        //Given
        PlayerDto dto = new PlayerDto(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                1
        );

        Player player = new Player(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                23
        );

        Player savedPlayer = new Player(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                23
        );

        savedPlayer.setId(1);

        //Mock the calls
        Mockito.when(playerMapper.toPlayer(dto))
                .thenReturn(player);
        Mockito.when(repository.save(player))
                .thenReturn(savedPlayer);
        Mockito.when(playerMapper.toPlayerResponseDto(savedPlayer))
                .thenReturn(new PlayerResponseDto("Leo", "Laith", "leolaith@gmail.com"));

        //when
        PlayerResponseDto playerResponseDto = playerService.savePlayer(dto);

        //then
        assertEquals(dto.firstname(), playerResponseDto.firstname());
        assertEquals(dto.lastname(), playerResponseDto.lastname());
        assertEquals(dto.email(), playerResponseDto.email());

    }


}