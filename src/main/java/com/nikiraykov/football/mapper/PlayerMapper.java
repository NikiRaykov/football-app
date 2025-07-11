package com.nikiraykov.football.mapper;

import com.nikiraykov.football.dto.PlayerDTO;
import com.nikiraykov.football.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class PlayerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PlayerDTO toDTO(Player player){
        return modelMapper.map(player, PlayerDTO.class);
    }

    public Player toEntity(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }
}
