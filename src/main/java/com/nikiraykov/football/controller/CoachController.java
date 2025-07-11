package com.nikiraykov.football.controller;

import com.nikiraykov.football.dto.CoachDTO;
import com.nikiraykov.football.mapper.CoachMapper;
import com.nikiraykov.football.model.Coach;
import com.nikiraykov.football.model.Team;
import com.nikiraykov.football.repository.CoachRepository;
import com.nikiraykov.football.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CoachController {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CoachMapper coachMapper;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/coaches")
    List<CoachDTO> getAllCoaches() {
        List<Coach> coaches = coachRepository.findAll();

       return coaches.stream()
                .map(coachMapper::toDTO)
               .toList();

    }

    @PutMapping("/coaches/{id}/team")
    public CoachDTO assignCoachToTeam(@PathVariable("id") Long coachId,
                                      @RequestParam Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team now found!"));

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found"));


        coach.setTeam(team);
        coachRepository.save(coach);

        return coachMapper.toDTO(coach);
    }

    @GetMapping("/coaches/{id}")
    public CoachDTO getCoachById(@PathVariable("id") Long coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found with this id " + coachId));

        return coachMapper.toDTO(coach);
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<String> fireCoach(@PathVariable("id") Long coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() -> new RuntimeException("Coach not found with this id " + coachId));

        coachRepository.delete(coach);

        return new ResponseEntity<>("Coach fired!", HttpStatus.OK);
    }

    @PostMapping("/coaches")
    public CoachDTO hireCoach(@RequestBody CoachDTO dto) {
        Coach coach = coachMapper.toEntity(dto);

        Team team = teamRepository.findById(dto.getTeamId())
                        .orElseThrow(() -> new RuntimeException("Team not fonud!"));

        coach.setTeam(team);

        coachRepository.save(coach);

        return coachMapper.toDTO(coach);
    }

    @PutMapping("/coaches/{id}")
    public CoachDTO updateCoach(@PathVariable("id") Long coachId,
                                @RequestBody CoachDTO dto){
        Coach coach = coachRepository.findById(coachId).
                orElseThrow(() -> new RuntimeException("Coach with ID not found!"));

        modelMapper.map(dto, coach);

        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found!"));

        coach.setTeam(team);

        Coach saved = coachRepository.save(coach);

        return coachMapper.toDTO(saved);
    }
}
