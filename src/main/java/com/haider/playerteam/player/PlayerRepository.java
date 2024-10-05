package com.haider.playerteam.player;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findAllByFirstnameContaining(String firstname);
}
