package com.haider.playerteam.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerMapperTest {

    private PlayerMapper mapper;

    @BeforeEach
    void setUP() {
       mapper = new PlayerMapper();
    }

    @Test
    public void shouldMapPlayerDtoToPlayer() {

        PlayerDto dto = new PlayerDto(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                1
        );

        Player player = mapper.toPlayer(dto);

        assertEquals(dto.firstname(), player.getFirstname());
        assertEquals(dto.lastname(), player.getLastname());
        assertEquals(dto.email(), player.getEmail());
        assertNotNull(player.getTeam());
        assertEquals(dto.teamId(), player.getTeam().getId());
    }


    @Test
    public void shouldMapPlayerToPlayerResponseDto() {
        //Given
        Player player = new Player(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                1
        );
        //when
        PlayerResponseDto playerResponseDto = mapper.toPlayerResponseDto(player);

        //then
        assertEquals(player.getFirstname(), playerResponseDto.firstname());
        assertEquals(player.getLastname(), playerResponseDto.lastname());
        assertEquals(player.getEmail(), playerResponseDto.email());

    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPlayerDtoIsNull() {
        var exp = assertThrows(NullPointerException.class, () -> mapper.toPlayer(null));
        assertEquals("The player Dto is null", exp.getMessage());
    }

}