package com.haider.playerteam.player;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PlayerController {

  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
      this.playerService = playerService;
  }



    @PostMapping("/players")
    public PlayerResponseDto savePlayer(
           @Valid @RequestBody PlayerDto dto
    ) {
     return this.playerService.saveStudent(dto);
    }




    @GetMapping("/players")
    public List<PlayerResponseDto> findAllPlayer() {
        return this.playerService.findAllPlayer();
    }

    @GetMapping("/players/{player-id}")
    public PlayerResponseDto findPlayerById(
            @PathVariable("player-id") Integer id
    ) {

      return this.playerService.findPlayerById(id);

    }

    @GetMapping("/players/search/{player-name}")
    public List<PlayerResponseDto> findPlayerByName(
            @PathVariable("player-name") String name
    ) {
            return this.playerService.findPlayerByName(name);
    }

    @DeleteMapping("/players/{player-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("player-id") Integer id
    ) {
        this.playerService.delete(id);
    }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgumentNotValidException(
          MethodArgumentNotValidException exp) {

    var errors = new HashMap<String, String>();
    exp.getBindingResult().getAllErrors()
            .forEach(error -> {
              var fieldName = ((FieldError)error).getField();
              var errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

}
