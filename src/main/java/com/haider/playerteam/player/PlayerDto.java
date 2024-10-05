package com.haider.playerteam.player;

import jakarta.validation.constraints.NotEmpty;

public record PlayerDto(
        @NotEmpty(message = "Firstname should not be empty")
        String firstname,
        @NotEmpty(message = "Lastname should not be empty")
        String lastname,
        String email,
        Integer teamId) {
}
