package com.axonactive.basketball.services.mappers;

import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.services.dtos.PlayerWithStatsDTO;
import com.axonactive.basketball.services.dtos.StatsDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatsMapper {
    StatsMapper INSTANCE = Mappers.getMapper(StatsMapper.class);
    @Mapping(target = "playerName",expression = "java(stat.getPlayer().getFirstName() + \"\" + stat.getPlayer().getLastName())")
    StatsDTO toDTO(Stats stat);
    List<StatsDTO> toDTOs (List<Stats> stats);
    @Mapping(target = "fullName",expression = "java(stat.getPlayer().getFirstName() + \"\" + stat.getPlayer().getLastName())")
    PlayerWithStatsDTO toPlayerWithStatsDTO(Stats stat);
    List<PlayerWithStatsDTO> toPlayerWithStatsDTOs(List<Stats> stats);
    @AfterMapping
    default void setPlayerName(Stats stat, @MappingTarget StatsDTO target){
        target.setPlayerName(stat.getPlayer().getFirstName() + " " + stat.getPlayer().getLastName());
    }
}
