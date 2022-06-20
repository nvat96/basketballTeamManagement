package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import com.axonactive.basketball.services.dtos.PlayerWithTeamDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    @Mapping(target = "playerName",expression = "java(player.getFirstName() +\"\" +player.getLastName())")
    PlayerDTO toDTO (Player player);
    List<PlayerDTO> toDTOs(List<Player> players);
    @AfterMapping
    default void setPlayerName(Player player, @MappingTarget PlayerDTO target){
        target.setPlayerName(player.getFirstName() + " " + player.getLastName());
    }
}
