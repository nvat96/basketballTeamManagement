package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.services.dtos.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    @Mapping(target = "arenaName",source = "arena.name")
    TeamDTO toDTO(Team team);
    List<TeamDTO> toDTOs(List<Team> teams);
}
