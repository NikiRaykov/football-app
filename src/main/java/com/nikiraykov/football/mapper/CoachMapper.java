package com.nikiraykov.football.mapper;

import com.nikiraykov.football.dto.CoachDTO;
import com.nikiraykov.football.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class CoachMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CoachDTO toDTO(Coach coach) {
        return modelMapper.map(coach, CoachDTO.class);
    }

    public Coach toEntity(CoachDTO coachDTO) {
        return modelMapper.map(coachDTO, Coach.class);
    }
}
