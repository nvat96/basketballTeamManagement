package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
//    @Mapping(source = "playerContract.playerContract.position",target = "position")
//    @Mapping(source = "stats.stats.height",target = "height")
//    @Mapping(source = "team.team.name",target = "team")
//    @Mapping(source = "statusReport.statusReport.status",target = "status")
    @Mapping(target = "gender", expression = "java(player.getGender().toString())")
    @Mapping(target = "nationality", expression = "java(player.getNationality().toString())")
    @Mapping(target = "typeOfPlayer", expression = "java(player.getTypeOfPlayer().toString())")
    PlayerDTO toDTO (Player player);
    List<PlayerDTO> toDTOs(List<Player> players);
}
