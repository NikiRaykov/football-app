package com.nikiraykov.football.controller;

import com.nikiraykov.football.dto.PlayerDTO;
import com.nikiraykov.football.dto.TeamDTO;
import com.nikiraykov.football.mapper.TeamMapper;
import com.nikiraykov.football.model.Player;
import com.nikiraykov.football.model.Team;
import com.nikiraykov.football.repository.PlayerRepository;
import com.nikiraykov.football.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/teams")
    Team createTeam (@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @GetMapping("/teams")
    List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/teams/{id}")
    TeamDTO teamById(@PathVariable Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found!"));

        return teamMapper.toDTO(team);
    }


    @GetMapping("/teams/{id}/players")
    List<PlayerDTO> getPlayersByTeam(@PathVariable Long id) {
        List<Player> players = playerRepository.findTeamById(id);

        List<PlayerDTO> result = new ArrayList<>();

        for (Player p: players) {
            PlayerDTO dto = new PlayerDTO();
            dto.setName(p.getName());
            dto.setAge(p.getAge());
            dto.setPosition(p.getPosition());
            dto.setNationality(p.getNationality());

            if (p.getTeam() != null) {
                dto.setTeamId(p.getTeam().getId());
            } else {
                dto.setTeamId(null);
            }
            
            result.add(dto);
        }

        return result;
    }

    @DeleteMapping("/teams/{id}")
    ResponseEntity<String> relegateTeam(@PathVariable("id") Long teamID) {
        Team team = teamRepository.findById(teamID)
                .orElseThrow(() -> new RuntimeException("Team not found!"));

        teamRepository.delete(team);

        return ResponseEntity.ok("Team relegated to SERIA B");
    }

    @PutMapping("/teams/{id}")
    TeamDTO updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found!"));

        modelMapper.map(teamDTO, team);

        Team save = teamRepository.save(team);

        return teamMapper.toDTO(save);
    }
}
