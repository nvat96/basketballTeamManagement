package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    PlayerDTO toDTO (Player player);
    List<PlayerDTO> toDTOs(List<Player> players);
}
