package com.haider.playerteam.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        verify(playerMapper, times(1))
                .toPlayer(dto);
        verify(repository, times(1))
                .save(player);
        verify(playerMapper, times(1))
                .toPlayerResponseDto(savedPlayer);

    }


    @Test
    public void shouldFindAllPlayer() {
        //Given
        List<Player> players = new ArrayList<>();
        players.add(new Player(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                23
        ));


        //Mock the calls
       Mockito.when(repository.findAll()).thenReturn(players);
       Mockito.when(playerMapper.toPlayerResponseDto(any(Player.class)))
               .thenReturn(new PlayerResponseDto(
                       "Leo",
                       "Laith",
                       "leolaith@gmail.com")
                       );

       //when
        List<PlayerResponseDto> responseDtos = playerService.findAllPlayer();

       //then
        assertEquals(players.size(), responseDtos.size());

        verify(repository, times(1)).findAll();


    }

    @Test
    public void shouldReturnPlayerById() {
        //Given
        int studentId = 1;
        Player savedPlayer = new Player(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                23);

        PlayerResponseDto playerResponseDto = new PlayerResponseDto(
                "Leo",
                "Laith",
                  "leolaith@gmail.com");



        //Mock the calls
        Mockito.when(repository.findById(studentId))
                .thenReturn(Optional.of(savedPlayer));
        Mockito.when(playerMapper.toPlayerResponseDto(any(Player.class)))
                .thenReturn(playerResponseDto);
        //when
        PlayerResponseDto responseDto = playerService.findPlayerById(studentId);


        //then
        assertEquals(responseDto.firstname(), savedPlayer.getFirstname());
        assertEquals(responseDto.lastname(), savedPlayer.getLastname());
        assertEquals(responseDto.email(), savedPlayer.getEmail());

        verify(repository, times(1)).findById(studentId);
        verify(playerMapper, times(1)).toPlayerResponseDto(savedPlayer);

    }

    @Test
    public void shouldFindPlayerByName() {
        //Given
        String firstname = "Leo";
        List<Player> players = new ArrayList<>();
        players.add(new Player(
                "Leo",
                "Laith",
                "leolaith@gmail.com",
                23
        ));

        //Mock the calls
        Mockito.when(repository.findAllByFirstnameContaining(firstname))
            .thenReturn(players);
        Mockito.when(playerMapper.toPlayerResponseDto(any(Player.class)))
                .thenReturn(new PlayerResponseDto(
                        "Leo",
                        "Laith",
                        "leolaith@gmail.com")
                );

        //when
     List<PlayerResponseDto> playerResponseDtos = playerService.findPlayerByName(firstname);

     //then
     assertEquals(players.size(), playerResponseDtos.size());

     verify(repository, times(1))
        .findAllByFirstnameContaining(firstname);
    }


}