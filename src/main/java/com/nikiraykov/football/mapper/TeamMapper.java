

package com.nikiraykov.football.mapper;
import com.nikiraykov.football.dto.TeamDTO;
import com.nikiraykov.football.model.Coach;
import com.nikiraykov.football.model.Team;
import com.nikiraykov.football.repository.CoachRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class TeamMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CoachRepository coachRepository;


    public TeamDTO toDTO(Team team) {
        TeamDTO dto = modelMapper.map(team, TeamDTO.class);

        if (team.getCoach() != null) {
            dto.setCoachId(team.getCoach().getId());
        }

        return dto;

    }

    public Team toEntity(TeamDTO teamDTO) {

        return modelMapper.map(teamDTO, Team.class);
    }
}
