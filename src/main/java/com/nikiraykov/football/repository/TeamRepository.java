package com.nikiraykov.football.repository;

import com.nikiraykov.football.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
