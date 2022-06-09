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
//    @Mapping(source = "playerContract.position",target = "position")
//    @Mapping(source = "stats.height",target = "height")
//    @Mapping(source = "team.name",target = "team")
//    @Mapping(source = "statusReport.status",target = "status")
    PlayerDTO toDTO (Player player);
    List<PlayerDTO> toDTOs(List<Player> players);
}
