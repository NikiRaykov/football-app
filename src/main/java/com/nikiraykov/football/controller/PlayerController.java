package com.nikiraykov.football.controller;

import com.nikiraykov.football.dto.PlayerDTO;
import com.nikiraykov.football.mapper.PlayerMapper;
import com.nikiraykov.football.model.Player;
import com.nikiraykov.football.model.Team;
import com.nikiraykov.football.repository.PlayerRepository;
import com.nikiraykov.football.repository.TeamRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayerController {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TeamRepository teamRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @PostMapping("/players")
    PlayerDTO createPlayer(@Valid @RequestBody PlayerDTO dto) {
        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        Player player = playerMapper.toEntity(dto);
        player.setTeam(team);

        Player saved = playerRepository.save(player);

        return playerMapper.toDTO(saved);
    }

    @PutMapping("/players/{id}")
    public PlayerDTO updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO dto) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found!"));

        modelMapper.map(dto, player);

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        player.setTeam(team);

        Player saved = playerRepository.save(player);
        return playerMapper.toDTO(saved);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<String> deletePlayerById(@PathVariable Long id){
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found!"));

        playerRepository.delete(player);

        return ResponseEntity.ok("Player deleted!");
    }

    @GetMapping("/players/{id}")
    public PlayerDTO getPlayerByID(@PathVariable Long id){
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found!"));

        return playerMapper.toDTO(player);
    }

    @GetMapping("players")
    public List<PlayerDTO> getFilteredPlayers(@RequestParam(required = false) String position){
        List<Player> players;

        if (position != null && !position.isBlank()) {
            players = playerRepository.searchByPosition(position);
        } else {
            players = playerRepository.findAll();
        }

        return players.stream()
                .map(playerMapper::toDTO)
                .toList();
    }
     }




