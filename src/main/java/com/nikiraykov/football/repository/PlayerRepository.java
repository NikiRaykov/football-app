package com.nikiraykov.football.repository;

import com.nikiraykov.football.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findTeamById(Long teamId);
    List<Player> searchByPosition(String position);

    @NonNull
    List<Player> findAll();
}

